package m95_spring_security_session_cors.practice.task06;

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

public class Task06 {
    public static void main(String[] args) {
        SpringApplication.run(Task06.class, args);
    }
}
