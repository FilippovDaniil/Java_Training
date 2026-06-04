/**
 * Задача 02 — Модуль 96: CustomUserDetailsService (загрузка пользователя из БД)
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   spring-boot-starter-web, spring-boot-starter-security, spring-boot-starter-data-jpa, h2.
 *
 * ЗАДАНИЕ:
 *   1) Реализуйте CustomUserDetailsService02 implements UserDetailsService:
 *        loadUserByUsername(username):
 *          UserEntity02 u = repo.findByUsername(username)
 *              .orElseThrow(() -> new UsernameNotFoundException("Не найден: " + username));
 *          return User.withUsername(u.getUsername())
 *              .password(u.getPassword())
 *              .authorities("ROLE_USER")           // роли разберём в Task03/05
 *              .disabled(!u.isEnabled())
 *              .build();
 *   2) Бин PasswordEncoder (BCrypt). При старте засейте одного пользователя
 *      (password = encoder.encode("password")).
 *   3) SecurityFilterChain: всё authenticated, httpBasic, csrf.disable.
 *   4) Проверьте: вход alice/password (Basic) на GET /api/tasks → 200; неверный пароль → 401.
 *
 * ЦЕЛЬ: связать таблицу users с механизмом аутентификации через UserDetailsService.
 *
 * ПОДСКАЗКА: достаточно бинов UserDetailsService + PasswordEncoder — Spring сам соберёт
 *            DaoAuthenticationProvider.
 */
import jakarta.persistence.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@SpringBootApplication
public class Task02 {
    public static void main(String[] args) {
        SpringApplication.run(Task02.class, args);
    }

    @Bean
    CommandLineRunner seed(UserRepository02 repo, PasswordEncoder enc) {
        return args -> repo.save(new UserEntity02("alice", enc.encode("password")));
    }
}

@Service
class CustomUserDetailsService02 implements UserDetailsService {
    private final UserRepository02 repo;
    CustomUserDetailsService02(UserRepository02 repo) { this.repo = repo; }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // TODO: UserEntity02 u = repo.findByUsername(username)
        // TODO:     .orElseThrow(() -> new UsernameNotFoundException("Не найден: " + username));
        // TODO: return User.withUsername(u.getUsername()).password(u.getPassword())
        // TODO:     .authorities("ROLE_USER").disabled(!u.isEnabled()).build();
        return null;
    }
}

@Configuration
@EnableWebSecurity
class SecurityConfig02 {
    @Bean
    PasswordEncoder passwordEncoder() { return new BCryptPasswordEncoder(); }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // TODO: http.authorizeHttpRequests(auth -> auth.anyRequest().authenticated());
        // TODO: http.httpBasic(Customizer.withDefaults());
        // TODO: http.csrf(csrf -> csrf.disable());
        return http.build();
    }
}

@RestController
class TaskController02 {
    @GetMapping("/api/tasks")
    String tasks() { return "Задачи (пользователь из БД)"; }
}

@Entity @Table(name = "users")
class UserEntity02 {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    private boolean enabled = true;
    protected UserEntity02() {}
    public UserEntity02(String username, String password) { this.username = username; this.password = password; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public boolean isEnabled() { return enabled; }
}

interface UserRepository02 extends JpaRepository<UserEntity02, Long> {
    Optional<UserEntity02> findByUsername(String username);
}
