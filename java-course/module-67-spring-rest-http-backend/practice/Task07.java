/**
 * Задача 07 — Модуль 67: МИНИ-ПРОЕКТ «HTTP-слой Task Tracker»
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: org.springframework.boot:spring-boot-starter-web:3.2.x (см. theory.md).
 *
 * ЦЕЛЬ: собрать корректный HTTP-слой для Task Tracker — правильные методы,
 *       статусы, заголовок Location, content negotiation. Логику храним в памяти.
 *
 * ЗАДАНИЕ (всё в одном файле):
 *
 *   Хранилище: Map<Long, Task07> + генератор id (AtomicLong). Task07 = record (id, title, status).
 *
 *   TrackerController07 (@RestController, @RequestMapping("/api/tasks")):
 *     1) list()    GET    /api/tasks                  → 200 + список задач
 *     2) get(id)   GET    /api/tasks/{id}             → 200 + задача, либо 404
 *     3) create()  POST   /api/tasks?title=...        → 201 + Location: /api/tasks/{newId}
 *                                                        + тело созданной задачи
 *     4) changeStatus(id) PATCH /api/tasks/{id}?status=DONE → 200 + обновлённая задача, либо 404
 *     5) delete(id) DELETE /api/tasks/{id}            → 204, либо 404 если не было
 *     6) summaryText() GET /api/tasks/summary (produces "text/plain")
 *                      → строка "Всего задач: <n>"
 *
 *   ТРЕБОВАНИЯ:
 *     - создание возвращает 201 + Location;
 *     - чтение несуществующей задачи — 404;
 *     - удаление — 204;
 *     - summary доступен как text/plain (content negotiation).
 *
 * АРХИТЕКТУРА:
 *
 *   [HTTP] ──► TrackerController07 ──► Map<Long, Task07> (хранилище в памяти)
 *               (методы, статусы, Location, Accept)
 *
 * ПОДСКАЗКА:
 *   Создание: id = seq.getAndIncrement(); store.put(id, ...);
 *             ResponseEntity.created(URI.create("/api/tasks/" + id)).body(task);
 *   PATCH статуса: достать задачу, создать новую запись с новым status, положить обратно.
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
public class Task07 {
    public static void main(String[] args) {
        SpringApplication.run(Task07.class, args);
    }
}

record Task07(Long id, String title, String status) {}

@RestController
@RequestMapping("/api/tasks")
class TrackerController07 {

    private final Map<Long, Task07> store = new ConcurrentHashMap<>();
    private final AtomicLong seq = new AtomicLong(1);

    // TODO: @GetMapping → 200 + список (new ArrayList<>(store.values()))
    public List<Task07> list() {
        return null;
    }

    // TODO: @GetMapping("/{id}") → 200 либо 404
    public ResponseEntity<Task07> get(@PathVariable Long id) {
        return null;
    }

    // TODO: @PostMapping → 201 + Location + тело
    public ResponseEntity<Task07> create(@RequestParam String title) {
        // TODO: id = seq.getAndIncrement(); new Task07(id, title, "NEW"); store.put(...)
        // TODO: ResponseEntity.created(URI.create("/api/tasks/" + id)).body(task)
        return null;
    }

    // TODO: @PatchMapping("/{id}") → 200 (обновлённая) либо 404
    public ResponseEntity<Task07> changeStatus(@PathVariable Long id, @RequestParam String status) {
        return null;
    }

    // TODO: @DeleteMapping("/{id}") → 204 либо 404
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return null;
    }

    // TODO: @GetMapping(value = "/summary", produces = "text/plain")
    public String summaryText() {
        // TODO: верните "Всего задач: " + store.size()
        return null;
    }
}
