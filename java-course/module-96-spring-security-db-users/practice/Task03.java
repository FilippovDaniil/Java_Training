/**
 * Задача 03 — Модуль 96: роли из БД → GrantedAuthority (префикс ROLE_)
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   spring-boot-starter-web, spring-boot-starter-security, spring-boot-starter-data-jpa, h2.
 *
 * ЗАДАНИЕ:
 *   Пользователь хранит набор ролей; преобразуйте их в authorities с префиксом ROLE_.
 *   1) UserEntity03 содержит @ElementCollection @Enumerated(STRING) Set<Role03> roles
 *      (Role03 — enum {USER, ADMIN}), fetch = EAGER (роли нужны при логине).
 *   2) В CustomUserDetailsService03.loadUserByUsername:
 *        List<GrantedAuthority> auth = u.getRoles().stream()
 *            .map(r -> new SimpleGrantedAuthority("ROLE_" + r.name()))
 *            .collect(Collectors.toList());
 *        return User.withUsername(...).password(...).authorities(auth).build();
 *   3) Засейте alice(USER) и admin(USER, ADMIN). SecurityFilterChain:
 *        "/api/admin/**" hasRole("ADMIN"); остальное authenticated; httpBasic; csrf.disable.
 *   4) Проверьте: admin → /api/admin/stats 200; alice → 403.
 *
 * ЦЕЛЬ: корректно отобразить роли из БД в authorities (ROLE_-префикс — обязателен для hasRole).
 *
 * ПОДСКАЗКА: EAGER для ролей избавит от LazyInitializationException при загрузке UserDetails.
 */
import jakarta.persistence.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;

@SpringBootApplication
public class Task03 {
    public static void main(String[] args) {
        SpringApplication.run(Task03.class, args);
    }

    @Bean
    CommandLineRunner seed(UserRepository03 repo, PasswordEncoder enc) {
        return args -> {
            repo.save(new UserEntity03("alice", enc.encode("password"), Set.of(Role03.USER)));
            repo.save(new UserEntity03("admin", enc.encode("admin"), Set.of(Role03.USER, Role03.ADMIN)));
        };
    }
}

enum Role03 { USER, ADMIN }

@Service
class CustomUserDetailsService03 implements UserDetailsService {
    private final UserRepository03 repo;
    CustomUserDetailsService03(UserRepository03 repo) { this.repo = repo; }

    @Override
    public UserDetails loadUserByUsername(String username) {
        UserEntity03 u = repo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Не найден: " + username));
        // TODO: List<GrantedAuthority> auth = u.getRoles().stream()
        // TODO:     .map(r -> new SimpleGrantedAuthority("ROLE_" + r.name()))
        // TODO:     .collect(Collectors.toList());
        // TODO: return User.withUsername(u.getUsername()).password(u.getPassword()).authorities(auth).build();
        return null;
    }
}

@Configuration
@EnableWebSecurity
class SecurityConfig03 {
    @Bean
    PasswordEncoder passwordEncoder() { return new BCryptPasswordEncoder(); }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // TODO: http.authorizeHttpRequests(auth -> auth
        // TODO:     .requestMatchers("/api/admin/**").hasRole("ADMIN")
        // TODO:     .anyRequest().authenticated());
        // TODO: http.httpBasic(Customizer.withDefaults());
        // TODO: http.csrf(csrf -> csrf.disable());
        return http.build();
    }
}

@RestController
class Controllers03 {
    @GetMapping("/api/tasks") String tasks() { return "Задачи"; }
    @GetMapping("/api/admin/stats") String stats() { return "Статистика (ADMIN)"; }
}

@Entity @Table(name = "users")
class UserEntity03 {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    // TODO: @ElementCollection(fetch = FetchType.EAGER) @Enumerated(EnumType.STRING)
    private Set<Role03> roles = new HashSet<>();
    protected UserEntity03() {}
    public UserEntity03(String username, String password, Set<Role03> roles) {
        this.username = username; this.password = password; this.roles = roles;
    }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public Set<Role03> getRoles() { return roles; }
}

interface UserRepository03 extends JpaRepository<UserEntity03, Long> {
    Optional<UserEntity03> findByUsername(String username);
}
