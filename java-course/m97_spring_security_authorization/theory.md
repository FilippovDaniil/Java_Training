# Модуль 97. Spring Security: авторизация — request-level, method security, ownership

Аутентификация отвечает «кто ты», авторизация — «что тебе можно». Spring Security даёт три уровня контроля: правила по **URL** (request-level), аннотации на **методах** (`@PreAuthorize`) и проверки **владения ресурсом** (ownership: «можно менять только свои задачи»).

> Практика — задачи в `practice/`. Зависимости: `spring-boot-starter-web`, `spring-boot-starter-security`, `spring-boot-starter-data-jpa`, `com.h2database:h2`. Реалистичные импорты + «ТРЕБУЮТСЯ ЗАВИСИМОСТИ»; bare-javac не компилируется (норма). Сквозной проект — **Task Tracker API**.

---

## Request-level: правила по URL и методу

Уже знакомо по `authorizeHttpRequests`, но можно различать **HTTP-методы**:

```java
http.authorizeHttpRequests(auth -> auth
    .requestMatchers(HttpMethod.GET, "/api/tasks/**").hasAnyRole("USER", "ADMIN")  // читать — всем вошедшим
    .requestMatchers(HttpMethod.POST, "/api/tasks").hasAnyRole("USER", "ADMIN")    // создавать — всем
    .requestMatchers(HttpMethod.DELETE, "/api/tasks/**").hasRole("ADMIN")          // удалять — только ADMIN
    .requestMatchers("/api/admin/**").hasRole("ADMIN")
    .anyRequest().authenticated());
```

| Метод DSL | Смысл |
|-----------|-------|
| `hasRole("X")` | одна роль |
| `hasAnyRole("X","Y")` | любая из ролей |
| `hasAuthority("PERM")` | конкретное право |
| `requestMatchers(HttpMethod.GET, "...")` | правило только для метода |

> Request-level хорош для грубых правил «по разделам». Тонкие правила «по конкретному ресурсу» лучше делать method security + ownership.

---

## Method security: @PreAuthorize

Включается аннотацией на конфигурации:

```java
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@Configuration
@EnableMethodSecurity            // включает @PreAuthorize/@PostAuthorize (prePostEnabled=true по умолч.)
public class MethodSecurityConfig { }
```

Затем — аннотации на методах сервиса/контроллера:

```java
@PreAuthorize("hasRole('ADMIN')")             // проверка ДО вызова метода
public void deleteTask(Long id) { ... }

@PreAuthorize("isAuthenticated()")
public List<Task> myTasks() { ... }
```

| Аннотация | Когда срабатывает |
|-----------|-------------------|
| `@PreAuthorize` | **до** выполнения метода (по аргументам и пользователю) |
| `@PostAuthorize` | **после** (по возвращённому значению) |
| `@Secured("ROLE_ADMIN")` | простая проверка роли (без SpEL) |
| `@PreFilter` / `@PostFilter` | фильтрация входной/выходной коллекции |

> `@PreAuthorize` мощнее `@Secured`: внутри — **SpEL** (можно ссылаться на аргументы, `authentication`, вызывать бины).

---

## SpEL в @PreAuthorize

```java
// доступ к аргументам метода через #имя:
@PreAuthorize("#username == authentication.name")             // только свой профиль
public User getProfile(String username) { ... }

@PreAuthorize("hasRole('ADMIN') or #task.ownerId == authentication.name")
public void update(Task task) { ... }
```

| Выражение | Смысл |
|-----------|-------|
| `authentication` | текущий `Authentication` |
| `authentication.name` | имя пользователя |
| `#arg` | аргумент метода по имени |
| `hasRole(...)`, `hasAuthority(...)` | проверки прав |
| `principal` | сам `UserDetails` |

---

## Ownership-based: «только свои ресурсы»

Частое требование: обычный пользователь меняет/удаляет **только свои** задачи, ADMIN — любые. Два подхода:

### A. Проверка в сервисе (явно, гибко)

```java
@Transactional
public void deleteTask(Long taskId, String currentUser, boolean isAdmin) {
    Task task = repo.findById(taskId).orElseThrow();
    if (!isAdmin && !task.getOwner().equals(currentUser))
        throw new AccessDeniedException("Не ваша задача");   // → 403
    repo.delete(task);
}
```

### B. @PreAuthorize с вызовом бина-проверки

```java
@PreAuthorize("hasRole('ADMIN') or @taskSecurity.isOwner(#id, authentication.name)")
public void delete(Long id) { ... }

@Component("taskSecurity")
public class TaskSecurity {
    public boolean isOwner(Long taskId, String username) {
        return repo.findById(taskId).map(t -> t.getOwner().equals(username)).orElse(false);
    }
}
```

```
   запрос DELETE /api/tasks/5
        |
   @PreAuthorize: ADMIN? --да--► разрешить
        | нет
   @taskSecurity.isOwner(5, "alice")? --да--► разрешить, нет--► 403
```

> `AccessDeniedException` → **403 Forbidden** (вошёл, но прав нет), в отличие от 401 (не вошёл).

---

## @PostFilter / @PreFilter — фильтрация коллекций

Вернуть из списка только то, что доступно пользователю:

```java
@PostFilter("filterObject.owner == authentication.name or hasRole('ADMIN')")
public List<Task> findAll() { return repo.findAll(); }   // вернёт только свои (или все для ADMIN)
```

> `@PostFilter` отсеивает элементы **после** выполнения — для больших выборок неэффективно (грузит всё, потом фильтрует). Лучше фильтровать **в запросе** (`findByOwner`). Используйте `@PostFilter` точечно.

---

## Подводные камни

| Проблема | Причина | Решение |
|----------|---------|---------|
| `@PreAuthorize` игнорируется | нет `@EnableMethodSecurity` | добавить аннотацию на конфиг |
| 401 vs 403 путают | 401 — не вошёл, 403 — нет прав | `AccessDeniedException` → 403 |
| ownership не проверяется | только request-level правила | проверка в сервисе или `@PreAuthorize` с бином |
| `#arg` не резолвится | параметры скомпилированы без имён | `-parameters` при компиляции / `@P("id")` |
| `@PostFilter` тормозит | грузит всё, потом фильтрует | фильтровать в запросе (`findByOwner`) |
| method security «не видит» self-invocation | вызов изнутри класса минует прокси (как `@Transactional`) | вызывать через бин ([модуль 81](../m81_spring_data_jpa_transactions/theory.md)) |
| `@Secured` не понимает SpEL | это не его синтаксис | использовать `@PreAuthorize` |

---

## Дополнительные источники

- [Spring Security: Method Security](https://docs.spring.io/spring-security/reference/servlet/authorization/method-security.html).
- [Spring Security: Authorize HTTP Requests](https://docs.spring.io/spring-security/reference/servlet/authorization/authorize-http-requests.html).
- [модуль 72](../m72_spring_rest_error_handling/theory.md) — единый контракт ошибок (как отдавать 403).

## Что дальше

В [модуле 98](../m98_spring_security_jwt/theory.md) — переход к **stateless**: JWT-токены, их выдача и валидация (вместо сессий и HTTP Basic). Это завершит переход Task Tracker к токенной аутентификации.
