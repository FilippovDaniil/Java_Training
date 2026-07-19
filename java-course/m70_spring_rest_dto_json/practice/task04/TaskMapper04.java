package m70_spring_rest_dto_json.practice.task04;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// TODO: record TaskResponse04(Long id, String title, String status)
public class TaskMapper04 {
    // TODO: static TaskResponse04 toResponse(TaskEntity04 e) { ... }
    public static TaskResponse04 toResponse(TaskEntity04 e) {
        return new TaskResponse04(e.id,e.title,e.status);
    }
}
