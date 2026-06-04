import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tasks")
class GroupController04 {

    @PostMapping
    public String create(/* TODO: @Validated(OnCreate.class) */ @RequestBody TaskDto04 dto) {
        // TODO: верните "Создано: " + dto.title()
        return null;
    }

    @PutMapping("/{id}")
    public String update(@PathVariable Long id,
                         /* TODO: @Validated(OnUpdate.class) */ @RequestBody TaskDto04 dto) {
        // TODO: верните "Обновлено: " + dto.id()
        return null;
    }
}
