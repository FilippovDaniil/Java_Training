# Модуль 95. Spring Security: сессии, HTTP Basic, CORS, cookies, загрузка файлов

Аутентификация может быть **stateful** (сервер хранит сессию, браузер шлёт cookie) или **stateless** (каждый запрос самодостаточен). Здесь разберём session-based подход, HTTP Basic для API, **CORS** (доступ из браузерного фронтенда на другом домене), безопасные cookies и защищённую загрузку файлов.

> Практика — задачи в `practice/`. Зависимости: `spring-boot-starter-web`, `spring-boot-starter-security`. Реалистичные импорты + «ТРЕБУЮТСЯ ЗАВИСИМОСТИ»; bare-javac не компилируется (норма). Сквозной проект — **Task Tracker API**.

---

## Session-based vs stateless

```
   SESSION-BASED (stateful)              STATELESS (JWT, модуль 98)
   ─────────────────────────            ──────────────────────────
   вход → сервер создаёт сессию         каждый запрос несёт токен
   браузер хранит JSESSIONID (cookie)   состояние НЕ хранится на сервере
   сервер помнит, кто вошёл             токен самодостаточен
   масштабирование сложнее (sticky)     масштабируется легко
```

`SessionCreationPolicy` управляет тем, создаёт ли Spring Security сессию:

```java
import org.springframework.security.config.http.SessionCreationPolicy;

http.sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED));
```

| Политика | Поведение |
|----------|-----------|
| `IF_REQUIRED` (по умолч.) | сессия создаётся при необходимости (form login) |
| `ALWAYS` | всегда |
| `NEVER` | не создаёт, но использует существующую |
| `STATELESS` | **никаких сессий** — для JWT/REST ([модуль 98](../m98_spring_security_jwt/theory.md)) |

---

## HTTP Basic

Простейшая аутентификация для API: логин-пароль в заголовке `Authorization: Basic base64(user:pass)`.

```java
http.httpBasic(org.springframework.security.config.Customizer.withDefaults());
```

```
   запрос без креда → 401 + заголовок WWW-Authenticate: Basic
   запрос с Authorization: Basic YWxpY2U6cGFzcw== → аутентифицирован
```

> Basic передаёт креды в КАЖДОМ запросе (base64 — это НЕ шифрование!). Использовать только поверх HTTPS. Для публичных API предпочтительнее токены (JWT).

---

## Управление сессиями: фиксация и конкурентность

```java
http.sessionManagement(sm -> sm
    .sessionFixation(sf -> sf.changeSessionId())   // защита от session fixation (по умолчанию)
    .maximumSessions(1)                            // не более 1 активной сессии на пользователя
    .maxSessionsPreventsLogin(false)               // новый вход вытесняет старый
);
```

| Защита | Зачем |
|--------|-------|
| session fixation (`changeSessionId`) | после входа меняется id сессии — атакующий не подсунет свой |
| `maximumSessions(N)` | ограничить число параллельных входов |

---

## CORS — доступ из браузерного фронтенда

Браузер блокирует запросы к другому **origin** (домен/порт/схема) — это Same-Origin Policy. **CORS** разрешает их явно. Нужен, когда SPA (`http://localhost:3000`) ходит к API (`http://localhost:8080`).

```
   localhost:3000 (React)  ──preflight OPTIONS──►  localhost:8080 (API)
        │                                              │
        │   ◄── Access-Control-Allow-Origin: ... ──────┘
        └── если origin разрешён → основной запрос проходит
```

```java
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Bean
public CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration cfg = new CorsConfiguration();
    cfg.setAllowedOrigins(java.util.List.of("http://localhost:3000"));  // конкретные origin'ы
    cfg.setAllowedMethods(java.util.List.of("GET", "POST", "PUT", "DELETE"));
    cfg.setAllowedHeaders(java.util.List.of("*"));
    cfg.setAllowCredentials(true);                                       // если шлём cookie
    UrlBasedCorsConfigurationSource src = new UrlBasedCorsConfigurationSource();
    src.registerCorsConfiguration("/**", cfg);
    return src;
}

// и подключить в цепочке:
http.cors(org.springframework.security.config.Customizer.withDefaults());  // подхватит бин выше
```

