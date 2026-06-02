/**
 * Задача 04 — Модуль 71: Группы валидации (создание vs обновление)
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   org.springframework.boot:spring-boot-starter-web:3.2.x,
 *   org.springframework.boot:spring-boot-starter-validation:3.2.x (см. theory.md).
 *
 * ЗАДАНИЕ:
 *   Одно DTO — разные правила при создании и обновлении.
 *   1) Интерфейсы-группы OnCreate и OnUpdate уже объявлены.
 *   2) В TaskDto04 поле id разметьте:
 *        @Null(groups = OnCreate.class)      — при создании id ДОЛЖЕН быть null
 *        @NotNull(groups = OnUpdate.class)   — при обновлении id ОБЯЗАТЕЛЕН
 *      поле title:
 *        @NotBlank(groups = {OnCreate.class, OnUpdate.class})
 *   3) В контроллере:
 *        - create: @Validated(OnCreate.class) @RequestBody
 *        - update: @Validated(OnUpdate.class) @RequestBody
 *
 *   Проверьте:
 *      POST { "title":"X" }            → 200 (id null — ок для Create)
 *      POST { "id":5, "title":"X" }    → 400 (id должен быть null при Create)
 *      PUT  { "title":"X" }            → 400 (id обязателен при Update)
 *      PUT  { "id":5, "title":"X" }    → 200
 *
 * ВАЖНО: для групп используют @Validated (Spring), а НЕ @Valid (см. theory.md).
 */
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
public class Task04 {
    public static void main(String[] args) {
        SpringApplication.run(Task04.class, args);
    }
}

interface OnCreate {}
interface OnUpdate {}

record TaskDto04(
        /* TODO: @Null(groups = OnCreate.class) @NotNull(groups = OnUpdate.class) */ Long id,
        /* TODO: @NotBlank(groups = {OnCreate.class, OnUpdate.class}) */ String title
) {}

@RestController
@RequestMapping("/api/tasks")
class GroupController04 {

    @PostMapping
    public String create(/* TODO: @Validated(OnCreate.class) */ @RequestBody TaskDto04 dto) {
        // TODO: верните "Создано: " + dto.title()
        return null;
    }

    @PutMapping("/{id}")
    public String update(@PathVariable Long id,
                         /* TODO: @Validated(OnUpdate.class) */ @RequestBody TaskDto04 dto) {
        // TODO: верните "Обновлено: " + dto.id()
        return null;
    }
}
