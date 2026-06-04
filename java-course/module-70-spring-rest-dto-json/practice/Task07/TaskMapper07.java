import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;

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
