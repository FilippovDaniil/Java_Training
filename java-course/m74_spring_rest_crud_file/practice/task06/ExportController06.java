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

    // ========== PATCH - обновить статус ==========
    @PatchMapping("/{id}")
    public String archive(@PathVariable Long id, @RequestBody StatusDto06 dto) {
        return "Задача " + id + " переведена в " + dto.status();
    }

    // ========== GET - экспорт в CSV ==========
    @GetMapping(value = "/export", produces = "text/csv")
    public ResponseEntity<String> exportCsv() {
        // Строим CSV
        StringBuilder csv = new StringBuilder();

        // Заголовок
        csv.append("id,title,status\n");

        // Данные
        for (TaskRow06 task : ALL) {
            csv.append(task.id()).append(",")
                    .append(task.title()).append(",")
                    .append(task.status()).append("\n");
        }

        // Возвращаем с заголовком для скачивания
        return ResponseEntity
                .ok()
                .header("Content-Disposition", "attachment; filename=\"tasks.csv\"")
                .body(csv.toString());
    }
}
