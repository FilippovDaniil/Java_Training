package m72_spring_rest_error_handling.practice.task03;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

class TaskNotFoundException03 extends RuntimeException {
    TaskNotFoundException03(String what, Long id) { super(what + " " + id + " не найден(а)"); }
}
