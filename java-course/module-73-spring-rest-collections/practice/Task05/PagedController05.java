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

    // TODO: @GetMapping
    public PagedResponse05<TaskDto05> page(
            /* @RequestParam(defaultValue = "0") */ int page,
            /* @RequestParam(defaultValue = "20") */ int size) {
        // TODO: вычислите срез content, totalElements, totalPages, hasNext
        // TODO: верните new PagedResponse05<>(content, page, size, totalElements, totalPages, hasNext)
        return null;
    }
}
