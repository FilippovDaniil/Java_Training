/**
 * Задача 04 — Модуль 93: текущий пользователь из SecurityContextHolder (в сервисе)
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   spring-boot-starter-web, spring-boot-starter-security (см. theory.md).
 *
 * ЗАДАНИЕ:
 *   Иногда личность нужна вне контроллера (в сервисе). Получите её из контекста.
 *   1) CurrentUserService04.currentUsername():
 *        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
 *        return (auth != null && auth.isAuthenticated()) ? auth.getName() : "anonymous";
 *   2) currentAuthorities(): вернуть строку с ролями текущего пользователя
 *        (auth.getAuthorities()).
 *   3) Контроллер GET /api/whoami использует сервис и возвращает имя + роли.
 *   4) Настройте SecurityFilterChain: /api/** authenticated, httpBasic, csrf.disable.
 *
 * ЦЕЛЬ: научиться доставать текущего пользователя из SecurityContext в любом слое.
 *
 * ПОДСКАЗКА: SecurityContext привязан к потоку запроса (ThreadLocal). Всегда проверяйте
 *            auth на null (для permitAll-эндпоинтов он может быть anonymous).
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

public class Task04 {
    public static void main(String[] args) {
        SpringApplication.run(Task04.class, args);
    }
}
