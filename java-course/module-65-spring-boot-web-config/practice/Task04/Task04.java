/**
 * Задача 04 — Модуль 65: Bean Validation (@Valid)
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   org.springframework.boot:spring-boot-starter-web:3.2.x,
 *   org.springframework.boot:spring-boot-starter-validation:3.2.x (см. theory.md).
 *
 * ЗАДАНИЕ:
 *   1) Разметьте поля CreateUserDto аннотациями ограничений:
 *        - name:  @NotBlank, @Size(min = 2, max = 50)
 *        - email: @NotBlank, @Email
 *        - age:   @Min(18), @Max(120)
 *   2) В RegistrationController04 метод register пометьте @PostMapping
 *      и добавьте @Valid перед @RequestBody.
 *   3) Верните "Пользователь <name> зарегистрирован".
 *   4) Проверьте: запрос с пустым name или age=10 должен вернуть 400 Bad Request.
 *
 * ПРИМЕРЫ:
 *   OK:    { "name": "Иван", "email": "ivan@mail.ru", "age": 25 }   → 200
 *   FAIL:  { "name": "",     "email": "не-email",      "age": 10 }  → 400
 *
 * ПОДСКАЗКА:
 *   import jakarta.validation.constraints.*;
 *   Без @Valid аннотации ограничений игнорируются!
 */

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

public class Task04 {
    public static void main(String[] args) {
        SpringApplication.run(Task04.class, args);
    }
}
