import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tasks")
class FilterController05 {

    @GetMapping
    public String search(
            /* TODO: @RequestParam(required = false) */ String status,
            /* TODO: @RequestParam(required = false) */ String assignee,
            /* TODO: @RequestParam(defaultValue = "id") */ String sort,
            /* TODO: @RequestParam(defaultValue = "0") */ int page,
            /* TODO: @RequestParam(defaultValue = "20") */ int size) {
        // TODO: соберите строку-описание (status/assignee == null → "любой")
        return null;
    }
}
