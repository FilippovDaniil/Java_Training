package m67_spring_rest_http_backend.practice.task06;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
class DecisionController06 {

    @PostMapping("/api/tasks/validate")
    public ResponseEntity<String> process(
            @RequestParam(name = "title", required = false) Optional<String> title,
            @RequestParam(name = "token", required = false) Optional<String> token,
            @RequestParam(name = "id", required = false) Optional<Long> id) {

        if (token.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Вы не прошли аутентификацию");
        } else if (!token.get().equals("admin")) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("Вам не разрешено посещать данную страницу");
        } else if (title.isEmpty() || title.get().isBlank()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Title должен быть заполнен");
        } else if (id.isPresent() && id.get() == 999) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Вы попали на дубликат");
        } else {
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
    }
}
