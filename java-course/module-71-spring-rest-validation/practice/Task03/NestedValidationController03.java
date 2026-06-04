import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tasks")
class NestedValidationController03 {

    @PostMapping
    public String create(/* TODO: @Valid */ @RequestBody CreateTaskDto03 dto) {
        // TODO: верните "OK: " + dto.assignee().name()
        return null;
    }
}
