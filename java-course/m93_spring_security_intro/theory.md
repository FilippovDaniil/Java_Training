# Модуль 93. Spring Security: введение, что меняет starter-security, цепочка фильтров

REST API из [блока 67–76](../module-67-spring-rest-http-backend/theory.md) открыто всем. Перед продакшеном нужна **безопасность**: кто ты (аутентификация) и что тебе можно (авторизация). Spring Security добавляет это декларативно. В этом модуле — что происходит после подключения стартера, как устроена **цепочка фильтров** и базовый `SecurityFilterChain`.

> Практика — задачи в `practice/`. Зависимости: `spring-boot-starter-web`, `spring-boot-starter-security`. Реалистичные импорты `org.springframework.security.*` + «ТРЕБУЮТСЯ ЗАВИСИМОСТИ» в шапке; bare-javac **не компилируется** (это норма). Сквозной проект — **Task Tracker API**.

---

## Аутентификация vs авторизация

| Термин | Вопрос | Пример |
|--------|--------|--------|
| **Аутентификация** (authn) | «Кто ты?» | логин/пароль, JWT-токен |
| **Авторизация** (authz) | «Что тебе можно?» | роль ADMIN может удалять задачи |

Сначала authn (установить личность), затем authz (проверить права).

---

## Что меняется после `spring-boot-starter-security`

Достаточно добавить зависимость — и Spring Boot **автоматически**:

```
   ДО стартера:  GET /api/tasks → 200 (открыто всем)
   ПОСЛЕ:        GET /api/tasks → 401 Unauthorized (всё закрыто по умолчанию)
```

| Что появляется автоматически | Деталь |
|------------------------------|--------|
| **Все** эндпоинты защищены | `anyRequest().authenticated()` по умолчанию |
| Пользователь по умолчанию | `user` + пароль печатается в лог при старте (`Using generated security password: ...`) |
| Форма логина | `GET /login` (сгенерированная страница) |
| HTTP Basic | работает для REST-клиентов |
| `/logout` | завершение сессии |
| CSRF-защита | включена для изменяющих запросов (POST/PUT/DELETE) |
| Заголовки безопасности | `X-Content-Type-Options`, `Cache-Control` и др. |

> «Secure by default» — пока вы не настроите свои правила, закрыто всё. Это безопасно, но для REST нужно настроить под себя.

---

## Цепочка фильтров (Security Filter Chain)

Spring Security — это **цепочка сервлет-фильтров** перед вашими контроллерами. Каждый запрос проходит её до DispatcherServlet.

```
   HTTP-запрос
        │
   ┌────▼─────────────────────────────────────────────┐
   │  FilterChainProxy (springSecurityFilterChain)     │
   │   ├─ SecurityContextPersistenceFilter             │
   │   ├─ CsrfFilter                                   │
   │   ├─ UsernamePasswordAuthenticationFilter (форма) │
   │   ├─ BasicAuthenticationFilter (HTTP Basic)       │
   │   ├─ ... (≈15 фильтров)                           │
   │   └─ AuthorizationFilter (проверка прав)          │
   └────┬──────────────────────────────────────────────┘
        │  (если разрешено)
   DispatcherServlet → ваш @RestController
```

- `DelegatingFilterProxy` (из контейнера) делегирует в `FilterChainProxy` (бин Spring).
- Если фильтр аутентификации установил личность, она попадает в `SecurityContext`.
- `AuthorizationFilter` в конце решает: пропустить к контроллеру или вернуть 401/403.

---

## SecurityFilterChain — точка конфигурации (Spring Security 6.x)

В Spring Security 6 (Spring Boot 3.x) настройка — **бин** `SecurityFilterChain` с lambda-DSL (старый `WebSecurityConfigurerAdapter` удалён).

```java
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/actuator/health", "/api/auth/**").permitAll()  // открыто
                .requestMatchers("/api/admin/**").hasRole("ADMIN")                // только ADMIN
                .anyRequest().authenticated()                                     // остальное — после входа
            )
            .httpBasic(org.springframework.security.config.Customizer.withDefaults());
        return http.build();
    }
}
```

| Элемент | Смысл |
|---------|-------|
| `authorizeHttpRequests` | правила доступа к URL |
| `requestMatchers("...")` | какие пути (в SS6 — `requestMatchers`, не `antMatchers`) |
| `permitAll()` | без аутентификации |
| `authenticated()` | нужен вход |
| `hasRole("ADMIN")` | нужна роль |
| порядок правил | **важен**: первое совпадение выигрывает; `anyRequest()` — последним |

---

## SecurityContext: кто сейчас вошёл

После успешной аутентификации личность лежит в `SecurityContextHolder`:

```java
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

Authentication auth = SecurityContextHolder.getContext().getAuthentication();
String username = auth.getName();                       // имя текущего пользователя
var authorities = auth.getAuthorities();                // роли/права
```

В контроллере удобнее внедрять напрямую:

```java
@GetMapping("/me")
public String me(java.security.Principal principal) { return principal.getName(); }

// или с деталями:
@GetMapping("/me2")
public String me2(@AuthenticationPrincipal org.springframework.security.core.userdetails.UserDetails user) {
    return user.getUsername();
}
```

| Объект | Что даёт |
|--------|----------|
| `Authentication` | имя, authorities, признак аутентифицированности |
| `Principal` | стандартный JDK-интерфейс (имя пользователя) |
| `@AuthenticationPrincipal` | сам `UserDetails`/principal |
| `GrantedAuthority` | одна роль/право (`ROLE_ADMIN`, `READ`) |

---

## Подводные камни

| Проблема | Причина | Решение |
|----------|---------|---------|
| Всё внезапно 401 | стартер закрыл всё по умолчанию | настроить `SecurityFilterChain` (`permitAll` для публичного) |
| `antMatchers` не компилируется | удалён в SS6 | `requestMatchers` |
| Правила «не срабатывают» | неверный порядок (`anyRequest()` раньше частных) | частные правила — выше, `anyRequest()` — последним |
| `WebSecurityConfigurerAdapter` не найден | удалён в SS6 | бин `SecurityFilterChain` |
| REST-клиент видит форму логина (302) | `formLogin` редиректит | для REST — `httpBasic`/JWT, отдельный entry point ([модуль 95](../module-95-spring-security-session-cors/theory.md)) |
| `Authentication == null` | эндпоинт `permitAll`, пользователь не вошёл | проверять на null / защитить эндпоинт |

---

## Дополнительные источники

- [Spring Security: Architecture](https://docs.spring.io/spring-security/reference/servlet/architecture.html).
- [Spring Security: Authorize HTTP Requests](https://docs.spring.io/spring-security/reference/servlet/authorization/authorize-http-requests.html).
- [модуль 69](../module-69-spring-rest-controllers/theory.md) — Spring MVC и DispatcherServlet (что после цепочки фильтров).

## Что дальше

В [модуле 94](../module-94-spring-security-inmemory/theory.md) — первые пользователи: `InMemoryUserDetailsManager`, `PasswordEncoder` (BCrypt), форма логина и основы CSRF.
