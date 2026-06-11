# Модуль 100. Spring Security: тестирование, hardening и аудит

Безопасность без тестов — иллюзия безопасности. Один неверный `requestMatcher` открывает админский эндпоинт всем. В этом модуле — как **проверять правила доступа автоматически** (`@WebMvcTest` + `spring-security-test`), как тестировать аутентификацию (роли, HTTP Basic, CSRF, JWT) и как **усилить** конфигурацию (security-заголовки, аудит событий входа). Этим модулем закрывается блок Spring Security (93–100).

> Практика — задачи в `practice/`. Это **тест-классы (без `main`)**, запуск в IDE (▶) или `./gradlew test`. Зависимости: `spring-boot-starter-web`, `spring-boot-starter-security`, `spring-boot-starter-test`, **`spring-security-test`** (post-processors и `@WithMockUser`); для JWT-задач — `io.jsonwebtoken:jjwt-*:0.12.x`. bare-javac не компилируется (норма). Сквозной проект — **Task Tracker API**.

---

## `@WebMvcTest` + Spring Security

`@WebMvcTest` поднимает **web-срез**: контроллеры, `MockMvc`, и — в отличие от обычного slice — автоконфигурацию Spring Security (фильтры применяются). Это позволяет проверять правила доступа быстро, без полного контекста и БД.

```java
@WebMvcTest(TaskController.class)
class TaskControllerSecurityTest {

    @Autowired MockMvc mockMvc;
    @MockBean TaskService taskService;     // зависимости контроллера — мокаем

    @Test
    void anonymous_gets_401() throws Exception {
        mockMvc.perform(get("/api/tasks"))
               .andExpect(status().isUnauthorized());     // нет аутентификации → 401
    }
}
```

> Если в `SecurityConfig` есть свой `SecurityFilterChain`, импортируйте его: `@Import(SecurityConfig.class)` — иначе сработает дефолтная автоконфигурация, и правила не совпадут с продом (см. модуль [76](../m76_spring_rest_testing/theory.md)).

---

## `spring-security-test`: два инструмента

| Инструмент | Что делает | Импорт |
|------------|------------|--------|
| `@WithMockUser` | подставляет фиктивного пользователя на весь тест | `org.springframework.security.test.context.support.WithMockUser` |
| post-processors (`.with(...)`) | модифицируют конкретный запрос: `csrf()`, `httpBasic()`, `jwt()`, `user()` | `...test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*` |

### `@WithMockUser` — аутентифицированный пользователь

```java
@Test
@WithMockUser(username = "alice", roles = "USER")     // roles="USER" → authority ROLE_USER
void authenticated_user_gets_200() throws Exception {
    mockMvc.perform(get("/api/tasks")).andExpect(status().isOk());
}

@Test
@WithMockUser(roles = "USER")                          // у пользователя нет ADMIN
void user_forbidden_on_admin() throws Exception {
    mockMvc.perform(get("/api/admin/stats")).andExpect(status().isForbidden());  // 403
}
```

> `roles = "USER"` автоматически становится authority `ROLE_USER`. Если нужен «сырой» authority без префикса — `authorities = "SCOPE_read"`.

---

## 401 vs 403 (ключевое различие)

```
   нет/неверная аутентификация   →  401 Unauthorized   («ты кто?»)
   аутентифицирован, но нет прав →  403 Forbidden       («тебе сюда нельзя»)
```

| Сценарий теста | Ожидаемый статус |
|----------------|------------------|
| без `@WithMockUser` / без токена | `isUnauthorized()` (401) |
| `@WithMockUser(roles="USER")` на `hasRole("ADMIN")` | `isForbidden()` (403) |
| `@WithMockUser(roles="ADMIN")` на `hasRole("ADMIN")` | `isOk()` (200) |

---

## Post-processors: `httpBasic`, `csrf`, `jwt`

```java
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;

// HTTP Basic (модуль 95):
mockMvc.perform(get("/api/tasks").with(httpBasic("alice", "password")))
       .andExpect(status().isOk());

// CSRF-токен для POST в session-режиме (без него — 403):
mockMvc.perform(post("/api/tasks").with(csrf())
                .contentType(MediaType.APPLICATION_JSON).content("{\"title\":\"X\"}"))
       .andExpect(status().isCreated());

// JWT через встроенный resource server (модуль 99, oauth2ResourceServer):
mockMvc.perform(get("/api/tasks").with(jwt().jwt(j -> j.claim("scope", "read"))))
       .andExpect(status().isOk());
```

> `.with(jwt())` работает с `oauth2ResourceServer` (Nimbus). Для **своего** фильтра (модуль 99) JWT тестируют иначе — реальным токеном в заголовке (см. ниже).

---

## Тестирование собственного JWT-фильтра

Если используется свой `OncePerRequestFilter`, генерируем реальный токен и шлём в заголовке:

```java
@Test
void valid_jwt_grants_access() throws Exception {
    String token = jwtService.generateToken("alice", List.of("ROLE_USER"));
    mockMvc.perform(get("/api/tasks").header("Authorization", "Bearer " + token))
           .andExpect(status().isOk());
}

@Test
void garbage_token_gives_401() throws Exception {
    mockMvc.perform(get("/api/tasks").header("Authorization", "Bearer not-a-jwt"))
           .andExpect(status().isUnauthorized());      // фильтр не аутентифицирует → 401, НЕ 500
}
```

### ⚠️ Критическая ловушка: не мокайте сам фильтр

