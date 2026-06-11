package m68_spring_rest_design.practice.task06;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RestController
@RequestMapping("/api/tasks")
class HateoasController06 {

    @GetMapping("/{id}")
    public Map<String, Object> getOne(@PathVariable Long id) {
        // TODO: соберите Map с id, title, status и вложенной "_links"
        return null;
    }
}
