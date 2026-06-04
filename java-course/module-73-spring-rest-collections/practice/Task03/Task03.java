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

public class Task03 {
    public static void main(String[] args) {
        SpringApplication.run(Task03.class, args);
    }
}
