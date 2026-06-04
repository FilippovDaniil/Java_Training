/**
 * Задача 03 — Модуль 94: несколько пользователей с ролями (USER/ADMIN)
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   spring-boot-starter-web, spring-boot-starter-security (см. theory.md).
 *
 * ЗАДАНИЕ:
 *   1) InMemoryUserDetailsManager с двумя пользователями:
 *        - alice / password → roles("USER");
 *        - admin / admin    → roles("ADMIN", "USER").
 *   2) SecurityFilterChain с правилами по ролям:
 *        - "/api/admin/**" → hasRole("ADMIN");
 *        - "/api/**"       → authenticated;
 *        httpBasic, csrf.disable.
 *   3) Контроллеры: GET /api/tasks (любой вошедший), GET /api/admin/stats (только ADMIN).
 *   4) Проверьте: alice → /api/tasks 200, /api/admin/stats 403; admin → оба 200.
 *
 * ЦЕЛЬ: связать роли пользователей с правилами доступа по путям.
 *
 * ПОДСКАЗКА: roles("USER") = authority ROLE_USER; hasRole("USER") добавит ROLE_ сам.
 */
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class Task03 {
    public static void main(String[] args) {
        SpringApplication.run(Task03.class, args);
    }
}

@Configuration
@EnableWebSecurity
class SecurityConfig03 {
    @Bean
    PasswordEncoder passwordEncoder() { return new BCryptPasswordEncoder(); }

    @Bean
    UserDetailsService users(PasswordEncoder enc) {
        // TODO: var alice = User.withUsername("alice").password(enc.encode("password")).roles("USER").build();
        // TODO: var admin = User.withUsername("admin").password(enc.encode("admin")).roles("ADMIN", "USER").build();
        // TODO: return new InMemoryUserDetailsManager(alice, admin);
        return null;
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // TODO: http.authorizeHttpRequests(auth -> auth
        // TODO:     .requestMatchers("/api/admin/**").hasRole("ADMIN")
        // TODO:     .requestMatchers("/api/**").authenticated()
        // TODO:     .anyRequest().authenticated());
        // TODO: http.httpBasic(Customizer.withDefaults());
        // TODO: http.csrf(csrf -> csrf.disable());
        return http.build();
    }
}

@RestController
class Controllers03 {
    @GetMapping("/api/tasks")
    String tasks() { return "Список задач (любой вошедший)"; }

    @GetMapping("/api/admin/stats")
    String stats() { return "Статистика (только ADMIN)"; }
}
