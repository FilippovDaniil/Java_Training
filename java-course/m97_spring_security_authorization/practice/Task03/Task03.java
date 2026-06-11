package m97_spring_security_authorization.practice.task03;

/**
 * Задача 03 — Модуль 97: @PreAuthorize со SpEL (аргументы + authentication)
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   spring-boot-starter-web, spring-boot-starter-security (см. theory.md).
 *
 * ЗАДАНИЕ:
 *   1) В ProfileService03:
 *        @PreAuthorize("#username == authentication.name or hasRole('ADMIN')")
 *        public String getProfile(String username) {...}
 *      → свой профиль может смотреть любой; чужой — только ADMIN.
 *   2) Контроллер GET /api/profile/{username} делегирует в сервис (#username — это аргумент).
 *   3) @EnableMethodSecurity; пользователи alice(USER), admin(ADMIN); httpBasic; csrf.disable.
 *   4) Проверьте: alice → /api/profile/alice 200, /api/profile/bob 403; admin → любой 200.
 *
 * ЦЕЛЬ: освоить SpEL в @PreAuthorize — доступ к аргументам метода (#arg) и authentication.
 *
 * ПОДСКАЗКА: чтобы #username резолвился по имени, компилируйте с флагом -parameters
 *            (Spring Boot включает его по умолчанию) либо используйте @P("username").
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

public class Task03 {
    public static void main(String[] args) {
        SpringApplication.run(Task03.class, args);
    }
}
