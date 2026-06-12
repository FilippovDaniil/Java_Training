# Модуль 98. Spring Security: переход к stateless, JWT — выдача и валидация

HTTP Basic и сессии ([модули 94–95](../m94_spring_security_inmemory/theory.md)) привязывают клиента к серверу (сессия) или шлют пароль в каждом запросе. Для масштабируемых API применяют **stateless**-аутентификацию через **JWT** (JSON Web Token): сервер не хранит сессию — токен самодостаточен. В этом модуле — устройство JWT, его **выдача** и **валидация** библиотекой `jjwt`.

> Практика — задачи в `practice/`. Зависимости: `spring-boot-starter-web`, `spring-boot-starter-security`, JWT — `io.jsonwebtoken:jjwt-api:0.12.x` (+ `jjwt-impl`, `jjwt-jackson` в runtime). Реалистичные импорты + «ТРЕБУЮТСЯ ЗАВИСИМОСТИ»; bare-javac не компилируется (норма). Сквозной проект — **Task Tracker API**.

---

## Stateless vs session (повторение)

```
   SESSION (stateful)                    JWT (stateless)
   ------------------                    ---------------
   вход → сервер хранит сессию           вход → сервер выдаёт ТОКЕН
   клиент шлёт JSESSIONID (cookie)       клиент шлёт Authorization: Bearer <token>
   сервер помнит, кто вошёл              сервер НЕ хранит ничего — токен самодостаточен
   масштаб: нужна общая сессия           масштаб: любой инстанс проверит подпись
```

В Spring Security stateless включается политикой сессий:

```java
http.sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
```

---

## Устройство JWT

Токен — три части в base64url, разделённые точками: `header.payload.signature`.

```
   eyJhbGciOiJIUzI1NiJ9 . eyJzdWIiOiJhbGljZSIsImV4cCI6...} . 4f9a8c...
   +---- header ----+     +-------- payload (claims) ------+  + signature +

   header:    {"alg":"HS256","typ":"JWT"}        — алгоритм подписи
   payload:   {"sub":"alice","roles":["USER"],   — claims (данные)
               "iat":1700000000,"exp":1700003600}
   signature: HMAC-SHA256(base64(header)+"."+base64(payload), secretKey)
```

| Часть | Содержит |
|-------|----------|
| header | алгоритм (`HS256`), тип |
| payload (claims) | `sub` (кто), `exp` (до когда), `iat` (когда выдан), произвольные (`roles`) |
| signature | подпись секретным ключом — гарантирует, что токен не подделан/не изменён |

> ⚠️ Payload **не зашифрован**, только закодирован base64 — любой может его прочитать. **Не кладите в JWT секреты/пароли.** Подпись лишь гарантирует целостность (никто не изменил), не конфиденциальность.

---

## jjwt 0.12.x: ключ

Для `HS256` нужен секрет ≥ 256 бит (32 байта). Ключ создаётся из секрета:

```java
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;

// секрет берётся из конфигурации (application.properties), НЕ хардкодить в проде!
SecretKey key = Keys.hmacShaKeyFor("change-me-to-a-very-long-secret-key-256bits!".getBytes(StandardCharsets.UTF_8));
```

> Слишком короткий секрет → `WeakKeyException`. В проде секрет хранят вне кода (env/secret-store), как в [модуле 84](../m84_spring_data_jpa_migrations/theory.md) для credentials.

---

## Выдача токена (jjwt 0.12.x)

```java
import io.jsonwebtoken.Jwts;
import java.util.Date;

String token = Jwts.builder()
        .subject(username)                                   // sub — кто
        .claim("roles", List.of("ROLE_USER"))                 // произвольные claims
        .issuedAt(new Date())                                 // iat
        .expiration(new Date(System.currentTimeMillis() + 3_600_000))  // exp (+1 час)
        .signWith(key)                                        // подпись (алгоритм выводится из ключа)
        .compact();                                           // → строка токена
```

| Метод (0.12.x) | Назначение |
|----------------|------------|
| `subject(...)` | claim `sub` |
| `claim(name, value)` | произвольный claim |
| `issuedAt`/`expiration` | `iat`/`exp` |
| `signWith(key)` | подписать (в 0.12.x алгоритм определяется по ключу) |
| `compact()` | собрать строку |

---

## Валидация и разбор токена (jjwt 0.12.x)

