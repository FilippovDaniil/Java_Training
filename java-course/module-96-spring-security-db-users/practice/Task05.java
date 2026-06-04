/**
 * Задача 05 — Модуль 96: роли отдельной сущностью (@ManyToMany) — гибкая модель
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   spring-boot-starter-web, spring-boot-starter-security, spring-boot-starter-data-jpa, h2.
 *
 * ЗАДАНИЕ:
 *   Вместо enum-набора сделайте роли ОТДЕЛЬНОЙ сущностью (как в реальных проектах).
 *   1) RoleEntity05 (@Entity): id + @Column(unique=true) name ("ROLE_USER", "ROLE_ADMIN").
 *   2) UserEntity05: @ManyToMany(fetch = EAGER) Set<RoleEntity05> roles
 *      (join-таблица users_roles).
 *   3) CustomUserDetailsService05.loadUserByUsername: маппить u.getRoles() в
 *      SimpleGrantedAuthority по role.getName() (имя УЖE с префиксом ROLE_).
 *   4) Засейте роли ROLE_USER/ROLE_ADMIN, пользователя admin с обеими; проверьте доступ
 *      к /api/admin/** (hasRole("ADMIN")).
 *
 * ЦЕЛЬ: освоить нормализованную модель ролей (@ManyToMany) — расширяемую и переиспользуемую.
 *
 * ПОДСКАЗКА: если имя роли уже "ROLE_ADMIN" — НЕ добавляйте префикс повторно; используйте
 *            new SimpleGrantedAuthority(role.getName()).
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
public class Task05 {
    public static void main(String[] args) {
        SpringApplication.run(Task05.class, args);
    }

    @Bean
    CommandLineRunner seed(UserRepository05 users, RoleRepository05 roles, PasswordEncoder enc) {
        return args -> {
            RoleEntity05 user = roles.save(new RoleEntity05("ROLE_USER"));
            RoleEntity05 admin = roles.save(new RoleEntity05("ROLE_ADMIN"));
            users.save(new UserEntity05("admin", enc.encode("admin"), Set.of(user, admin)));
        };
    }
}

@Service
class CustomUserDetailsService05 implements UserDetailsService {
    private final UserRepository05 repo;
    CustomUserDetailsService05(UserRepository05 repo) { this.repo = repo; }

    @Override
    public UserDetails loadUserByUsername(String username) {
        UserEntity05 u = repo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Не найден: " + username));
        // TODO: var auth = u.getRoles().stream()
        // TODO:     .map(r -> new SimpleGrantedAuthority(r.getName()))   // имя уже с ROLE_
        // TODO:     .collect(Collectors.toList());
        // TODO: return User.withUsername(u.getUsername()).password(u.getPassword()).authorities(auth).build();
        return null;
    }
}

@Configuration
@EnableWebSecurity
class SecurityConfig05 {
    @Bean
    PasswordEncoder passwordEncoder() { return new BCryptPasswordEncoder(); }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // TODO: http.authorizeHttpRequests(auth -> auth
        // TODO:     .requestMatchers("/api/admin/**").hasRole("ADMIN").anyRequest().authenticated());
        // TODO: http.httpBasic(Customizer.withDefaults());
        // TODO: http.csrf(csrf -> csrf.disable());
        return http.build();
    }
}

@RestController
class Controllers05 {
    @GetMapping("/api/admin/stats") String stats() { return "Статистика (ADMIN)"; }
}

@Entity @Table(name = "roles")
class RoleEntity05 {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String name;
    protected RoleEntity05() {}
    public RoleEntity05(String name) { this.name = name; }
    public String getName() { return name; }
}

@Entity @Table(name = "users")
class UserEntity05 {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    // TODO: @ManyToMany(fetch = FetchType.EAGER)
    private Set<RoleEntity05> roles = new HashSet<>();
    protected UserEntity05() {}
    public UserEntity05(String username, String password, Set<RoleEntity05> roles) {
        this.username = username; this.password = password; this.roles = roles;
    }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public Set<RoleEntity05> getRoles() { return roles; }
}

interface UserRepository05 extends JpaRepository<UserEntity05, Long> {
    Optional<UserEntity05> findByUsername(String username);
}
interface RoleRepository05 extends JpaRepository<RoleEntity05, Long> {}
