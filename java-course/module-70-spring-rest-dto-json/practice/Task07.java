/**
 * Задача 07 — Модуль 70: МИНИ-ПРОЕКТ «DTO-слой Task Tracker»
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: org.springframework.boot:spring-boot-starter-web:3.2.x (см. theory.md).
 *
 * ЦЕЛЬ: построить аккуратный DTO-слой Task Tracker: раздельные request/response DTO,
 *       маппер сущность→DTO (служебные поля наружу не идут), вложенный исполнитель,
 *       форматирование даты и единая обёртка ApiResponse<T>.
 *
 * ЗАДАНИЕ (всё в одном файле):
 *
 *   1) Сущность TaskEntity07 (как из БД): id, title, status, createdAt (LocalDateTime),
 *      assigneeId, assigneeName, internalNote (НАРУЖУ НЕЛЬЗЯ).
 *
 *   2) DTO:
 *        - AssigneeDto07(Long id, String name)
 *        - CreateTaskRequest07(String title, Long assigneeId)         — вход
 *        - TaskResponse07(Long id, String title, String status,
 *                         AssigneeDto07 assignee,
 *                         @JsonFormat(pattern="dd.MM.yyyy HH:mm") LocalDateTime createdAt) — выход
 *        - ApiResponse07<T>(T data, String message) с фабрикой ok(...)
 *
 *   3) Маппер TaskMapper07.toResponse(TaskEntity07 e):
 *        перенести публичные поля, собрать вложенный AssigneeDto07,
 *        internalNote НЕ переносить.
 *
 *   4) Контроллер TaskController07 (@RequestMapping("/api/tasks")):
 *        - GET /api/tasks/{id} → ApiResponse07<TaskResponse07> (через маппер из getStub())
 *        - POST /api/tasks (@RequestBody CreateTaskRequest07)
 *              → создать TaskEntity07 (id=1, status="NEW", createdAt=now, имя исполнителя "Иван"),
 *                смаппить и вернуть ApiResponse07<TaskResponse07>
 *
 * ПРОВЕРКА: в JSON-ответе НЕТ internalNote; дата отформатирована; есть обёртка data/message.
 *
 * АРХИТЕКТУРА:
 *
 *   TaskEntity07 ──TaskMapper07──► TaskResponse07 ──обёртка──► ApiResponse07<T> ──Jackson──► JSON
 *
 * ПОДСКАЗКА: повторно используйте идеи Task04 (маппер), Task05 (обёртка), Task06 (вложенность).
 */
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@SpringBootApplication
public class Task07 {
    public static void main(String[] args) {
        SpringApplication.run(Task07.class, args);
    }
}

// ============================================================
// «Сущность» (как из БД)
// ============================================================

class TaskEntity07 {
    Long id;
    String title;
    String status;
    LocalDateTime createdAt;
    Long assigneeId;
    String assigneeName;
    String internalNote; // НАРУЖУ НЕЛЬЗЯ
}

// ============================================================
// DTO (каркасы)
// ============================================================

// TODO: record AssigneeDto07(Long id, String name)
// TODO: record CreateTaskRequest07(String title, Long assigneeId)
// TODO: record TaskResponse07(Long id, String title, String status, AssigneeDto07 assignee,
//                             @JsonFormat(pattern="dd.MM.yyyy HH:mm") LocalDateTime createdAt)
// TODO: record ApiResponse07<T>(T data, String message) { static <T> ApiResponse07<T> ok(T d){...} }

// ============================================================
// Маппер (каркас)
// ============================================================

class TaskMapper07 {
    // TODO: static TaskResponse07 toResponse(TaskEntity07 e) {
    //   соберите AssigneeDto07(e.assigneeId, e.assigneeName),
    //   верните TaskResponse07(... без internalNote ...);
    // }
}

// ============================================================
// Контроллер (каркас)
// ============================================================

@RestController
@RequestMapping("/api/tasks")
class TaskController07 {

    private TaskEntity07 getStub() {
        TaskEntity07 e = new TaskEntity07();
        e.id = 1L; e.title = "Купить кофе"; e.status = "NEW";
        e.createdAt = LocalDateTime.of(2026, 6, 2, 14, 30);
        e.assigneeId = 7L; e.assigneeName = "Иван";
        e.internalNote = "СЛУЖЕБНОЕ";
        return e;
    }

    // TODO: @GetMapping("/{id}") → ApiResponse07<TaskResponse07>
    public Object getOne(@PathVariable Long id) {
        // TODO: return ApiResponse07.ok(TaskMapper07.toResponse(getStub()));
        return null;
    }

    // TODO: @PostMapping → создать сущность из запроса, смаппить, обернуть
    public Object create(/* @RequestBody CreateTaskRequest07 req */ Object req) {
        return null;
    }
}
