/**
 * Задача 05 — Модуль 97: ownership через @PreAuthorize + бин-проверку
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   spring-boot-starter-web, spring-boot-starter-security (см. theory.md).
 *
 * ЗАДАНИЕ:
 *   Вынесите ownership-логику в отдельный бин и вызовите его из @PreAuthorize (декларативно).
 *   1) Компонент @Component("taskSecurity") TaskSecurity05 с методом
 *        public boolean isOwner(Long taskId, String username) { ... } (вернуть owner.equals(username)).
 *   2) В TaskService05.deleteTask(Long id):
 *        @PreAuthorize("hasRole('ADMIN') or @taskSecurity.isOwner(#id, authentication.name)")
 *   3) @EnableMethodSecurity; контроллер DELETE /api/tasks/{id} → сервис.
 *   4) Пользователи alice(USER, владелец задачи 1), bob(USER), admin(ADMIN).
 *      Проверьте: alice DELETE /1 → 200; bob DELETE /1 → 403; admin DELETE /1 → 200.
 *
 * ЦЕЛЬ: освоить декларативный ownership-контроль через вызов бина в SpEL.
 *
 * ПОДСКАЗКА: имя бина в SpEL — "@taskSecurity" (значение из @Component("taskSecurity")).
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
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@SpringBootApplication
public class Task05 {
    public static void main(String[] args) {
        SpringApplication.run(Task05.class, args);
    }
}

@Component("taskSecurity")
class TaskSecurity05 {
    private final Map<Long, String> owners = Map.of(1L, "alice");
    public boolean isOwner(Long taskId, String username) {
        // TODO: return username.equals(owners.get(taskId));
        return false;
    }
}

@Service
class TaskService05 {
    // TODO: @PreAuthorize("hasRole('ADMIN') or @taskSecurity.isOwner(#id, authentication.name)")
    public String deleteTask(Long id) {
        return "задача " + id + " удалена";
    }
}

@RestController
@RequestMapping("/api/tasks")
class TaskController05 {
    private final TaskService05 service;
    TaskController05(TaskService05 service) { this.service = service; }

    @DeleteMapping("/{id}")
    String delete(@PathVariable Long id) { return service.deleteTask(id); }
}

@Configuration
@EnableWebSecurity
// TODO: @EnableMethodSecurity
class SecurityConfig05 {
    @Bean
    PasswordEncoder passwordEncoder() { return new BCryptPasswordEncoder(); }

    @Bean
    UserDetailsService users(PasswordEncoder enc) {
        return new InMemoryUserDetailsManager(
                User.withUsername("alice").password(enc.encode("password")).roles("USER").build(),
                User.withUsername("bob").password(enc.encode("password")).roles("USER").build(),
                User.withUsername("admin").password(enc.encode("admin")).roles("ADMIN").build());
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // TODO: http.authorizeHttpRequests(auth -> auth.anyRequest().authenticated());
        // TODO: http.httpBasic(Customizer.withDefaults());
        // TODO: http.csrf(csrf -> csrf.disable());
        return http.build();
    }
}
