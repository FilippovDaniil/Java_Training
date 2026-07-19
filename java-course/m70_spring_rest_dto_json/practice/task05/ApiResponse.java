package m70_spring_rest_dto_json.practice.task05;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.List;

// TODO: record ApiResponse<T>(T data, String message) с фабрикой ok(...)
record ApiResponse<T>(T data, String message, Instant timestamp) {
    // TODO: static <T> ApiResponse<T> ok(T data) { ... }
    public static <T> ApiResponse<T> ok(T data) {
        return new ApiResponse<>(data, "OK", Instant.now());
    }
}
