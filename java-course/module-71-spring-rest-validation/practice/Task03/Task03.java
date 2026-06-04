/**
 * Задача 03 — Модуль 71: Вложенная валидация (@Valid на поле)
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   org.springframework.boot:spring-boot-starter-web:3.2.x,
 *   org.springframework.boot:spring-boot-starter-validation:3.2.x (см. theory.md).
 *
 * ЗАДАНИЕ:
 *   1) AssigneeDto03 разметьте: id — @NotNull, name — @NotBlank.
 *   2) В CreateTaskDto03 поле assignee пометьте @Valid и @NotNull —
 *      ТОЛЬКО @Valid на поле включает проверку ВЛОЖЕННОГО объекта.
 *   3) В контроллере create: @Valid @RequestBody.
 *      Вернуть "OK: " + dto.assignee().name().
 *   4) Проверьте:
 *        OK:   { "title":"Кофе", "assignee": { "id":7, "name":"Иван" } } → 200
 *        FAIL: { "title":"Кофе", "assignee": { "id":7, "name":"" } }     → 400
 *
 * ВОПРОС (ответьте комментарием):
 *   Что произойдёт, если убрать @Valid с поля assignee (но оставить @NotNull)?
 *
 * ПОДСКАЗКА: @NotNull проверит лишь, что объект не null; @Valid — что он сам валиден.
 */

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

public class Task03 {
    public static void main(String[] args) {
        SpringApplication.run(Task03.class, args);
    }
}
