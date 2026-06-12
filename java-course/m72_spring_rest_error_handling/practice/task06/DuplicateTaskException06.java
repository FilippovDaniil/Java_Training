package m72_spring_rest_error_handling.practice.task06;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

class DuplicateTaskException06 extends BusinessException06 {
    DuplicateTaskException06() { super("Такая задача уже существует"); }
}
