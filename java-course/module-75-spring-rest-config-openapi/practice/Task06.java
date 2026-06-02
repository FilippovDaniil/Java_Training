/**
 * Задача 06 — Модуль 75: Эволюция API — совместимое и несовместимое изменение
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: org.springframework.boot:spring-boot-starter-web:3.2.x (см. theory.md).
 *
 * ЗАДАНИЕ (анализ + код):
 *   1) В v1 ответ задачи — TaskV1_06(Long id, String title).
 *   2) СОВМЕСТИМОЕ изменение: добавьте в ту же версию НЕОБЯЗАТЕЛЬНОЕ поле.
 *      Сделайте TaskV1_06 с дополнительным полем priority (Integer, может быть null) —
 *      старые клиенты его просто проигнорируют. Эндпоинт /api/v1/tasks/{id}.
 *   3) НЕСОВМЕСТИМОЕ изменение: переименование/смена типа поля требует НОВОЙ версии.
 *      Сделайте TaskV2_06, где title переименован в name. Эндпоинт /api/v2/tasks/{id}.
 *
 *   В комментарии заполните таблицу: какие из изменений совместимы, какие — нет
 *   (см. таблицу в theory.md).
 *
 * ВЫВОД (ответьте комментарием):
 *   Почему добавление поля — ок в v1, а переименование — только через v2?
 *
 * ПОДСКАЗКА: совместимо = аддитивно (добавление необязательного); несовместимо = переименование/удаление/смена типа.
 */
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class Task06 {
    public static void main(String[] args) {
        SpringApplication.run(Task06.class, args);
    }
}

// СОВМЕСТИМОЕ: добавлено необязательное поле priority
// TODO: record TaskV1_06(Long id, String title, Integer priority)
record TaskV1_06(Long id, String title) {}

// НЕСОВМЕСТИМОЕ: title → name, поэтому это уже v2
// TODO: record TaskV2_06(Long id, String name, Integer priority)

@RestController
@RequestMapping("/api/v1/tasks")
class EvolutionV1Controller06 {
    @GetMapping("/{id}")
    public TaskV1_06 get(@PathVariable Long id) {
        // TODO: верните задачу с заполненным (или null) priority
        return null;
    }
}

// TODO: @RestController + @RequestMapping("/api/v2/tasks")
class EvolutionV2Controller06 {
    // TODO: @GetMapping("/{id}") → TaskV2_06 (поле name вместо title)
    public Object get(@PathVariable Long id) {
        return null;
    }
}
