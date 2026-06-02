/**
 * Задача 05 — Модуль 67: Полный набор HTTP-методов для ресурса "Задача"
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: org.springframework.boot:spring-boot-starter-web:3.2.x (см. theory.md).
 *
 * ЗАДАНИЕ:
 *   Реализуйте TaskController05 для ресурса /api/tasks с корректными методами и статусами:
 *     - GET    /api/tasks/{id} → 200 OK + строка "Задача <id>"
 *     - POST   /api/tasks      → 201 Created + "Создана задача" + Location: /api/tasks/100
 *     - PUT    /api/tasks/{id} → 200 OK + "Задача <id> заменена" (идемпотентно)
 *     - PATCH  /api/tasks/{id} → 200 OK + "Статус задачи <id> изменён"
 *     - DELETE /api/tasks/{id} → 204 No Content (идемпотентно)
 *
 *   В каждом ответе подберите статус, соответствующий семантике метода (см. theory.md).
 *
 * ЦЕЛЬ: связать таблицу "метод ↔ семантика ↔ статус" с реальными аннотациями Spring.
 *
 * ПОДСКАЗКА:
 *   @PutMapping("/{id}"), @PatchMapping("/{id}"), @DeleteMapping("/{id}")
 *   ResponseEntity.created(URI.create("/api/tasks/100"))
 */
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@SpringBootApplication
public class Task05 {
    public static void main(String[] args) {
        SpringApplication.run(Task05.class, args);
    }
}

@RestController
@RequestMapping("/api/tasks")
class TaskController05 {

    // TODO: @GetMapping("/{id}") → 200 + "Задача <id>"
    public ResponseEntity<String> getOne(@PathVariable Long id) {
        return null;
    }

    // TODO: @PostMapping → 201 + Location + "Создана задача"
    public ResponseEntity<String> create() {
        return null;
    }

    // TODO: @PutMapping("/{id}") → 200 + "Задача <id> заменена"
    public ResponseEntity<String> replace(@PathVariable Long id) {
        return null;
    }

    // TODO: @PatchMapping("/{id}") → 200 + "Статус задачи <id> изменён"
    public ResponseEntity<String> patch(@PathVariable Long id) {
        return null;
    }

    // TODO: @DeleteMapping("/{id}") → 204
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return null;
    }
}