| Параметр | Смысл |
|----------|-------|
| `allowedOrigins` | какие домены могут обращаться (НЕ `*` вместе с `allowCredentials=true`!) |
| `allowedMethods` | разрешённые HTTP-методы |
| `allowCredentials(true)` | разрешить cookie/Authorization (тогда origin должен быть конкретным) |
| preflight `OPTIONS` | браузер сам шлёт перед «непростым» запросом |

> ❌ `allowedOrigins("*")` + `allowCredentials(true)` — нерабочая и небезопасная комбинация. Указывайте конкретные origin'ы.

---

## Безопасные cookies

Cookie сессии должны быть защищены:

```properties
server.servlet.session.cookie.http-only=true   # JS не читает cookie (защита от XSS-кражи)
server.servlet.session.cookie.secure=true       # только по HTTPS
server.servlet.session.cookie.same-site=strict  # не отправлять с чужих сайтов (анти-CSRF)
```

| Атрибут | Защита |
|---------|--------|
| `HttpOnly` | cookie недоступна из JavaScript (XSS не украдёт) |
| `Secure` | передаётся только по HTTPS |
| `SameSite=Strict/Lax` | не уходит при кросс-сайтовых запросах (анти-CSRF) |

---

## Защита загрузки файлов

Загрузка файлов — частый вектор атак. Минимум:

```properties
spring.servlet.multipart.max-file-size=5MB       # лимит размера файла
spring.servlet.multipart.max-request-size=10MB    # лимит всего запроса
```

```java
@PostMapping("/api/tasks/{id}/attachments")
public String upload(@RequestParam("file") MultipartFile file) {
    // TODO: проверить тип (file.getContentType()), расширение, размер;
    //       НЕ доверять оригинальному имени файла (path traversal!);
    //       сохранять под сгенерированным именем вне веб-корня.
}
```

| Риск | Защита |
|------|--------|
| огромный файл (DoS) | лимиты `max-file-size`/`max-request-size` |
| опасный тип (exe, скрипт) | белый список content-type/расширений |
| path traversal (`../../`) | не использовать оригинальное имя; генерировать своё |
| исполняемое содержимое | хранить вне веб-корня, не отдавать как статику |

---

## Подводные камни

| Проблема | Причина | Решение |
|----------|---------|---------|
| CORS-ошибка в браузере | origin не разрешён | `CorsConfigurationSource` + `http.cors()` |
| CORS «не работает» при cookie | `*` + credentials | конкретные `allowedOrigins` + `allowCredentials(true)` |
| preflight OPTIONS → 401 | security блокирует OPTIONS | CORS до авторизации (`http.cors()`), не блокировать preflight |
| cookie крадётся через XSS | нет `HttpOnly` | `cookie.http-only=true` |
| Basic «не защищён» | base64 не шифрует | только поверх HTTPS / перейти на JWT |
| `MaxUploadSizeExceededException` | превышен лимит | поднять лимит осознанно или вернуть 413 |
| path traversal при загрузке | доверяют имени файла | генерировать имя, валидировать |

---

## Дополнительные источники

- [Spring Security: CORS](https://docs.spring.io/spring-security/reference/servlet/integrations/cors.html).
- [Spring Security: Session Management](https://docs.spring.io/spring-security/reference/servlet/authentication/session-management.html).
- [OWASP: File Upload Cheat Sheet](https://cheatsheetseries.owasp.org/cheatsheets/File_Upload_Cheat_Sheet.html).
- [модуль 74](../m74_spring_rest_crud_file/theory.md) — загрузка/выгрузка файлов в REST.

## Что дальше

В [модуле 96](../m96_spring_security_db_users/theory.md) — пользователи из базы данных: `CustomUserDetailsService`, сущность `User` с ролями и эндпоинт регистрации.