```java
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

Jws<Claims> jws = Jwts.parser()
        .verifyWith(key)            // проверить подпись тем же ключом
        .build()
        .parseSignedClaims(token);  // бросит исключение, если подпись неверна/токен истёк

Claims claims = jws.getPayload();
String username = claims.getSubject();           // "alice"
Date exp = claims.getExpiration();
Object roles = claims.get("roles");
```

Исключения при разборе (все — наследники `JwtException`):

| Исключение | Причина |
|------------|---------|
| `ExpiredJwtException` | токен просрочен (`exp` в прошлом) |
| `io.jsonwebtoken.security.SignatureException` | подпись не сошлась (подделка/другой ключ) |
| `MalformedJwtException` | повреждённый формат |
| `JwtException` (базовый) | ловить общий случай → токен невалиден |

```java
public boolean isValid(String token) {
    try { Jwts.parser().verifyWith(key).build().parseSignedClaims(token); return true; }
    catch (JwtException e) { return false; }      // любая проблема → невалиден
}
```

---

## JwtService — утилита проекта

Логику работы с токеном выносят в сервис:

```java
@Service
public class JwtService {
    private final SecretKey key;
    private final long expirationMs;

    public JwtService(@Value("${jwt.secret}") String secret,
                      @Value("${jwt.expiration-ms:3600000}") long expirationMs) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        this.expirationMs = expirationMs;
    }

    public String generateToken(String username, Collection<String> roles) { /* builder */ }
    public String extractUsername(String token) { /* parse → getSubject */ }
    public boolean isValid(String token) { /* try parse */ }
}
```

> Сам **фильтр**, который читает заголовок `Authorization: Bearer ...` и кладёт пользователя в `SecurityContext`, — тема [модуля 99](../m99_spring_security_custom_jwt_filter/theory.md). Здесь — только выдача (login) и проверка токена.

---

## Endpoint логина

Проверяем логин-пароль через `AuthenticationManager`, при успехе выдаём токен:

```java
@PostMapping("/api/auth/login")
public TokenResponse login(@RequestBody LoginRequest req) {
    Authentication auth = authManager.authenticate(
        new UsernamePasswordAuthenticationToken(req.username(), req.password()));  // проверит пароль
    String token = jwtService.generateToken(auth.getName(), rolesOf(auth));
    return new TokenResponse(token);
}
```

`AuthenticationManager` берётся из `AuthenticationConfiguration`:

```java
@Bean
AuthenticationManager authManager(AuthenticationConfiguration cfg) throws Exception {
    return cfg.getAuthenticationManager();
}
```

```
   POST /api/auth/login {username, password}
        |
   AuthenticationManager.authenticate → проверка через UserDetailsService + PasswordEncoder
        | успех
   JwtService.generateToken → {token}
        |
   клиент сохраняет токен и шлёт его в Authorization: Bearer <token> (фильтр — модуль 99)
```

---

## Подводные камни

| Проблема | Причина | Решение |
|----------|---------|---------|
| `WeakKeyException` | секрет < 256 бит для HS256 | секрет ≥ 32 байт |
| Секрет в коде | хардкод | `@Value("${jwt.secret}")`, хранить вне кода |
| Положили пароль в payload | payload не шифруется | только публичные claims (sub, roles, exp) |
| Токен «вечный» | нет `exp` | всегда задавать срок жизни |
| Старый API jjwt (`parseClaimsJws`, `setSubject`) | методы из 0.11.x | в 0.12.x: `parseSignedClaims`, `subject`, `verifyWith` |
| Валидация не ловит истёкший | не обрабатывают `ExpiredJwtException` | ловить `JwtException` (базовый) |
| Логин не проверяет пароль | вручную сравнивают | `AuthenticationManager.authenticate` |

---

## Дополнительные источники

- [JWT.io — структура токена](https://jwt.io/introduction).
- [jjwt (Java JWT) — README](https://github.com/jwtk/jjwt).
- [Spring Security: Authentication](https://docs.spring.io/spring-security/reference/servlet/authentication/index.html).
- [модуль 95](../m95_spring_security_session_cors/theory.md) — session vs stateless.

## Что дальше

В [модуле 99](../m99_spring_security_custom_jwt_filter/theory.md) — **custom JWT-фильтр**: чтение `Bearer`-токена из заголовка, установка `Authentication` в `SecurityContext`, плюс встроенная поддержка Bearer (`oauth2ResourceServer`) и тестирование. Тогда выданный здесь токен начнёт реально авторизовать запросы.
