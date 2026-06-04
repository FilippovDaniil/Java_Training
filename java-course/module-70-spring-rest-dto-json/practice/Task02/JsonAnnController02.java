import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tasks")
class JsonAnnController02 {

    @GetMapping("/1")
    public TaskView02 getOne() {
        // assignee = null специально, чтобы проверить NON_NULL
        return new TaskView02(1L, "Купить кофе", "служебное", null);
    }
}
