package m71_spring_rest_validation.practice.task04;

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
    public String create(/* TODO: @Validated(OnCreate.class) */
            @Validated(OnCreate.class) @RequestBody TaskDto04 dto) {
        // TODO: верните "Создано: " + dto.title()
        return "Создано: " + dto.title();
    }

    @PutMapping("/{id}")
    public String update(@PathVariable("id") Long id,
                         /* TODO: @Validated(OnUpdate.class) */
                         @Validated(OnUpdate.class)
                             @RequestBody TaskDto04 dto) {
        // TODO: верните "Обновлено: " + dto.id()
        return "Обновлено: " + dto.id();
    }
}
