package m73_spring_rest_collections.practice.task07;

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
