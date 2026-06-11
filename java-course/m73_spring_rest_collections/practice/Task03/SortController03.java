package m73_spring_rest_collections.practice.task03;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
class SortController03 {

    private static final List<TaskDto03> ALL = List.of(
            new TaskDto03(1L, "Гамма", 2),
            new TaskDto03(2L, "Альфа", 5),
            new TaskDto03(3L, "Бета", 1));

    // TODO: @GetMapping
    public List<TaskDto03> sorted(
            /* @RequestParam(defaultValue = "title") */ String sortBy,
            /* @RequestParam(defaultValue = "asc") */ String dir) {
        // TODO: выберите Comparator по sortBy (белый список!), иначе IllegalArgumentException
        // TODO: если dir == "desc" — reversed()
        // TODO: отсортируйте копию ALL и верните
        return null;
    }
}
