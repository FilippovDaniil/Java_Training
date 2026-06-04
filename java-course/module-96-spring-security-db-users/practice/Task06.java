/**
 * Задача 06 — Модуль 96: REST-эндпоинт регистрации с валидацией и DTO
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   spring-boot-starter-web, spring-boot-starter-security, spring-boot-starter-data-jpa,
 *   spring-boot-starter-validation, h2.
 *
 * ЗАДАНИЕ:
 *   1) DTO запроса RegisterRequest06 (record): @NotBlank username, @Size(min=8) password.
 *   2) DTO ответа UserResponse06 (record): id + username (БЕЗ пароля!).
 *   3) AuthController06: POST /api/auth/register, @Valid @RequestBody RegisterRequest06:
 *        - вызвать RegistrationService06.register(...);
 *        - вернуть ResponseEntity со статусом 201 и UserResponse06.
 *      Дубликат username → 409 Conflict (@ExceptionHandler на IllegalStateException).
 *   4) SecurityFilterChain: "/api/auth/**" permitAll, остальное authenticated, httpBasic, csrf.disable.
 *   5) Проверьте: POST валидного → 201; короткий пароль → 400; дубликат → 409.
 *
 * ЦЕЛЬ: оформить регистрацию как публичный REST-эндпоинт с валидацией и без утечки пароля.
 *
 * ПОДСКАЗКА: НИКОГДА не возвращайте сущность с полем password — используйте DTO ответа.
 */
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@SpringBootApplication
public class Task06 {
    public static void main(String[] args) {
        SpringApplication.run(Task06.class, args);
    }
}

record RegisterRequest06(@NotBlank String username, @Size(min = 8) String password) {}
record UserResponse06(Long id, String username) {}

@RestController
@RequestMapping("/api/auth")
class AuthController06 {
    private final RegistrationService06 service;
    AuthController06(RegistrationService06 service) { this.service = service; }

    @PostMapping("/register")
    ResponseEntity<UserResponse06> register(@Valid @RequestBody RegisterRequest06 req) {
        // TODO: UserEntity06 u = service.register(req.username(), req.password());
        // TODO: return ResponseEntity.status(HttpStatus.CREATED)
        // TODO:     .body(new UserResponse06(u.getId(), u.getUsername()));
        return null;
    }

    @ExceptionHandler(IllegalStateException.class)
    ResponseEntity<String> duplicate(IllegalStateException e) {
        // TODO: return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        return null;
    }
}

@Service
class RegistrationService06 {
    private final UserRepository06 repo;
    private final PasswordEncoder encoder;
    RegistrationService06(UserRepository06 repo, PasswordEncoder encoder) { this.repo = repo; this.encoder = encoder; }

    @Transactional
    public UserEntity06 register(String username, String rawPassword) {
        // TODO: if (repo.existsByUsername(username)) throw new IllegalStateException("Имя занято: " + username);
        // TODO: return repo.save(new UserEntity06(username, encoder.encode(rawPassword)));
        return null;
    }
}

@Configuration
@EnableWebSecurity
class SecurityConfig06 {
    @Bean
    PasswordEncoder passwordEncoder() { return new BCryptPasswordEncoder(); }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // TODO: http.authorizeHttpRequests(auth -> auth
        // TODO:     .requestMatchers("/api/auth/**").permitAll().anyRequest().authenticated());
        // TODO: http.httpBasic(Customizer.withDefaults());
        // TODO: http.csrf(csrf -> csrf.disable());
        return http.build();
    }
}

@Entity @Table(name = "users")
class UserEntity06 {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    protected UserEntity06() {}
    public UserEntity06(String username, String password) { this.username = username; this.password = password; }
    public Long getId() { return id; }
    public String getUsername() { return username; }
}

interface UserRepository06 extends JpaRepository<UserEntity06, Long> {
    Optional<UserEntity06> findByUsername(String username);
    boolean existsByUsername(String username);
}
