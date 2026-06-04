/**
 * Задача 02 — Модуль 71: @Valid в контроллере и ответ 400
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   org.springframework.boot:spring-boot-starter-web:3.2.x,
 *   org.springframework.boot:spring-boot-starter-validation:3.2.x (см. theory.md).
 *
 * ЗАДАНИЕ:
 *   1) NewTaskDto уже размечен ограничениями (@NotBlank, @Size, @Min). Изучите.
 *   2) В TaskController02 метод create:
 *        - @PostMapping
 *        - ДОБАВЬТЕ @Valid перед @RequestBody — это и запускает проверку.
 *        - верните "Создана задача: " + dto.title()
 *   3) Проверьте вручную:
 *        OK:   { "title":"Кофе", "priority":3 }  → 200
 *        FAIL: { "title":"",     "priority":9 }  → 400 Bad Request
 *
 * ЭКСПЕРИМЕНТ: уберите @Valid и повторите FAIL-запрос — увидите, что проверка
 *   перестаёт срабатывать (вернётся 200). Верните @Valid обратно.
 *
 * ПОДСКАЗКА: без @Valid ограничения молча игнорируются (см. theory.md).
 */

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

public class Task02 {
    public static void main(String[] args) {
        SpringApplication.run(Task02.class, args);
    }
}
