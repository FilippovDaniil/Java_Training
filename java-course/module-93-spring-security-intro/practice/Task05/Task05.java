/**
 * Задача 05 — Модуль 93: внедрение Principal / @AuthenticationPrincipal в контроллер
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   spring-boot-starter-web, spring-boot-starter-security (см. theory.md).
 *
 * ЗАДАНИЕ:
 *   Spring умеет внедрять текущего пользователя в аргументы метода контроллера.
 *   Реализуйте три эндпоинта, возвращающих имя пользователя ТРЕМЯ способами:
 *     1) GET /api/me/principal  — параметр java.security.Principal principal → principal.getName()
 *     2) GET /api/me/auth       — параметр Authentication auth → auth.getName()
 *     3) GET /api/me/details    — @AuthenticationPrincipal UserDetails user → user.getUsername()
 *   Настройте SecurityFilterChain: всё authenticated, httpBasic, csrf.disable.
 *
 * ЦЕЛЬ: освоить идиоматичные способы получить текущего пользователя в контроллере.
 *
 * ПОДСКАЗКА: Principal — стандартный JDK-интерфейс; Authentication — богаче (роли);
 *            @AuthenticationPrincipal даёт сам UserDetails (детали аккаунта).
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.security.Principal;

public class Task05 {
    public static void main(String[] args) {
        SpringApplication.run(Task05.class, args);
    }
}
