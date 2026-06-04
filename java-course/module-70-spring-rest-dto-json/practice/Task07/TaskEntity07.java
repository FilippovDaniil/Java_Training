import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;

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
