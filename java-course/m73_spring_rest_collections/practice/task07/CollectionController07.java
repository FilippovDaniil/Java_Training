package m73_spring_rest_collections.practice.task07;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/tasks")
class CollectionController07 {

    private static final List<TaskDto07> ALL = IntStream.rangeClosed(1, 12)
            .mapToObj(i -> new TaskDto07((long) i, "Задача " + i,
                    (i % 2 == 0) ? "NEW" : "DONE",
                    (i % 3 == 0) ? "ivan" : "anna",
                    (i % 5) + 1))
            .toList();

    @GetMapping
    public PagedResponse07<TaskDto07> search(
            @RequestParam(name = "status", required = false) String status,
            @RequestParam(name = "assignee", required = false) String assignee,
            @RequestParam(name = "sortBy", defaultValue = "title") String sortBy,
            @RequestParam(name = "dir", defaultValue = "asc") String dir,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "5") int size) {

        // ============ 1) ФИЛЬТР ============
        Stream<TaskDto07> s = ALL.stream();
        if (status != null && !status.isBlank()) {
            s = s.filter(t -> status.equals(t.status()));
        }
        if (assignee != null && !assignee.isBlank()) {
            s = s.filter(t -> assignee.equals(t.assignee()));
        }
        List<TaskDto07> filtered = s.toList();

        // ============ 2) СОРТИРОВКА ============
        Comparator<TaskDto07> cmp;
        switch (sortBy) {
            case "title" -> cmp = Comparator.comparing(TaskDto07::title);
            case "priority" -> cmp = Comparator.comparingInt(TaskDto07::priority);
            case "id" -> cmp = Comparator.comparingLong(TaskDto07::id);
            case "status" -> cmp = Comparator.comparing(TaskDto07::status);
            case "assignee" -> cmp = Comparator.comparing(TaskDto07::assignee);
            default -> throw new IllegalArgumentException(
                    "Недопустимое поле для сортировки: " + sortBy
            );
        }

        if ("desc".equalsIgnoreCase(dir)) {
            cmp = cmp.reversed();
        }

        List<TaskDto07> sorted = new ArrayList<>(filtered);
        sorted.sort(cmp);

        // ============ 3) ПАГИНАЦИЯ ============
        long total = sorted.size();
        int from = page * size;
        int to = Math.min(from + size, sorted.size());

        List<TaskDto07> content;
        if (from >= total) {
            content = List.of();
        } else {
            content = sorted.subList(from, to);
        }

        // ============ 4) ОТВЕТ ============
        int totalPages = size > 0 ? (int) Math.ceil((double) total / size) : 0;
        boolean hasNext = page + 1 < totalPages;

        return new PagedResponse07<>(content, page, size, total, totalPages, hasNext);
    }
}
