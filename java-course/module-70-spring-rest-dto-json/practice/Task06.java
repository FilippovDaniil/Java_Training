/**
 * Задача 06 — Модуль 70: Вложенные DTO и коллекции
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: org.springframework.boot:spring-boot-starter-web:3.2.x (см. theory.md).
 *
 * ЗАДАНИЕ:
 *   Постройте составной ответ с вложенным объектом и коллекцией:
 *     1) AssigneeDto06 — record(Long id, String name).
 *     2) TaskDetailDto06 — record(Long id, String title, AssigneeDto06 assignee, List<String> tags).
 *     3) Метод getOne вернёт задачу с вложенным исполнителем и списком тегов.
 *
 * ОЖИДАЕМЫЙ JSON:
 *   { "id":1, "title":"Купить кофе",
 *     "assignee": { "id":7, "name":"Иван" },
 *     "tags": ["покупки","срочно"] }
 *
 * ЦЕЛЬ: увидеть, как вложенный record превращается во вложенный JSON-объект,
 *       а List<String> — в JSON-массив.
 *
 * ПОДСКАЗКА: Jackson рекурсивно сериализует вложенные record'ы.
 */
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
public class Task06 {
    public static void main(String[] args) {
        SpringApplication.run(Task06.class, args);
    }
}

// TODO: record AssigneeDto06(Long id, String name)
// TODO: record TaskDetailDto06(Long id, String title, AssigneeDto06 assignee, List<String> tags)

@RestController
@RequestMapping("/api/tasks")
class NestedController06 {

    @GetMapping("/{id}")
    public Object getOne(@PathVariable Long id) {
        // TODO: соберите TaskDetailDto06 с вложенным AssigneeDto06(7, "Иван")
        //       и tags = List.of("покупки", "срочно"); верните его
        return null;
    }
}
