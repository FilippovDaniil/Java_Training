package m74_spring_rest_crud_file.practice.task06;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
class ExportController06 {

    private static final List<TaskRow06> ALL = List.of(
            new TaskRow06(1L, "Купить кофе", "NEW"),
            new TaskRow06(2L, "Отчёт", "DONE"));

    // TODO: @PatchMapping("/{id}")
    public String archive(@PathVariable Long id, /* @RequestBody */ StatusDto06 dto) {
        // TODO: верните "Задача " + id + " переведена в " + dto.status()
        return null;
    }

    // TODO: @GetMapping(value = "/export", produces = "text/csv")
    public ResponseEntity<String> exportCsv() {
        // TODO: соберите CSV (заголовок + строки ALL)
        // TODO: верните ResponseEntity с Content-Disposition: attachment; filename="tasks.csv"
        return null;
    }
}
