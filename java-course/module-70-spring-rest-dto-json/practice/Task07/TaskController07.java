import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;

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
