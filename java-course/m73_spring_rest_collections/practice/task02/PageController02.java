package m73_spring_rest_collections.practice.task02;

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
