# Модуль 96. Spring Security: пользователи из базы данных, CustomUserDetailsService, регистрация

In-memory пользователи ([модуль 94](../m94_spring_security_inmemory/theory.md)) годятся для демо. В реальном приложении пользователи хранятся в **БД**, регистрируются через API и имеют роли. Здесь — сущность `User`, `CustomUserDetailsService` и эндпоинт регистрации.

> Практика — задачи в `practice/`. Зависимости: `spring-boot-starter-web`, `spring-boot-starter-security`, `spring-boot-starter-data-jpa`, `com.h2database:h2`. Реалистичные импорты + «ТРЕБУЮТСЯ ЗАВИСИМОСТИ»; bare-javac не компилируется (норма). Сквозной проект — **Task Tracker API**.

---

## UserDetailsService — мост между БД и Security

Spring Security не знает про вашу таблицу. Он спрашивает у `UserDetailsService`: «дай пользователя по имени». Ваша задача — загрузить его из БД и вернуть `UserDetails`.

```
   логин-пароль  ──►  DaoAuthenticationProvider
                          │ loadUserByUsername("alice")
                          ▼
                  CustomUserDetailsService → UserRepository → таблица users
                          │
                          ▼ UserDetails (хеш пароля + authorities)
                  сравнение пароля через PasswordEncoder.matches
```

---

## Сущность User и роли

```java
@Entity @Table(name = "users")
public class UserEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;          // ХЕШ (BCrypt), не сырой пароль!

    @ElementCollection(fetch = FetchType.EAGER)     // простой вариант: роли как строки
    @Enumerated(EnumType.STRING)
    private Set<Role> roles = new HashSet<>();       // {USER, ADMIN}

    private boolean enabled = true;
}

enum Role { USER, ADMIN }
```

> Роли можно хранить как `@ElementCollection` (просто) или отдельной сущностью `Role` со связью `@ManyToMany` (гибче). Для учебного проекта достаточно enum-набора.

---

## CustomUserDetailsService

```java
import org.springframework.security.core.userdetails.*;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository repo;
    public CustomUserDetailsService(UserRepository repo) { this.repo = repo; }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity u = repo.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("Не найден: " + username));
        return User.withUsername(u.getUsername())
            .password(u.getPassword())                       // уже хеш из БД
            .authorities(toAuthorities(u.getRoles()))         // ROLE_USER, ROLE_ADMIN
            .disabled(!u.isEnabled())
            .build();
    }

    private List<GrantedAuthority> toAuthorities(Set<Role> roles) {
        return roles.stream()
            .map(r -> new SimpleGrantedAuthority("ROLE_" + r.name()))   // префикс ROLE_!
            .collect(Collectors.toList());
    }
}
```

| Шаг | Деталь |
|-----|--------|
| `loadUserByUsername` | найти в БД или бросить `UsernameNotFoundException` |
| вернуть `UserDetails` | с хешем пароля и authorities |
| `ROLE_` префикс | роли → authorities `ROLE_XXX` (чтобы работал `hasRole`) |

> Достаточно объявить бин `UserDetailsService` + `PasswordEncoder` — Spring Boot сам соберёт `DaoAuthenticationProvider`. Отдельно его конфигурировать обычно не нужно.

---

## Регистрация пользователя

Регистрация = создать пользователя с **захешированным** паролем.

```java
@Service
public class RegistrationService {
    private final UserRepository repo;
    private final PasswordEncoder encoder;
    // ...
    @Transactional
    public UserEntity register(String username, String rawPassword) {
        if (repo.existsByUsername(username))
            throw new IllegalArgumentException("Имя занято: " + username);   // → 409 Conflict
        UserEntity u = new UserEntity();
        u.setUsername(username);
        u.setPassword(encoder.encode(rawPassword));     // ХЕШИРУЕМ перед сохранением!
        u.setRoles(Set.of(Role.USER));                   // роль по умолчанию
        return repo.save(u);
    }
}
```

```java
@PostMapping("/api/auth/register")     // публичный эндпоинт (permitAll)
public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest req) { ... }
```

| Правило регистрации | Почему |
|---------------------|--------|
| хешировать пароль (`encoder.encode`) | в БД — только хеш |
| проверять уникальность username | иначе конфликт/перезапись → 409 |
| роль по умолчанию `USER` | новый пользователь не должен быть ADMIN |
| `/api/auth/register` → `permitAll` | регистрироваться можно без входа |

---

## Сборка: SecurityFilterChain

```java
http.authorizeHttpRequests(auth -> auth
        .requestMatchers("/api/auth/**").permitAll()        // регистрация/логин — открыто
        .requestMatchers("/api/admin/**").hasRole("ADMIN")
        .anyRequest().authenticated())
    .httpBasic(Customizer.withDefaults())
    .csrf(csrf -> csrf.disable());
```

---

## Подводные камни

| Проблема | Причина | Решение |
|----------|---------|---------|
| Вход не проходит при верном пароле | в БД сырой пароль, а не хеш | хешировать при регистрации (`encode`) |
| `hasRole("ADMIN")` не срабатывает | authority без префикса `ROLE_` | добавлять `ROLE_` при маппинге ролей |
| `UsernameNotFoundException` глотается | по умолчанию маскируется как BadCredentials | это норма (не раскрывать, существует ли юзер) |
| Роли = null после загрузки | `LazyInitializationException` (роли lazy) | `FetchType.EAGER` для ролей / загрузка в транзакции |
| Дубли username | нет проверки/уникального индекса | `@Column(unique=true)` + `existsByUsername` |
| Регистрация требует входа | `/api/auth/**` не в `permitAll` | добавить в permitAll |
| Пароль в логах/ответе | возвращают сущность с паролем | DTO без поля password |

---

## Дополнительные источники

- [Spring Security: UserDetailsService](https://docs.spring.io/spring-security/reference/servlet/authentication/passwords/user-details-service.html).
- [Spring Security: DaoAuthenticationProvider](https://docs.spring.io/spring-security/reference/servlet/authentication/passwords/dao-authentication-provider.html).
- [модуль 77](../m77_spring_data_jpa_intro/theory.md) — JpaRepository и сущности (основа для UserRepository).

## Что дальше

В [модуле 97](../m97_spring_security_authorization/theory.md) — авторизация: правила на уровне запросов и методов (`@PreAuthorize`), доступ на основе владения ресурсом (ownership).
