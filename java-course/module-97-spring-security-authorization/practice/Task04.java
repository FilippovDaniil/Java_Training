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

@SpringBootApplication
public class Task04 {
    public static void main(String[] args) {
        SpringApplication.run(Task04.class, args);
    }
}

@Service
class TaskService04 {
    // taskId -> owner (засеяно: задача 1 принадлежит alice)
    private final Map<Long, String> owners = new ConcurrentHashMap<>(Map.of(1L, "alice"));

    public String updateTask(Long taskId, String currentUser, boolean isAdmin) {
        // TODO: String owner = owners.get(taskId);
        // TODO: if (owner == null) throw new IllegalArgumentException("Нет задачи " + taskId);
        // TODO: if (!isAdmin && !owner.equals(currentUser))
        // TODO:     throw new AccessDeniedException("Не ваша задача");
        // TODO: return "задача " + taskId + " обновлена пользователем " + currentUser;
        return null;
    }
}

@RestController
@RequestMapping("/api/tasks")
class TaskController04 {
    private final TaskService04 service;
    TaskController04(TaskService04 service) { this.service = service; }

    @PutMapping("/{id}")
    String update(@PathVariable Long id, Authentication auth) {
        // TODO: boolean isAdmin = auth.getAuthorities().stream()
        // TODO:     .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
        // TODO: return service.updateTask(id, auth.getName(), isAdmin);
        return null;
    }
}

@Configuration
@EnableWebSecurity
class SecurityConfig04 {
    @Bean
    PasswordEncoder passwordEncoder() { return new BCryptPasswordEncoder(); }

    @Bean
    UserDetailsService users(PasswordEncoder enc) {
        return new InMemoryUserDetailsManager(
                User.withUsername("alice").password(enc.encode("password")).roles("USER").build(),
                User.withUsername("bob").password(enc.encode("password")).roles("USER").build(),
                User.withUsername("admin").password(enc.encode("admin")).roles("ADMIN", "USER").build());
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // TODO: http.authorizeHttpRequests(auth -> auth.anyRequest().authenticated());
        // TODO: http.httpBasic(Customizer.withDefaults());
        // TODO: http.csrf(csrf -> csrf.disable());
        return http.build();
    }
}
