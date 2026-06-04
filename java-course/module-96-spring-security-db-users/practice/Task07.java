/**
 * Задача 07 — Модуль 96: МИНИ-ПРОЕКТ «Task Tracker с пользователями из БД»
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   spring-boot-starter-web, spring-boot-starter-security, spring-boot-starter-data-jpa,
 *   spring-boot-starter-validation, com.h2database:h2 (см. theory.md).
 *
 * ЦЕЛЬ: перевести аутентификацию Task Tracker с in-memory (модуль 94) на БД: сущность User
 *       с ролями, CustomUserDetailsService, регистрация и стартовый admin.
 *
 * ЗАДАНИЕ (всё в одном файле):
 *
 *   1) UserEntity07: id, username(unique), password(хеш), @ElementCollection EAGER Set<String> roles.
 *      UserRepository07: findByUsername, existsByUsername.
 *
 *   2) CustomUserDetailsService07: loadUserByUsername → User.withUsername(...).password(...)
 *      .authorities(маппинг ролей в "ROLE_"+role).build().
 *
 *   3) RegistrationService07.register(username, rawPassword): хеш пароля, проверка уникальности,
 *      роль "USER" по умолчанию.
 *
 *   4) AuthController07: POST /api/auth/register (@Valid DTO) → 201, UserResponse07 (без пароля);
 *      дубликат → 409.
 *
 *   5) SecurityFilterChain07: "/api/auth/**" permitAll; "/api/admin/**" hasRole("ADMIN");
 *      "/api/**" authenticated; httpBasic; csrf.disable.
 *
 *   6) Стартовый seed (CommandLineRunner): создать admin/admin с ролями {USER, ADMIN},
 *      если его ещё нет.
 *
 *   7) TaskController07: GET /api/tasks (Principal) → "<user>: задачи".
 *      AdminController07: GET /api/admin/users → список username из репозитория (только ADMIN).
 *
 * АРХИТЕКТУРА (точка роста к JWT, модуль 98):
 *   таблица users(+roles) ──CustomUserDetailsService──► DaoAuthenticationProvider
 *   регистрация: /api/auth/register (permitAll, хеш пароля, роль USER)
 *   доступ: /api/admin/** ADMIN · /api/** authenticated · httpBasic (временно)
 *
 * ОЖИДАЕМЫЙ ИТОГ:
 *   - регистрация нового пользователя → 201, затем вход его кредами на /api/tasks → 200;
 *   - admin/admin → доступ к /api/admin/users; обычный пользователь → 403;
 *   - повторная регистрация имени → 409; пароли в БД — BCrypt-хеши.
 *
 * ПОДСКАЗКА: соберите Task02 (UserDetailsService), Task03 (роли), Task04 (регистрация),
 *            Task06 (REST + DTO). httpBasic заменим на JWT в модуле 98.
 */
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;

@SpringBootApplication
public class Task07 {
    public static void main(String[] args) {
        SpringApplication.run(Task07.class, args);
    }

    @Bean
    CommandLineRunner seedAdmin(UserRepository07 repo, PasswordEncoder enc) {
        return args -> {
            // TODO: if (!repo.existsByUsername("admin"))
            // TODO:     repo.save(new UserEntity07("admin", enc.encode("admin"), Set.of("USER", "ADMIN")));
        };
    }
}

record RegisterRequest07(@NotBlank String username, @Size(min = 8) String password) {}
record UserResponse07(Long id, String username) {}

@Service
class CustomUserDetailsService07 implements UserDetailsService {
    private final UserRepository07 repo;
    CustomUserDetailsService07(UserRepository07 repo) { this.repo = repo; }

    @Override
    public UserDetails loadUserByUsername(String username) {
        // TODO: UserEntity07 u = repo.findByUsername(username)
        // TODO:     .orElseThrow(() -> new UsernameNotFoundException("Не найден: " + username));
        // TODO: var auth = u.getRoles().stream().map(r -> new SimpleGrantedAuthority("ROLE_" + r))
        // TODO:     .collect(Collectors.toList());
        // TODO: return User.withUsername(u.getUsername()).password(u.getPassword()).authorities(auth).build();
        return null;
    }
}

@Service
class RegistrationService07 {
    private final UserRepository07 repo;
    private final PasswordEncoder encoder;
    RegistrationService07(UserRepository07 repo, PasswordEncoder encoder) { this.repo = repo; this.encoder = encoder; }

    @Transactional
    public UserEntity07 register(String username, String rawPassword) {
        // TODO: if (repo.existsByUsername(username)) throw new IllegalStateException("Имя занято: " + username);
        // TODO: return repo.save(new UserEntity07(username, encoder.encode(rawPassword), Set.of("USER")));
        return null;
    }
}

@RestController
@RequestMapping("/api/auth")
class AuthController07 {
    private final RegistrationService07 service;
    AuthController07(RegistrationService07 service) { this.service = service; }

    @PostMapping("/register")
    ResponseEntity<UserResponse07> register(@Valid @RequestBody RegisterRequest07 req) {
        // TODO: UserEntity07 u = service.register(req.username(), req.password());
        // TODO: return ResponseEntity.status(HttpStatus.CREATED).body(new UserResponse07(u.getId(), u.getUsername()));
        return null;
    }

    @ExceptionHandler(IllegalStateException.class)
    ResponseEntity<String> duplicate(IllegalStateException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    }
}

@RestController
@RequestMapping("/api/tasks")
class TaskController07 {
    @GetMapping
    String myTasks(Principal principal) {
        // TODO: return principal.getName() + ": задачи";
        return null;
    }
}

@RestController
@RequestMapping("/api/admin")
class AdminController07 {
    private final UserRepository07 repo;
    AdminController07(UserRepository07 repo) { this.repo = repo; }

    @GetMapping("/users")
    List<String> users() {
        // TODO: return repo.findAll().stream().map(UserEntity07::getUsername).toList();
        return List.of();
    }
}

@Configuration
@EnableWebSecurity
class SecurityConfig07 {
    @Bean
    PasswordEncoder passwordEncoder() { return new BCryptPasswordEncoder(); }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // TODO: http.authorizeHttpRequests(auth -> auth
        // TODO:     .requestMatchers("/api/auth/**").permitAll()
        // TODO:     .requestMatchers("/api/admin/**").hasRole("ADMIN")
        // TODO:     .requestMatchers("/api/**").authenticated()
        // TODO:     .anyRequest().authenticated());
        // TODO: http.httpBasic(Customizer.withDefaults());
        // TODO: http.csrf(csrf -> csrf.disable());
        return http.build();
    }
}

@Entity @Table(name = "users")
class UserEntity07 {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> roles = new HashSet<>();
    protected UserEntity07() {}
    public UserEntity07(String username, String password, Set<String> roles) {
        this.username = username; this.password = password; this.roles = roles;
    }
    public Long getId() { return id; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public Set<String> getRoles() { return roles; }
}

interface UserRepository07 extends JpaRepository<UserEntity07, Long> {
    Optional<UserEntity07> findByUsername(String username);
    boolean existsByUsername(String username);
}
