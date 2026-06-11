# Модуль 99. Spring Security: custom JWT-фильтр и встроенная поддержка Bearer

В [модуле 98](../module-98-spring-security-jwt/theory.md) мы научились **выдавать** и **проверять** JWT (сервис `JwtService`, endpoint логина). Но выданный токен ещё ничего не авторизует: `/api/**` остаётся недоступным, потому что никто не читает заголовок `Authorization: Bearer ...` и не кладёт пользователя в `SecurityContext`. В этом модуле — **фильтр**, который замыкает stateless-флоу: читает токен из каждого запроса и аутентифицирует пользователя.

> Практика — задачи в `practice/`. Зависимости: `spring-boot-starter-web`, `spring-boot-starter-security`, JWT — `io.jsonwebtoken:jjwt-api:0.12.x` (+ `jjwt-impl`, `jjwt-jackson` в runtime); для задачи 06 (встроенный resource server) — `spring-boot-starter-oauth2-resource-server`. Реалистичные импорты + «ТРЕБУЮТСЯ ЗАВИСИМОСТИ»; bare-javac не компилируется (норма). Сквозной проект — **Task Tracker API**.

---

## Зачем нужен фильтр

В session-режиме Spring сам восстанавливает пользователя из `JSESSIONID` (cookie → сессия → `SecurityContext`). В stateless-режиме сессии нет — **каждый** запрос приходит «чистым», и единственный источник личности клиента — токен в заголовке. Кто-то должен на каждый запрос: достать токен, проверить подпись, собрать `Authentication` и положить его в `SecurityContext`. Это и делает фильтр.

```
   Запрос с Authorization: Bearer eyJ...
        │
   ┌────▼─────────────────────────────────────────┐
   │ JwtAuthenticationFilter (OncePerRequestFilter) │
   │  1. читает заголовок Authorization             │
   │  2. префикс "Bearer " → вырезает токен          │
   │  3. JwtService.isValid(token)?                  │
   │  4. да → строит UsernamePasswordAuthenticationToken
   │         → SecurityContextHolder.setAuthentication│
   │  5. chain.doFilter(req, res)  ← всегда!          │
   └────┬───────────────────────────────────────────┘
        │
   остальная цепочка → авторизация (authenticated / hasRole) → контроллер
```

После того как фильтр положил `Authentication` в контекст, правила `authorizeHttpRequests` (модуль [97](../module-97-spring-security-authorization/theory.md)) видят пользователя как залогиненного.

---

## `OncePerRequestFilter`

Базовый класс Spring для фильтров, гарантирующий **один вызов на запрос** (даже при forward/include внутри сервлет-контейнера). Переопределяем единственный метод:

```java
import org.springframework.web.filter.OncePerRequestFilter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.*;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain)
            throws ServletException, IOException {
        // 1. достать токен; 2. проверить; 3. установить контекст; 4. chain.doFilter(...)
    }
}
```

> ⚠️ Метод `doFilter` в `OncePerRequestFilter` объявлен `final` — переопределять можно **только** `doFilterInternal`. Это важно и для тестов: мокать сам фильтр через `@MockBean` бессмысленно (Mockito не перехватит `final`-метод) — см. модуль [100](../module-100-spring-security-testing/theory.md).

---

## Шаг 1: чтение токена из заголовка

```java
String header = request.getHeader("Authorization");      // "Bearer eyJhbG..."
if (header == null || !header.startsWith("Bearer ")) {
    chain.doFilter(request, response);                    // токена нет → пропускаем дальше БЕЗ аутентификации
    return;
}
String token = header.substring(7);                       // вырезаем "Bearer " (7 символов)
```

| Заголовок | Действие |
|-----------|----------|
| отсутствует | `chain.doFilter` и выход — пусть авторизация решит (публичный путь → 200, защищённый → 401) |
| не начинается с `Bearer ` | то же — это не наш токен |
| `Bearer <token>` | `substring(7)` → дальше валидация |

---

## Шаг 2: валидация и установка `Authentication`

```java
if (jwtService.isValid(token)) {
    String username = jwtService.extractUsername(token);
    List<String> roles = jwtService.extractRoles(token);          // ["ROLE_USER", ...]

    var authorities = roles.stream()
            .map(SimpleGrantedAuthority::new)                     // строка → GrantedAuthority
            .toList();

    var authentication = new UsernamePasswordAuthenticationToken(
            username,        // principal
            null,            // credentials — пароль НЕ нужен (уже проверен при логине)
            authorities);    // полномочия для hasRole/hasAuthority

    SecurityContextHolder.getContext().setAuthentication(authentication);
}
chain.doFilter(request, response);                                // ВСЕГДА продолжаем цепочку
```

Ключевые моменты:

- **principal** = имя пользователя из `sub`; **credentials** = `null` (пароль при stateless не хранится и не нужен — личность подтверждена подписью токена).
- **authorities** строятся из claim `roles`. Если в токен клали `ROLE_USER` — `hasRole("USER")` сработает (Spring добавляет/ожидает префикс `ROLE_`).
- `chain.doFilter` вызывается **в любом случае** — и когда установили контекст, и когда нет. Иначе запрос «повиснет» и не дойдёт до контроллера.

---

## Шаг 3: регистрация фильтра в цепочке

Фильтр ставят **перед** `UsernamePasswordAuthenticationFilter` (стандартное место аутентификации) — чтобы к моменту проверки прав пользователь уже был в контексте:

