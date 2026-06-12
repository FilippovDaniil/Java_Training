package m97_spring_security_authorization.practice.task04;

/**
 * Задача 04 — Модуль 97: ownership-проверка в сервисе (AccessDeniedException → 403)
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   spring-boot-starter-web, spring-boot-starter-security (см. theory.md).
 *
 * ЗАДАНИЕ:
 *   Задача принадлежит пользователю (owner). Менять её может владелец или ADMIN.
 *   1) TaskService04 хранит задачи в Map<Long, String owner> (упрощённо).
 *   2) updateTask(taskId, currentUser, isAdmin):
 *        - найти задачу; если нет → бросить (404-семантика);
 *        - if (!isAdmin && !owner.equals(currentUser)) throw new AccessDeniedException("Не ваша задача");
 *        - иначе «обновить».
 *   3) Контроллер PUT /api/tasks/{id}: достать currentUser и isAdmin из Authentication
 *      (authentication.getName(), проверка наличия ROLE_ADMIN) и вызвать сервис.
 *   4) Проверьте: владелец/ADMIN → 200; чужой пользователь → 403.
 *
 * ЦЕЛЬ: реализовать ownership-контроль явной проверкой в сервисе (гибкий вариант).
 *
 * ПОДСКАЗКА: AccessDeniedException (org.springframework.security.access) → Spring отдаёт 403.
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Task04 {
    public static void main(String[] args) {
        SpringApplication.run(Task04.class, args);
    }
}
