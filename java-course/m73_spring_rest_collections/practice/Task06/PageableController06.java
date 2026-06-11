package m73_spring_rest_collections.practice.task06;

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
