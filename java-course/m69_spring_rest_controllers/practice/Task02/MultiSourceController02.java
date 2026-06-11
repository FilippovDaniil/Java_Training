package m69_spring_rest_controllers.practice.task02;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tasks")
class MultiSourceController02 {

    // TODO: @PostMapping("/{id}")
    public String handle(
            /* TODO: @PathVariable */ Long id,
            /* TODO: @RequestParam(defaultValue = "false") */ boolean notify,
            /* TODO: @RequestHeader(value = "Authorization", required = false) */ String auth,
            /* TODO: @RequestBody */ CreateTaskDto body) {
        // TODO: соберите строку "id=..., title=..., notify=..., auth=есть/нет"
        return null;
    }
}