```java
// ❌ НЕ РАБОТАЕТ — тест получает 200 с пустым телом, цепочка обрывается:
@MockBean JwtAuthenticationFilter jwtFilter;
```

`JwtAuthenticationFilter extends OncePerRequestFilter`, а `doFilter()` там объявлен `final`. Mockito не может перехватить `final`-метод → мок вызывает реальный `doFilter()` → тот зовёт `doFilterInternal()` на моке (пустой) → `chain.doFilter()` не вызывается → `DispatcherServlet` не достигается → **200 с пустым ответом**.

```java
// ✅ ПРАВИЛЬНО — поднять реальный фильтр, замокать его ЗАВИСИМОСТИ:
@MockBean JwtService jwtService;                 // зависимость фильтра
```

Реальный фильтр загружается; без токена в запросе он просто зовёт `chain.doFilter` — всё работает.

### Публичные auth-эндпоинты: отключить фильтры

Для тестов `/api/auth/login` (где фильтры мешают) проще обойти их целиком:

```java
@WebMvcTest(controllers = AuthController.class)
@AutoConfigureMockMvc(addFilters = false)        // обходит ВСЕ security-фильтры
class AuthControllerTest {
    // Bean Validation (@Valid) продолжает работать; security — нет
}
```

При конфликте бинов `UserDetailsService` в slice:

```java
@WebMvcTest(controllers = TaskController.class,
            excludeAutoConfiguration = UserDetailsServiceAutoConfiguration.class)
```

---

## Hardening: проверяем security-заголовки

Spring Security по умолчанию добавляет защитные заголовки. Тест фиксирует, что они на месте:

```java
@Test
@WithMockUser
void security_headers_present() throws Exception {
    mockMvc.perform(get("/api/tasks"))
           .andExpect(header().string("X-Content-Type-Options", "nosniff"))
           .andExpect(header().string("X-Frame-Options", "DENY"))
           .andExpect(header().string("Cache-Control", containsString("no-store")));
}
```

| Заголовок | Зачем |
|-----------|-------|
| `X-Content-Type-Options: nosniff` | запретить MIME-sniffing |
| `X-Frame-Options: DENY` | защита от clickjacking |
| `Cache-Control: no-store` | не кэшировать защищённые ответы |
| `Strict-Transport-Security` (HSTS) | принудительный HTTPS (на проде) |

Тонкая настройка — через `http.headers(h -> h.frameOptions(...).contentSecurityPolicy(...))`.

---

## Аудит: события аутентификации

Spring публикует события входа — их слушают для аудита (кто вошёл, кто не смог):

```java
@Component
class AuthEventListener {
    @EventListener
    void onSuccess(AuthenticationSuccessEvent e) {
        log.info("LOGIN OK: {}", e.getAuthentication().getName());
    }
    @EventListener
    void onFailure(AbstractAuthenticationFailureEvent e) {
        log.warn("LOGIN FAIL: {} ({})", e.getAuthentication().getName(),
                 e.getException().getMessage());
    }
}
```

> Аудит — основа для обнаружения brute-force и расследования инцидентов (тема [модуля 110](../m110_spring_test_security_async/theory.md) — security-тесты в полном контексте, и далее SecOps).

---

## Подводные камни

| Проблема | Причина | Решение |
|----------|---------|---------|
| Тест безопасности проходит, но прод дырявый | `@WebMvcTest` не подхватил `SecurityConfig` | `@Import(SecurityConfig.class)` |
| `@MockBean` на фильтре → 200 пустой ответ | `doFilter` в `OncePerRequestFilter` — `final` | мокать зависимости фильтра (`JwtService`), не сам фильтр |
| POST-тест → 403 неожиданно | включён CSRF (session-режим) | `.with(csrf())` в запросе |
| Публичный эндпоинт в тесте → 401 | правила применяются, тест не аутентифицирован | `@WithMockUser` или `@AutoConfigureMockMvc(addFilters=false)` |
| Контекст slice не поднимается с `oauth2Login()` | нет бина `ClientRegistrationRepository` | `@MockBean ClientRegistrationRepository` |
| `@WithMockUser(roles="ROLE_USER")` не даёт доступ | двойной префикс `ROLE_ROLE_USER` | в `roles` — без префикса (`"USER"`); префикс добавит Spring |
| Битый токен → 500 вместо 401 | исключение из фильтра не поймано | `try/catch (JwtException)` в фильтре (модуль 99) |
| Конфликт бинов `UserDetailsService` | дефолтная автоконфигурация + свой бин | `excludeAutoConfiguration = UserDetailsServiceAutoConfiguration.class` |

---

## Дополнительные источники

- [Spring Security: Testing](https://docs.spring.io/spring-security/reference/servlet/test/index.html).
- [Spring Security: Testing Method Security & MockMvc](https://docs.spring.io/spring-security/reference/servlet/test/mockmvc/index.html).
- [Spring Security: HTTP Response Headers](https://docs.spring.io/spring-security/reference/servlet/exploits/headers.html).
- [модуль 76](../m76_spring_rest_testing/theory.md) — `@WebMvcTest`/MockMvc (база тестирования контроллеров).

## Что дальше

Блок **Spring Security (93–100) закрыт**: от первого 401 до stateless JWT с тестами, hardening и аудитом. Дальше — **Часть «Spring Test» (модули 101–110)**: системное тестирование от unit без контекста до Testcontainers и REST Docs. Начинаем с [модуля 101](../m101_spring_test_basics/theory.md) — инструменты тест-стека (JUnit 5, AssertJ, Hamcrest, JSONassert, Mockito).
