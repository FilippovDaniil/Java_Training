package m65_spring_boot_web_config.practice.task05;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/products")
class LookupController05 {

    @GetMapping("/{id}")
    public String find(@PathVariable Long id) {
        // TODO: если id <= 0 — бросьте new NoSuchElementException("Товар не найден: " + id)
        return "Товар " + id;
    }
}
