package m69_spring_rest_controllers.practice.task04;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tasks")
class StatusController04 {

    // TODO: @GetMapping("/entity/{id}") → 200 либо 404 через ResponseEntity
    public ResponseEntity<String> viaEntity(@PathVariable Long id) {
        return null;
    }

    // TODO: @PostMapping("/annotation") + @ResponseStatus(HttpStatus.CREATED)
    public String viaAnnotation() {
        // TODO: верните "создано"
        return null;
    }

    // TODO: @DeleteMapping("/void/{id}") + @ResponseStatus(HttpStatus.NO_CONTENT)
    public void viaVoid(@PathVariable Long id) {
        // тело пустое — статус 204 задаёт аннотация
    }
}
