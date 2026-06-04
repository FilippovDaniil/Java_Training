import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// TODO: @JsonInclude(JsonInclude.Include.NON_NULL)
record TaskView02(
        Long id,
        /* TODO: @JsonProperty("task_title") */ String title,
        /* TODO: @JsonIgnore */ String secret,
        String assignee
) {}
