import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.*;

class TaskNotFoundException04 extends RuntimeException {
    private final Long taskId;
    TaskNotFoundException04(Long id) { super("Задача " + id + " не найдена"); this.taskId = id; }
    public Long getTaskId() { return taskId; }
}
