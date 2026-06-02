/**
 * Задача 07 — Модуль 73: МИНИ-ПРОЕКТ «Коллекция Task Tracker: фильтр + сортировка + пагинация»
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: org.springframework.boot:spring-boot-starter-web:3.2.x (см. theory.md).
 *
 * ЦЕЛЬ: собрать «настоящий» эндпоинт списка задач, объединив всё из модуля 73:
 *       фильтрацию по параметрам → сортировку → пагинацию → ответ с метаданными.
 *
 * ЗАДАНИЕ:
 *   search(): GET /api/tasks?status=&assignee=&sortBy=&dir=&page=&size=
 *     ПОРЯДОК ОБРАБОТКИ (важен!):
 *       1) ФИЛЬТР: применить необязательные status, assignee (накопление условий на Stream).
 *       2) СОРТИРОВКА: sortBy ∈ {"title","priority"} (белый список!), dir ∈ {"asc","desc"};
 *          неизвестное поле → IllegalArgumentException.
 *       3) ПАГИНАЦИЯ: по page/size нарезать срез (безопасно, с Math.min).
 *       4) ОТВЕТ: вернуть PagedResponse07 c content, page, size, totalElements (число ПОСЛЕ
 *          фильтра, но ДО пагинации), totalPages, hasNext.
 *
 *   Параметры:
 *     status      @RequestParam(required=false)
 *     assignee    @RequestParam(required=false)
 *     sortBy      @RequestParam(defaultValue="title")
 *     dir         @RequestParam(defaultValue="asc")
 *     page        @RequestParam(defaultValue="0")
 *     size        @RequestParam(defaultValue="5")
 *
 *   Проверьте:
 *     GET /api/tasks?status=NEW&sortBy=priority&dir=desc&page=0&size=2
 *
 * КОНВЕЙЕР:
 *
 *   ALL ──filter(status,assignee)──► ──sort(sortBy,dir)──► ──page(page,size)──► PagedResponse07
 *                                              (totalElements = размер после фильтра)
 *
 * ПОДСКАЗКА: переиспользуйте Task04 (фильтр), Task03 (сортировка), Task05 (метаданные).
 */
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@SpringBootApplication
public class Task07 {
    public static void main(String[] args) {
        SpringApplication.run(Task07.class, args);
    }
}

record TaskDto07(Long id, String title, String status, String assignee, int priority) {}

record PagedResponse07<T>(List<T> content, int page, int size,
                          long totalElements, int totalPages, boolean hasNext) {}

@RestController
@RequestMapping("/api/tasks")
class CollectionController07 {

    private static final List<TaskDto07> ALL = IntStream.rangeClosed(1, 12)
            .mapToObj(i -> new TaskDto07((long) i, "Задача " + i,
                    (i % 2 == 0) ? "NEW" : "DONE",
                    (i % 3 == 0) ? "ivan" : "anna",
                    (i % 5) + 1))
            .toList();

    // TODO: @GetMapping
    public PagedResponse07<TaskDto07> search(
            /* @RequestParam(required=false) */ String status,
            /* @RequestParam(required=false) */ String assignee,
            /* @RequestParam(defaultValue="title") */ String sortBy,
            /* @RequestParam(defaultValue="asc") */ String dir,
            /* @RequestParam(defaultValue="0") */ int page,
            /* @RequestParam(defaultValue="5") */ int size) {

        // 1) ФИЛЬТР
        Stream<TaskDto07> s = ALL.stream();
        // TODO: if (status != null)   s = s.filter(t -> t.status().equals(status));
        // TODO: if (assignee != null) s = s.filter(t -> t.assignee().equals(assignee));
        List<TaskDto07> filtered = s.toList();

        // 2) СОРТИРОВКА (белый список полей)
        Comparator<TaskDto07> cmp;
        // TODO: switch (sortBy) { case "title" -> ...; case "priority" -> ...; default -> throw new IllegalArgumentException(...) }
        // TODO: if (dir == "desc") cmp = cmp.reversed();
        // TODO: filtered = new ArrayList<>(filtered); filtered.sort(cmp);

        // 3) ПАГИНАЦИЯ
        long total = filtered.size();
        // TODO: from = page*size; to = Math.min(from+size, filtered.size());
        // TODO: content = (from >= total) ? List.of() : filtered.subList(from, to);

        // 4) ОТВЕТ
        // TODO: totalPages = (int) Math.ceil((double) total / size);
        // TODO: hasNext = page + 1 < totalPages;
        // TODO: return new PagedResponse07<>(content, page, size, total, totalPages, hasNext);
        return null;
    }
}
