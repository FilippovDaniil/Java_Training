package m94_spring_security_inmemory.practice.task05;

/**
 * Задача 05 — Модуль 94: CSRF — когда нужен, когда отключать
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   spring-boot-starter-web, spring-boot-starter-security (см. theory.md).
 *
 * ЗАДАНИЕ (две конфигурации — сравнить осознанно):
 *
 *   Вариант A — SESSION-BASED (CSRF ВКЛЮЧЁН, по умолчанию):
 *     1) formLogin + НЕ отключать csrf.
 *     2) POST /api/tasks без CSRF-токена → 403 (защита сработала).
 *        Через браузерную форму с токеном — проходит.
 *
 *   Вариант B — STATELESS REST (CSRF отключён осознанно):
 *     3) httpBasic + http.csrf(csrf -> csrf.disable()).
 *     4) POST /api/tasks с Basic-кредами → 200 (cookie-сессии нет → CSRF не применим).
 *
 *   Реализуйте ОДИН из вариантов в SecurityConfig05 (закомментируйте альтернативу),
 *   в комментарии объясните разницу.
 *
 * ЦЕЛЬ: понять правило: cookie-сессия → CSRF нужен; токен в заголовке/Basic без cookie →
 *       CSRF можно отключить. НЕ отключать CSRF в session-based приложении «на всякий случай».
 *
 * ПОДСКАЗКА: 403 на POST в session-based — почти всегда отсутствие CSRF-токена, а не прав.
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

public class Task05 {
    public static void main(String[] args) {
        SpringApplication.run(Task05.class, args);
    }
}
