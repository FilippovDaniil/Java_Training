package m71_spring_rest_validation.practice.task02;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tasks")
class TaskController02 {

    @PostMapping
    public String create(/* TODO: добавьте @Valid */ @RequestBody NewTaskDto dto) {
        // TODO: верните "Создана задача: " + dto.title()
        return null;
    }
}
