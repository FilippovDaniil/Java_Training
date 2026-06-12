# Модуль 94. Spring Security: in-memory пользователи, PasswordEncoder, форма логина, CSRF

В [модуле 93](../m93_spring_security_intro/theory.md) мы пользовались сгенерированным пользователем `user`. Теперь зададим **своих** пользователей в памяти, разберём **хеширование паролей** (`PasswordEncoder`), форму логина и азы **CSRF**.

> Практика — задачи в `practice/`. Зависимости: `spring-boot-starter-web`, `spring-boot-starter-security`. Реалистичные импорты + «ТРЕБУЮТСЯ ЗАВИСИМОСТИ»; bare-javac не компилируется (норма). Сквозной проект — **Task Tracker API**.

---

## UserDetails и UserDetailsService

Spring Security представляет пользователя как `UserDetails` (имя, пароль, authorities, флаги). Источник пользователей — `UserDetailsService` (метод `loadUserByUsername`).

```
   loadUserByUsername("alice")
        |
        ▼
   UserDetails { username, password(хеш), authorities[ROLE_USER], enabled... }
```

Готовая реализация «в памяти» — `InMemoryUserDetailsManager`.

---

## InMemoryUserDetailsManager

```java
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Bean
public UserDetailsService users(PasswordEncoder encoder) {
    UserDetails user = User.withUsername("alice")
            .password(encoder.encode("password"))   // ХРАНИМ ХЕШ, не сырой пароль!
            .roles("USER")                           // → authority ROLE_USER
            .build();
    UserDetails admin = User.withUsername("admin")
            .password(encoder.encode("admin"))
            .roles("ADMIN", "USER")
            .build();
    return new InMemoryUserDetailsManager(user, admin);
}
```

> `roles("USER")` автоматически добавляет префикс `ROLE_` → authority `ROLE_USER`. Поэтому `hasRole("USER")` и `hasAuthority("ROLE_USER")` — эквивалентны.

---

## PasswordEncoder: пароли только в хешах

**Никогда** не храните пароли в открытом виде. `PasswordEncoder` хеширует их (BCrypt — стандарт по умолчанию).

```java
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Bean
public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
}

// использование:
String hash = encoder.encode("password");          // $2a$10$....
boolean ok  = encoder.matches("password", hash);    // true
```

| Метод | Что делает |
|-------|-----------|
| `encode(raw)` | вернуть хеш (с солью внутри) |
| `matches(raw, hash)` | проверить совпадение (НЕ расшифровка — повторное хеширование) |

### DelegatingPasswordEncoder

`PasswordEncoderFactories.createDelegatingPasswordEncoder()` хранит хеши с префиксом алгоритма `{bcrypt}...`, `{noop}...` — позволяет мигрировать между алгоритмами.

```java
// если в БД "{bcrypt}$2a$..." — декодер сам выберет BCrypt
PasswordEncoder enc = PasswordEncoderFactories.createDelegatingPasswordEncoder();
```

> Ошибка `There is no PasswordEncoder mapped for the id "null"` — пароль без префикса при делегирующем энкодере. Лечение: явный `BCryptPasswordEncoder` или хранить хеши с префиксом `{bcrypt}`.

---

## Форма логина

`formLogin()` включает страницу входа (по умолчанию `GET /login`) для браузерных приложений:

```java
http.formLogin(form -> form
        .loginPage("/login").permitAll()         // своя страница (опционально)
        .defaultSuccessUrl("/tasks", true)        // куда после входа
);
http.logout(logout -> logout.logoutUrl("/logout").logoutSuccessUrl("/login?logout"));
```

Без `loginPage(...)` Spring сгенерирует страницу автоматически. Для REST-API форма не нужна — там `httpBasic`/JWT ([модули 95](../m95_spring_security_session_cors/theory.md), [98](../m98_spring_security_jwt/theory.md)).

---

## CSRF — что это и когда нужно

**CSRF** (Cross-Site Request Forgery): чужой сайт от имени залогиненного пользователя шлёт изменяющий запрос (ваш браузер сам приложит cookie сессии). Защита — секретный **CSRF-токен**, который атакующий не знает.

```
   Браузер с активной сессией (cookie) --► ваш сайт: POST /tasks
        ▲
        +-- вредоносная страница инициирует запрос; cookie уедет автоматически
   Защита: сервер требует CSRF-токен, которого у чужого сайта нет → запрос отклонён
```

| Сценарий | CSRF |
|----------|------|
| Браузер + cookie/сессия + формы | **включён** (по умолчанию) — токен обязателен для POST/PUT/DELETE |
| Stateless REST (JWT в заголовке, без cookie) | можно **отключить** (`csrf.disable`) — атака невозможна без авто-отправки cookie |

```java
// для браузерных форм — НЕ отключать; токен подставляется в форму автоматически (Thymeleaf)
// для stateless REST:
http.csrf(csrf -> csrf.disable());
```

> Правило: cookie-сессия → CSRF нужен; токен в заголовке `Authorization` (JWT) → CSRF не применим, можно отключить. **Не отключайте CSRF «на всякий случай» в session-based приложении.**

---

## Подводные камни

| Проблема | Причина | Решение |
|----------|---------|---------|
| Пароль в открытом виде | нет `PasswordEncoder` | `BCryptPasswordEncoder`, хранить только хеш |
| `no PasswordEncoder mapped for id "null"` | делегирующий энкодер + пароль без префикса | явный BCrypt или префикс `{bcrypt}` |
| `hasRole("ROLE_USER")` не срабатывает | двойной префикс (`hasRole` сам добавляет `ROLE_`) | `hasRole("USER")` или `hasAuthority("ROLE_USER")` |
| POST форм возвращает 403 | session-based + нет CSRF-токена | подставлять токен в форму (не отключать CSRF) |
| REST POST возвращает 403 | CSRF включён, токена нет | для stateless REST — `csrf.disable` |
| Логин-форма у REST-клиента | `formLogin` редиректит (302) | `httpBasic`/JWT для API |

---

## Дополнительные источники

- [Spring Security: In-Memory Authentication](https://docs.spring.io/spring-security/reference/servlet/authentication/passwords/in-memory.html).
- [Spring Security: Password Storage](https://docs.spring.io/spring-security/reference/features/authentication/password-storage.html).
- [Spring Security: CSRF](https://docs.spring.io/spring-security/reference/servlet/exploits/csrf.html).

## Что дальше

В [модуле 95](../m95_spring_security_session_cors/theory.md) — session-based безопасность, HTTP Basic, CORS, cookies и защита загрузки файлов.
