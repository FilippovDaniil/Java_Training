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
import java.util.Map;

@RestController
@RequestMapping("/api/tasks")
class SortController03 {

    private static final List<TaskDto03> ALL = List.of(
            new TaskDto03(1L, "Гамма", 2),
            new TaskDto03(2L, "Альфа", 5),
            new TaskDto03(3L, "Бета", 1));

    @GetMapping
    public List<TaskDto03> sorted(
            @RequestParam(name = "sortBy", defaultValue = "title") String sortBy,
            @RequestParam(name = "dir", defaultValue = "asc") String dir) {

        // 1. Белый список допустимых полей для сортировки
        Map<String, Comparator<TaskDto03>> comparators = Map.of(
                "id", Comparator.comparingLong(TaskDto03::id),
                "title", Comparator.comparing(TaskDto03::title),
                "priority", Comparator.comparingInt(TaskDto03::priority)
        );

        // 2. Проверяем, что поле для сортировки допустимо
        Comparator<TaskDto03> comparator = comparators.get(sortBy);
        if (comparator == null) {
            throw new IllegalArgumentException("Недопустимое поле для сортировки: " + sortBy +
                    ". Разрешены: " + comparators.keySet());
        }

        // 3. Применяем направление сортировки
        if ("desc".equalsIgnoreCase(dir)) {
            comparator = comparator.reversed();
        } else if (!"asc".equalsIgnoreCase(dir)) {
            throw new IllegalArgumentException("Недопустимое направление сортировки: " + dir +
                    ". Разрешены: asc, desc");
        }

        // 4. Сортируем копию и возвращаем
        return ALL.stream()
                .sorted(comparator)
                .toList();
    }
}
