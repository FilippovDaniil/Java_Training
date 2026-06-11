package m96_spring_security_db_users.practice.task06;

/**
 * Задача 06 — Модуль 96: REST-эндпоинт регистрации с валидацией и DTO
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   spring-boot-starter-web, spring-boot-starter-security, spring-boot-starter-data-jpa,
 *   spring-boot-starter-validation, h2.
 *
 * ЗАДАНИЕ:
 *   1) DTO запроса RegisterRequest06 (record): @NotBlank username, @Size(min=8) password.
 *   2) DTO ответа UserResponse06 (record): id + username (БЕЗ пароля!).
 *   3) AuthController06: POST /api/auth/register, @Valid @RequestBody RegisterRequest06:
 *        - вызвать RegistrationService06.register(...);
 *        - вернуть ResponseEntity со статусом 201 и UserResponse06.
 *      Дубликат username → 409 Conflict (@ExceptionHandler на IllegalStateException).
 *   4) SecurityFilterChain: "/api/auth/**" permitAll, остальное authenticated, httpBasic, csrf.disable.
 *   5) Проверьте: POST валидного → 201; короткий пароль → 400; дубликат → 409.
 *
 * ЦЕЛЬ: оформить регистрацию как публичный REST-эндпоинт с валидацией и без утечки пароля.
 *
 * ПОДСКАЗКА: НИКОГДА не возвращайте сущность с полем password — используйте DTO ответа.
 */

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

public class Task06 {
    public static void main(String[] args) {
        SpringApplication.run(Task06.class, args);
    }
}
