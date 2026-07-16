package m69_spring_rest_controllers.practice.task04;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tasks")
class StatusController04 {

    @GetMapping("/entity/{id}")
    public ResponseEntity<String> viaEntity(@PathVariable("id") Long id) {
        // Эмуляция поиска в БД
        if (id > 0 && id < 100) {  // условие, что объект найден
            return ResponseEntity.ok("Объект с id: " + id);  // 200 + данные
        } else {
            return ResponseEntity.notFound().build();  // 404
        }
    }

    // TODO: @PostMapping("/annotation") + @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/annotation")
    @ResponseStatus(HttpStatus.CREATED)
    public String viaAnnotation() {
        // TODO: верните "создано"
        return "создано";
    }

    // TODO: @DeleteMapping("/void/{id}") + @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/void/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void viaVoid(@PathVariable Long id) {
        // тело пустое — статус 204 задаёт аннотация
    }
}
