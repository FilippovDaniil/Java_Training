/**
 * Задача 02 — Модуль 73: Ручная пагинация (page/size + subList)
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: org.springframework.boot:spring-boot-starter-web:3.2.x (см. theory.md).
 *
 * ЗАДАНИЕ:
 *   page(): GET /api/tasks?page=0&size=2
 *     - @RequestParam(defaultValue = "0") int page
 *     - @RequestParam(defaultValue = "20") int size
 *     Верните срез списка ALL по странице. Аккуратно с границами:
 *       from = page * size;
 *       to   = Math.min(from + size, ALL.size());
 *       если from >= ALL.size() → вернуть List.of();
 *       иначе ALL.subList(from, to).
 *
 *   Проверьте: ?page=0&size=2 → первые 2; ?page=1&size=2 → следующие 2;
 *              ?page=99&size=2 → [] (без исключения!).
 *
 * ЦЕЛЬ: безопасно нарезать список на страницы.
 *
 * ПОДСКАЗКА: главный риск — IndexOutOfBoundsException; защищайтесь Math.min и проверкой from.
 */
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.IntStream;

@SpringBootApplication
public class Task02 {
    public static void main(String[] args) {
        SpringApplication.run(Task02.class, args);
    }
}

record TaskDto02(Long id, String title) {}

@RestController
@RequestMapping("/api/tasks")
class PageController02 {

    // 10 задач для теста пагинации
    private static final List<TaskDto02> ALL = IntStream.rangeClosed(1, 10)
            .mapToObj(i -> new TaskDto02((long) i, "Задача " + i))
            .toList();

    // TODO: @GetMapping
    public List<TaskDto02> page(
            /* @RequestParam(defaultValue = "0") */ int page,
            /* @RequestParam(defaultValue = "20") */ int size) {
        // TODO: from = page*size; to = Math.min(from+size, ALL.size());
        // TODO: if (from >= ALL.size()) return List.of(); else return ALL.subList(from, to);
        return null;
    }
}