```java
@Bean
SecurityFilterChain filterChain(HttpSecurity http, JwtAuthenticationFilter jwtFilter) throws Exception {
    http
        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/api/auth/**").permitAll()
            .anyRequest().authenticated())
        .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .csrf(csrf -> csrf.disable())
        .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);  // ← вставка фильтра
    return http.build();
}
```

```
   [DisableEncodeUrlFilter] → ... → [JwtAuthenticationFilter] → [UsernamePasswordAuthenticationFilter] → ...
                                            ▲ addFilterBefore вставил наш фильтр сюда
```

| Метод | Когда |
|-------|-------|
| `addFilterBefore(f, X.class)` | вставить `f` перед фильтром `X` (обычный выбор для JWT) |
| `addFilterAfter(f, X.class)` | вставить после `X` |
| `addFilterAt(f, X.class)` | заменить позицию `X` (редко) |

---

## Полная картина stateless-флоу

```
   ① POST /api/auth/login {username,password}
        → AuthenticationManager проверяет пароль (модуль 98)
        → JwtService.generateToken → {token}
   ② Клиент сохраняет token, шлёт его в каждом запросе:
        GET /api/tasks   Authorization: Bearer eyJ...
   ③ JwtAuthenticationFilter читает токен → SecurityContext = alice[ROLE_USER]
   ④ authorizeHttpRequests: /api/tasks authenticated → ✅ контроллер
        /api/admin/** hasRole('ADMIN') → alice без ADMIN → 403
```

---

## Альтернатива: встроенный OAuth2 Resource Server

Свой фильтр писать не обязательно. Spring Security умеет проверять Bearer-JWT «из коробки» через `oauth2ResourceServer` — нужен только `JwtDecoder`:

```java
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import javax.crypto.spec.SecretKeySpec;

@Bean
JwtDecoder jwtDecoder(@Value("${jwt.secret}") String secret) {
    var key = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
    return NimbusJwtDecoder.withSecretKey(key).macAlgorithm(MacAlgorithm.HS256).build();
}

@Bean
SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
        .authorizeHttpRequests(a -> a.requestMatchers("/api/auth/**").permitAll().anyRequest().authenticated())
        .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .csrf(c -> c.disable())
        .oauth2ResourceServer(o -> o.jwt(j -> {}));   // ← Spring сам разбирает Bearer-токен
    return http.build();
}
```

> Внимание: `oauth2ResourceServer` использует **Nimbus** (Spring Security), а не `jjwt`. Декодер `NimbusJwtDecoder` проверяет подпись; имя claim с ролями по умолчанию — `scope`/`scp`. Чтобы роли из своего claim `roles` стали authorities, настраивают `JwtAuthenticationConverter`.

### Custom-фильтр vs встроенный resource server

| Критерий | Custom-фильтр (`OncePerRequestFilter`) | `oauth2ResourceServer.jwt` |
|----------|----------------------------------------|----------------------------|
| Библиотека | любая (`jjwt`) | Nimbus (встроенная) |
| Контроль логики | полный (формат claims, обработка ошибок) | конвенции Spring |
| Кода писать | больше | меньше |
| Когда выбрать | свой формат токена, особая логика | стандартный JWT, OAuth2/OIDC-провайдер |

---

## Подводные камни

| Проблема | Причина | Решение |
|----------|---------|---------|
| Запрос «висит» / пустой ответ | забыли `chain.doFilter(...)` | вызывать `chain.doFilter` **всегда**, на всех ветках |
| `hasRole("USER")` не срабатывает | в токене роль без префикса `ROLE_` | класть `ROLE_USER` либо строить authority с префиксом |
| `@MockBean` на фильтре ломает тест → 200 пустой | `doFilter` в `OncePerRequestFilter` — `final`, Mockito не перехватит | не мокать фильтр; мокать его зависимости (`JwtService`) — модуль [100](../module-100-spring-security-testing/theory.md) |
| Невалидный токен роняет приложение | исключение из `doFilterInternal` не поймано | `try/catch (JwtException)` → не ставить контекст, продолжить цепочку |
| 401 даже с валидным токеном | фильтр не зарегистрирован / стоит после авторизации | `addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)` |
| Создаются сессии | не задан `STATELESS` | `sessionCreationPolicy(STATELESS)` |
| `extractRoles` возвращает не `List` | claim `roles` сериализован как `List<?>` | приводить аккуратно (`(List<String>) claims.get("roles")`) |

---

## Дополнительные источники

- [Spring Security: Adding a Custom Filter](https://docs.spring.io/spring-security/reference/servlet/architecture.html#servlet-adding-custom-filter).
- [Spring Security: OAuth2 Resource Server (JWT)](https://docs.spring.io/spring-security/reference/servlet/oauth2/resource-server/jwt.html).
- [`OncePerRequestFilter` — Javadoc](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/filter/OncePerRequestFilter.html).
- [модуль 98](../module-98-spring-security-jwt/theory.md) — выдача и валидация JWT (предыдущий шаг).

## Что дальше

В [модуле 100](../module-100-spring-security-testing/theory.md) — **тестирование безопасности**: `@WebMvcTest` + `spring-security-test`, `@WithMockUser`, post-processors `jwt()`/`httpBasic()`/`csrf()`, проверка 401/403/200, hardening и аудит. Закроем блок Spring Security (93–100) и перейдём к Spring Test (101–110).
