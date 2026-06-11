package m70_spring_rest_dto_json.practice.task03;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDate;
import java.time.LocalDateTime;

record TaskWithDate03(
        Long id,
        String title,
        /* TODO: @JsonFormat(pattern = "dd.MM.yyyy HH:mm") */ LocalDateTime createdAt,
        /* TODO: @JsonFormat(pattern = "yyyy-MM-dd") */ LocalDate dueDate
) {}
