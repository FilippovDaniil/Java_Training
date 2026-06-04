import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// СОВМЕСТИМОЕ: добавлено необязательное поле priority
// TODO: record TaskV1_06(Long id, String title, Integer priority)
record TaskV1_06(Long id, String title) {}
