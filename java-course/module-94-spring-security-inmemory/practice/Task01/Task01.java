/**
 * Задача 01 — Модуль 94: InMemoryUserDetailsManager + один пользователь
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   spring-boot-starter-web, spring-boot-starter-security (см. theory.md). bare-javac не компилируется.
 *
 * ЗАДАНИЕ:
 *   1) Объявите бин PasswordEncoder (BCryptPasswordEncoder).
 *   2) Объявите бин UserDetailsService = InMemoryUserDetailsManager с пользователем:
 *        User.withUsername("alice").password(encoder.encode("password")).roles("USER").build();
 *      (пароль ХРАНИМ как хеш — encoder.encode!)
 *   3) SecurityFilterChain: всё authenticated, httpBasic, csrf.disable.
 *   4) Контроллер GET /api/tasks отдаёт список.
 *   5) Проверьте: вход alice/password (Basic) → 200; неверный пароль → 401.
 *
 * ЦЕЛЬ: задать собственного пользователя в памяти вместо сгенерированного.
 *
 * ПОДСКАЗКА: бин UserDetailsService автоматически используется DaoAuthenticationProvider.
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

public class Task01 {
    public static void main(String[] args) {
        SpringApplication.run(Task01.class, args);
    }
}
