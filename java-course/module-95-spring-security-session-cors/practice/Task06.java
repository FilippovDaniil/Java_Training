/**
 * Задача 06 — Модуль 95: защищённая загрузка файла (вложение к задаче)
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   spring-boot-starter-web, spring-boot-starter-security (см. theory.md).
 *
 * ЗАДАНИЕ:
 *   Реализуйте безопасную загрузку вложения к задаче.
 *   1) В application.properties — лимиты (впишите как образец в SETTINGS-блок JavaDoc):
 *        spring.servlet.multipart.max-file-size=5MB
 *        spring.servlet.multipart.max-request-size=10MB
 *   2) Эндпоинт POST /api/tasks/{id}/attachments (authenticated), параметр MultipartFile file:
 *        - проверить, что файл не пустой;
 *        - проверить content-type по БЕЛОМУ списку (image/png, image/jpeg, application/pdf);
 *        - НЕ доверять оригинальному имени (path traversal!) — сгенерировать своё имя
 *          (например, UUID + расширение из content-type);
 *        - вернуть сгенерированное имя.
 *   3) SecurityFilterChain: /api/** authenticated, httpBasic, csrf.disable.
 *
 * ЦЕЛЬ: освоить безопасную обработку загрузки: лимиты, белый список типов, генерация имени.
 *
 * ПОДСКАЗКА: оригинальное имя file.getOriginalFilename() может содержать "../" — НЕ использовать
 *            его для пути сохранения; хранить файлы вне веб-корня.
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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;
import java.util.UUID;

@SpringBootApplication
public class Task06 {
    public static void main(String[] args) {
        SpringApplication.run(Task06.class, args);
    }
}

@RestController
class AttachmentController06 {
    private static final Set<String> ALLOWED = Set.of("image/png", "image/jpeg", "application/pdf");

    @PostMapping("/api/tasks/{id}/attachments")
    String upload(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
        // TODO: if (file.isEmpty()) throw new IllegalArgumentException("Пустой файл");
        // TODO: if (!ALLOWED.contains(file.getContentType()))
        // TODO:     throw new IllegalArgumentException("Недопустимый тип: " + file.getContentType());
        // TODO: String ext = file.getContentType().equals("application/pdf") ? ".pdf" : ".img";
        // TODO: String safeName = UUID.randomUUID() + ext;   // НЕ используем оригинальное имя!
        // TODO: // сохранить file во внешнее хранилище под safeName ...
        // TODO: return "Загружено к задаче " + id + " как " + safeName;
        return null;
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
                User.withUsername("alice").password(enc.encode("password")).roles("USER").build());
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // TODO: http.authorizeHttpRequests(auth -> auth.requestMatchers("/api/**").authenticated().anyRequest().authenticated());
        // TODO: http.httpBasic(Customizer.withDefaults());
        // TODO: http.csrf(csrf -> csrf.disable());
        return http.build();
    }
}
