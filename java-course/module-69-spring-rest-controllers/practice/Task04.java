/**
 * Задача 04 — Модуль 69: Три способа задать статус ответа
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: org.springframework.boot:spring-boot-starter-web:3.2.x (см. theory.md).
 *
 * ЗАДАНИЕ:
 *   Реализуйте один и тот же по смыслу результат ТРЕМЯ способами:
 *     1) viaEntity(id): @GetMapping("/entity/{id}")
 *        - вернуть ResponseEntity: если id > 0 → 200 + "задача <id>", иначе 404.
 *     2) viaAnnotation(): @PostMapping("/annotation") + @ResponseStatus(HttpStatus.CREATED)
 *        - вернуть объект (строку) "создано", статус 201 даёт аннотация.
 *     3) viaVoid(id): @DeleteMapping("/void/{id}") + @ResponseStatus(HttpStatus.NO_CONTENT)
 *        - метод void, статус 204.
 *
 * ВОПРОС (ответьте комментарием):
 *   В каком случае удобнее ResponseEntity, а в каком — @ResponseStatus?
 *   (подсказка: статичный vs динамический статус — см. таблицу в theory.md)
 *
 * ПОДСКАЗКА:
 *   ResponseEntity подходит, когда статус зависит от данных (200 ИЛИ 404).
 *   @ResponseStatus — когда статус всегда один и тот же.
 */
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
public class Task04 {
    public static void main(String[] args) {
        SpringApplication.run(Task04.class, args);
    }
}

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
