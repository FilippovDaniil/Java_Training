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

public class Task07 {
    public static void main(String[] args) {
        SpringApplication.run(Task07.class, args);
    }
}
