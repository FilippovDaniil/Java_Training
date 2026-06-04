import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;

@RestController
@RequestMapping("/api/tasks")
class ConversionController06 {

    // TODO: @GetMapping("/by-status")
    public String byStatus(/* @RequestParam */ Status06 status) {
        // TODO: верните "Фильтр по статусу: " + status
        return null;
    }

    // TODO: @GetMapping("/created-after")
    public String createdAfter(
            /* @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) */ LocalDate from) {
        // TODO: верните "Создано после: " + from
        return null;
    }
}
