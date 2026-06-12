package m73_spring_rest_collections.practice.task01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
class CollectionController01 {

    private final List<TaskDto01> seed = List.of(
            new TaskDto01(1L, "Купить кофе", "NEW"),
            new TaskDto01(2L, "Написать отчёт", "IN_PROGRESS"),
            new TaskDto01(3L, "Отправить письмо", "DONE"));

    // TODO: @GetMapping → вернуть seed
    public List<TaskDto01> all() {
        return null;
    }

    // TODO: @GetMapping("/empty") → вернуть List.of()
    public List<TaskDto01> empty() {
        return null;
    }
}
