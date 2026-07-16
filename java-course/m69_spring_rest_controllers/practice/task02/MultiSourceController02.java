package m69_spring_rest_controllers.practice.task02;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tasks")
class MultiSourceController02 {

    // TODO: @PostMapping("/{id}")
    @PostMapping("/{id}")
    public String handle(
            /* TODO: @PathVariable */@PathVariable("id") Long id,
            /* TODO: @RequestParam(defaultValue = "false") */
                                     @RequestParam(name = "notify", defaultValue = "false") boolean notify,
            /* TODO: @RequestHeader(value = "Authorization", required = false) */
                                     @RequestHeader(name = "auth", value = "Authorization", required = false) String auth,
            /* TODO: @RequestBody */ @RequestBody CreateTaskDto body) {
        // TODO: соберите строку "id=..., title=..., notify=..., auth=есть/нет"
        return "id = " + id + ", title = " + body.title() + ", notify = " + notify +
                ", auth = " + (auth == null ? "нет" : "да");

    }
}
