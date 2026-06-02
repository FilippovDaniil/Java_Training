/**
 * Задача 05 — Модуль 73: Ответ с метаданными пагинации (PagedResponse<T>)
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: org.springframework.boot:spring-boot-starter-web:3.2.x (см. theory.md).
 *
 * ЗАДАНИЕ:
 *   Верните не голый список, а объект с метаданными о страницах.
 *   1) PagedResponse05<T> — record(List<T> content, int page, int size,
 *                                   long totalElements, int totalPages, boolean hasNext). Готов.
 *   2) page(): GET /api/tasks?page=0&size=3
 *        - нарежьте срез (как в Task02)
 *        - totalElements = ALL.size()
 *        - totalPages = (int) Math.ceil((double) totalElements / size)
 *        - hasNext = page + 1 < totalPages
 *        - верните new PagedResponse05<>(...)
 *
 * ОЖИДАЕМЫЙ JSON (page=0, size=3, всего 10):
 *   { "content":[...3 шт...], "page":0, "size":3,
 *     "totalElements":10, "totalPages":4, "hasNext":true }
 *
 * ЦЕЛЬ: дать клиенту всё для построения постраничной навигации.
 *
 * ПОДСКАЗКА: ceil через Math.ceil с приведением к double.
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
public class Task05 {
    public static void main(String[] args) {
        SpringApplication.run(Task05.class, args);
    }
}

record TaskDto05(Long id, String title) {}

record PagedResponse05<T>(List<T> content, int page, int size,
                          long totalElements, int totalPages, boolean hasNext) {}

@RestController
@RequestMapping("/api/tasks")
class PagedController05 {

    private static final List<TaskDto05> ALL = IntStream.rangeClosed(1, 10)
            .mapToObj(i -> new TaskDto05((long) i, "Задача " + i))
            .toList();

    // TODO: @GetMapping
    public PagedResponse05<TaskDto05> page(
            /* @RequestParam(defaultValue = "0") */ int page,
            /* @RequestParam(defaultValue = "20") */ int size) {
        // TODO: вычислите срез content, totalElements, totalPages, hasNext
        // TODO: верните new PagedResponse05<>(content, page, size, totalElements, totalPages, hasNext)
        return null;
    }
}
