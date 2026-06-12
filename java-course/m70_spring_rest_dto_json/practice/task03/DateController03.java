package m70_spring_rest_dto_json.practice.task03;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDate;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/tasks")
class DateController03 {

    @GetMapping("/1")
    public TaskWithDate03 getOne() {
        return new TaskWithDate03(1L, "Купить кофе",
                LocalDateTime.of(2026, 6, 2, 14, 30),
                LocalDate.of(2026, 6, 10));
    }
}
