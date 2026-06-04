/**
 * Задача 01 — Модуль 95: HTTP Basic аутентификация для REST
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   spring-boot-starter-web, spring-boot-starter-security (см. theory.md). bare-javac не компилируется.
 *
 * ЗАДАНИЕ:
 *   1) Пользователь alice/password (in-memory, BCrypt).
 *   2) SecurityFilterChain: всё authenticated, http.httpBasic(Customizer.withDefaults()),
 *      csrf.disable.
 *   3) Контроллер GET /api/tasks.
 *   4) Проверьте:
 *        - без заголовка → 401 + заголовок WWW-Authenticate: Basic;
 *        - с заголовком Authorization: Basic base64("alice:password") → 200.
 *   5) В комментарии: Basic шлёт креды в КАЖДОМ запросе, base64 ≠ шифрование → только HTTPS.
 *
 * ЦЕЛЬ: освоить HTTP Basic как простой способ аутентификации REST-клиента.
 *
 * ПОДСКАЗКА: curl -u alice:password http://localhost:8080/api/tasks сам сформирует заголовок.
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

public class Task01 {
    public static void main(String[] args) {
        SpringApplication.run(Task01.class, args);
    }
}
