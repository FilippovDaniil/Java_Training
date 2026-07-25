package m75_spring_rest_config_openapi.practice.task06;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// TODO: @RestController + @RequestMapping("/api/v2/tasks")
@RestController
@RequestMapping("/api/v2/tasks")
class EvolutionV2Controller06 {
    // TODO: @GetMapping("/{id}") → TaskV2_06 (поле name вместо title)
    @GetMapping("/{id}")
    public Object get(@PathVariable("id") Long id) {
        return new TaskV1_06(id,"title",77);
    }
}
