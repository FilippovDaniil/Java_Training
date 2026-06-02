/**
 * Задача 03 — Модуль 73: Сортировка (?sort=поле,направление)
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: org.springframework.boot:spring-boot-starter-web:3.2.x (см. theory.md).
 *
 * ЗАДАНИЕ:
 *   sorted(): GET /api/tasks?sortBy=priority&dir=desc
 *     - @RequestParam(defaultValue = "title") String sortBy
 *     - @RequestParam(defaultValue = "asc")   String dir
 *     Отсортируйте копию ALL:
 *       - sortBy == "title"    → Comparator.comparing(TaskDto03::title)
 *       - sortBy == "priority" → Comparator.comparingInt(TaskDto03::priority)
 *       - иначе → 400 (бросьте IllegalArgumentException — это «белый список» полей!)
 *     если dir == "desc" — comparator.reversed().
 *
 * ВАЖНО (безопасность): сортировать можно ТОЛЬКО по полям из белого списка.
 *   Нельзя подставлять произвольное имя поля — это защита от инъекций.
 *
 * ПОДСКАЗКА:
 *   List<TaskDto03> copy = new ArrayList<>(ALL); copy.sort(cmp); return copy;
 */
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@SpringBootApplication
public class Task03 {
    public static void main(String[] args) {
        SpringApplication.run(Task03.class, args);
    }
}

record TaskDto03(Long id, String title, int priority) {}

@RestController
@RequestMapping("/api/tasks")
class SortController03 {

    private static final List<TaskDto03> ALL = List.of(
            new TaskDto03(1L, "Гамма", 2),
            new TaskDto03(2L, "Альфа", 5),
            new TaskDto03(3L, "Бета", 1));

    // TODO: @GetMapping
    public List<TaskDto03> sorted(
            /* @RequestParam(defaultValue = "title") */ String sortBy,
            /* @RequestParam(defaultValue = "asc") */ String dir) {
        // TODO: выберите Comparator по sortBy (белый список!), иначе IllegalArgumentException
        // TODO: если dir == "desc" — reversed()
        // TODO: отсортируйте копию ALL и верните
        return null;
    }
}
