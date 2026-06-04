import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

// TODO: record ApiResponse<T>(T data, String message) с фабрикой ok(...)
record ApiResponse<T>(T data, String message) {
    // TODO: static <T> ApiResponse<T> ok(T data) { ... }
}
