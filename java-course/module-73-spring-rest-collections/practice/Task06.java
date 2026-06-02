/**
 * Задача 06 — Модуль 73: Pageable и Page из Spring Data (превью)
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   org.springframework.boot:spring-boot-starter-web:3.2.x,
 *   org.springframework.boot:spring-boot-starter-data-jpa:3.2.x  (ради Pageable/Page;
 *   достаточно и org.springframework.data:spring-data-commons). См. theory.md.
 *
 * ЗАДАНИЕ:
 *   Покажите, как Spring сам разбирает ?page=&size=&sort= в объект Pageable.
 *   1) all(Pageable pageable): GET /api/tasks?page=0&size=2&sort=title,asc
 *      - примените пагинацию к списку ВРУЧНУЮ, используя pageable.getPageNumber()
 *        и pageable.getPageSize() (тут БД нет — режем список сами).
 *      - заверните результат в PageImpl, чтобы вернуть полноценный Page<T>:
 *          return new PageImpl<>(content, pageable, ALL.size());
 *   2) Сравните с ручной пагинацией из Task02/Task05.
 *
 * ЦЕЛЬ: познакомиться с Pageable/Page — в модуле 79 они заработают «по-настоящему» с БД.
 *
 * ПОДСКАЗКА:
 *   import org.springframework.data.domain.*;
 *   PageImpl<>(List<T> content, Pageable pageable, long total) — готовый Page.
 *   Page<T> сам сериализуется в JSON с полями content/totalElements/totalPages/...
 */
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.IntStream;

@SpringBootApplication
public class Task06 {
    public static void main(String[] args) {
        SpringApplication.run(Task06.class, args);
    }
}

record TaskDto06(Long id, String title) {}

@RestController
@RequestMapping("/api/tasks")
class PageableController06 {

    private static final List<TaskDto06> ALL = IntStream.rangeClosed(1, 10)
            .mapToObj(i -> new TaskDto06((long) i, "Задача " + i))
            .toList();

    // TODO: @GetMapping
    public Page<TaskDto06> all(Pageable pageable) {
        // TODO: from = pageable.getPageNumber() * pageable.getPageSize();
        // TODO: to   = Math.min(from + pageable.getPageSize(), ALL.size());
        // TODO: content = (from >= ALL.size()) ? List.of() : ALL.subList(from, to);
        // TODO: return new PageImpl<>(content, pageable, ALL.size());
        return null;
    }
}
