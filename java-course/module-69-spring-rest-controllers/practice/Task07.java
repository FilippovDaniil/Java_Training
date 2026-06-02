/**
 * Задача 07 — Модуль 69: МИНИ-ПРОЕКТ «Контроллер Task Tracker со всеми источниками»
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: org.springframework.boot:spring-boot-starter-web:3.2.x (см. theory.md).
 *
 * ЦЕЛЬ: собрать рабочий контроллер Task Tracker, применив весь арсенал модуля 69 —
 *       шаблоны путей, @PathVariable/@RequestParam/@RequestBody/@RequestHeader,
 *       enum-конвертацию, ResponseEntity и @ResponseStatus.
 *
 * ЗАДАНИЕ (хранилище в памяти — Map<Long, Task07>):
 *
 *   TaskController07 (@RestController, @RequestMapping("/api/tasks")):
 *     1) list(status, page, size):
 *          GET /api/tasks?status=DONE&page=0&size=20
 *          - status: @RequestParam(required=false) Status07 (enum, конвертация)
 *          - page/size: @RequestParam с defaultValue
 *          → вернуть список (при заданном status — отфильтровать)
 *     2) getOne(id):
 *          GET /api/tasks/{id} → 200 + задача либо 404 (ResponseEntity)
 *     3) create(auth, dto):
 *          POST /api/tasks, Header Authorization, @RequestBody CreateDto07
 *          @ResponseStatus(CREATED) → создать и вернуть задачу (201)
 *     4) patchStatus(id, dto):
 *          PATCH /api/tasks/{id}, @RequestBody StatusDto07
 *          → обновить статус, вернуть задачу либо 404
 *     5) delete(id):
 *          DELETE /api/tasks/{id}, @ResponseStatus(NO_CONTENT) → удалить (204)
 *
 * АРХИТЕКТУРА:
 *
 *   [HTTP] ──► TaskController07 ──► Map<Long, Task07>
 *     (PathVariable/RequestParam/RequestBody/RequestHeader, enum, статусы)
 *
 * ПОДСКАЗКА:
 *   Status07 — enum {NEW, IN_PROGRESS, DONE}; фильтрация: stream().filter(t -> t.status()==status).
 *   id-генератор: AtomicLong seq.
 */
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@SpringBootApplication
public class Task07 {
    public static void main(String[] args) {
        SpringApplication.run(Task07.class, args);
    }
}

enum Status07 { NEW, IN_PROGRESS, DONE }

record Task07Model(Long id, String title, Status07 status) {}
record CreateDto07(String title) {}
record StatusDto07(Status07 status) {}

@RestController
@RequestMapping("/api/tasks")
class TaskController07 {

    private final Map<Long, Task07Model> store = new ConcurrentHashMap<>();
    private final AtomicLong seq = new AtomicLong(1);

    // TODO: @GetMapping — фильтр по статусу + page/size
    public List<Task07Model> list(
            /* @RequestParam(required = false) */ Status07 status,
            /* @RequestParam(defaultValue = "0") */ int page,
            /* @RequestParam(defaultValue = "20") */ int size) {
        // TODO: если status != null — отфильтровать, иначе вернуть все
        return null;
    }

    // TODO: @GetMapping("/{id}") → 200 либо 404
    public ResponseEntity<Task07Model> getOne(@PathVariable Long id) {
        return null;
    }

    // TODO: @PostMapping + @ResponseStatus(HttpStatus.CREATED)
    public Task07Model create(
            /* @RequestHeader(value="Authorization", required=false) */ String auth,
            /* @RequestBody */ CreateDto07 dto) {
        // TODO: id = seq.getAndIncrement(); new Task07Model(id, dto.title(), Status07.NEW); store.put(...)
        return null;
    }

    // TODO: @PatchMapping("/{id}") → 200 либо 404
    public ResponseEntity<Task07Model> patchStatus(@PathVariable Long id, /* @RequestBody */ StatusDto07 dto) {
        return null;
    }

    // TODO: @DeleteMapping("/{id}") + @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        // TODO: store.remove(id)
    }
}
