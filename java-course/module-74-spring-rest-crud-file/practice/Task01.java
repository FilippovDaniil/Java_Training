/**
 * Задача 01 — Модуль 74: Полный CRUD-скелет (повторение)
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: org.springframework.boot:spring-boot-starter-web:3.2.x (см. theory.md).
 *
 * ЗАДАНИЕ:
 *   Реализуйте полный CRUD для ресурса задач (хранилище в памяти Map<Long, Task01>):
 *     - POST   /api/tasks        @RequestParam title → 201 + созданная задача
 *     - GET    /api/tasks        → список
 *     - GET    /api/tasks/{id}   → 200 либо 404
 *     - PUT    /api/tasks/{id}   @RequestParam title → 200 (заменить целиком) либо 404
 *     - DELETE /api/tasks/{id}   → 204 либо 404
 *
 *   Подберите корректные статусы (201/200/404/204) через ResponseEntity.
 *
 * ЦЕЛЬ: закрепить «скелет» CRUD перед файловыми операциями.
 *
 * ПОДСКАЗКА: id-генератор AtomicLong; используйте наработки из модулей 67/69.
 */
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@SpringBootApplication
public class Task01 {
    public static void main(String[] args) {
        SpringApplication.run(Task01.class, args);
    }
}

record Task01Model(Long id, String title) {}

@RestController
@RequestMapping("/api/tasks")
class CrudController01 {

    private final Map<Long, Task01Model> store = new ConcurrentHashMap<>();
    private final AtomicLong seq = new AtomicLong(1);

    // TODO: @PostMapping → 201 + Location + задача
    public ResponseEntity<Task01Model> create(@RequestParam String title) {
        return null;
    }

    // TODO: @GetMapping → список
    public List<Task01Model> all() {
        return null;
    }

    // TODO: @GetMapping("/{id}") → 200 либо 404
    public ResponseEntity<Task01Model> one(@PathVariable Long id) {
        return null;
    }

    // TODO: @PutMapping("/{id}") → 200 либо 404
    public ResponseEntity<Task01Model> replace(@PathVariable Long id, @RequestParam String title) {
        return null;
    }

    // TODO: @DeleteMapping("/{id}") → 204 либо 404
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return null;
    }
}
