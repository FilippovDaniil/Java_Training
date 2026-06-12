package m67_spring_rest_http_backend.practice.task01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/demo")
class StatusController01 {

    @GetMapping("/read")
    public ResponseEntity<String> read() {
        // TODO: 200 OK с телом "данные"
        return null;
    }

    @PostMapping("/create")
    public ResponseEntity<String> created() {
        // TODO: 201 Created с телом "создано"
        return null;
    }

    @DeleteMapping("/clear")
    public ResponseEntity<Void> noBody() {
        // TODO: 204 No Content
        return null;
    }

    @GetMapping("/missing")
    public ResponseEntity<String> missing() {
        // TODO: 404 Not Found
        return null;
    }
}
