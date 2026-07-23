package m73_spring_rest_collections.practice.task05;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.stream.IntStream;

@RestController
@RequestMapping("/api/tasks")
class PagedController05 {

    private static final List<TaskDto05> ALL = IntStream.rangeClosed(1, 10)
            .mapToObj(i -> new TaskDto05((long) i, "Задача " + i))
            .toList();

    @GetMapping
    public PagedResponse05<TaskDto05> page(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "20") int size) {

        // 1. Вычисляем индексы
        int from = page * size;
        int to = Math.min(from + size, ALL.size());

        // 2. Получаем content
        List<TaskDto05> content;
        if (from >= ALL.size()) {
            content = List.of();
        } else {
            content = ALL.subList(from, to);
        }

        // 3. Вычисляем мета-информацию
        long totalElements = ALL.size();
        int totalPages = (int) Math.ceil((double) totalElements / size);
        boolean hasNext = page < totalPages - 1;

        // 4. Возвращаем ответ
        return new PagedResponse05<>(
                content,
                page,
                size,
                totalElements,
                totalPages,
                hasNext
        );
    }
}
