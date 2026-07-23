package m73_spring_rest_collections.practice.task04;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/tasks")
class FilterController04 {

    private static final List<TaskDto04> ALL = List.of(
            new TaskDto04(1L, "A", "DONE", "ivan", 5),
            new TaskDto04(2L, "B", "NEW", "ivan", 2),
            new TaskDto04(3L, "C", "DONE", "anna", 4));

    // TODO: @GetMapping
    @GetMapping
    public List<TaskDto04> filter(
            /* @RequestParam(required = false) */
            @RequestParam(name = "status", required = false) String status,
            /* @RequestParam(required = false) */
            @RequestParam(name = "assignee", required = false) String assignee,
            /* @RequestParam(required = false) */
            @RequestParam(name = "priorityMin", required = false) Integer priorityMin) {
        // TODO: накопите условия на Stream и верните результат
        // Начинаем поток
        Stream<TaskDto04> stream = ALL.stream();

        // Добавляем фильтры по мере необходимости
        if (status != null && !status.isBlank()) {
            stream = stream.filter(task -> task.status().equals(status));
        }

        if (assignee != null && !assignee.isBlank()) {
            stream = stream.filter(task -> task.assignee().equals(assignee));
        }

        if (priorityMin != null) {
            stream = stream.filter(task -> task.priority() >= priorityMin);
        }

        return stream.toList();
    }
}
