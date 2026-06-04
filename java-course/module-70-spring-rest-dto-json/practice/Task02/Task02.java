/**
 * Задача 02 — Модуль 70: Jackson-аннотации — переименование, скрытие, пропуск null
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: org.springframework.boot:spring-boot-starter-web:3.2.x (см. theory.md).
 *
 * ЗАДАНИЕ:
 *   Разметьте TaskView02 так, чтобы:
 *     - title    → в JSON назывался "task_title"  (@JsonProperty)
 *     - secret   → НЕ попадал в JSON               (@JsonIgnore)
 *     - assignee → если null, поле отсутствовало   (@JsonInclude(NON_NULL) на классе/поле)
 *     - id       → как есть
 *   Метод getOne вернёт TaskView02 с secret="служебное" и assignee=null.
 *
 * ОЖИДАЕМЫЙ JSON (assignee отсутствует, secret отсутствует):
 *   { "id":1, "task_title":"Купить кофе" }
 *
 * ЦЕЛЬ: управлять формой JSON, не меняя имена полей в коде.
 *
 * ПОДСКАЗКА:
 *   import com.fasterxml.jackson.annotation.*;
 *   @JsonInclude(JsonInclude.Include.NON_NULL) можно повесить на класс.
 */

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

public class Task02 {
    public static void main(String[] args) {
        SpringApplication.run(Task02.class, args);
    }
}
