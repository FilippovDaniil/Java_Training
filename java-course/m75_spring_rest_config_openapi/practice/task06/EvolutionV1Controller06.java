package m75_spring_rest_config_openapi.practice.task06;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// НЕСОВМЕСТИМОЕ: title → name, поэтому это уже v2
// TODO: record TaskV2_06(Long id, String name, Integer priority)

@RestController
@RequestMapping("/api/v1/tasks")
class EvolutionV1Controller06 {
    @GetMapping("/{id}")
    public TaskV1_06 get(@PathVariable Long id) {
        // TODO: верните задачу с заполненным (или null) priority
        return null;
    }
}
