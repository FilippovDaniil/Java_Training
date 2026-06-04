/**
 * Задача 06 — Модуль 94: hasRole vs hasAuthority в правилах доступа
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   spring-boot-starter-web, spring-boot-starter-security (см. theory.md).
 *
 * ЗАДАНИЕ:
 *   Покажите эквивалентность hasRole("X") и hasAuthority("ROLE_X") и роль префикса.
 *   1) Пользователи: alice → roles("USER"); admin → roles("ADMIN").
 *      (roles("USER") даёт authority ROLE_USER.)
 *   2) Правила:
 *        - "/api/user/**"  → hasRole("USER")            // = hasAuthority("ROLE_USER")
 *        - "/api/admin/**" → hasAuthority("ROLE_ADMIN")  // = hasRole("ADMIN")
 *        - остальное authenticated; httpBasic; csrf.disable.
 *   3) Эндпоинты /api/user/area и /api/admin/area.
 *   4) Проверьте соответствие доступа ролям и в комментарии зафиксируйте:
 *        hasRole("USER") ≡ hasAuthority("ROLE_USER"); hasRole сам добавляет префикс ROLE_,
 *        поэтому hasRole("ROLE_USER") — ОШИБКА (двойной префикс ROLE_ROLE_USER).
 *
 * ЦЕЛЬ: не путать hasRole и hasAuthority и понять механику префикса ROLE_.
 *
 * ПОДСКАЗКА: если используете hasAuthority — пишите ПОЛНОЕ имя с ROLE_; если hasRole — без.
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
public class Task06 {
    public static void main(String[] args) {
        SpringApplication.run(Task06.class, args);
    }
}

@Configuration
@EnableWebSecurity
class SecurityConfig06 {
    @Bean
    PasswordEncoder passwordEncoder() { return new BCryptPasswordEncoder(); }

    @Bean
    UserDetailsService users(PasswordEncoder enc) {
        return new InMemoryUserDetailsManager(
                User.withUsername("alice").password(enc.encode("password")).roles("USER").build(),
                User.withUsername("admin").password(enc.encode("admin")).roles("ADMIN").build());
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // TODO: http.authorizeHttpRequests(auth -> auth
        // TODO:     .requestMatchers("/api/user/**").hasRole("USER")
        // TODO:     .requestMatchers("/api/admin/**").hasAuthority("ROLE_ADMIN")
        // TODO:     .anyRequest().authenticated());
        // TODO: http.httpBasic(Customizer.withDefaults());
        // TODO: http.csrf(csrf -> csrf.disable());
        return http.build();
    }
}

@RestController
class Controllers06 {
    @GetMapping("/api/user/area")
    String userArea() { return "Зона USER"; }

    @GetMapping("/api/admin/area")
    String adminArea() { return "Зона ADMIN"; }
}
