import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.*;

// TODO: @RestControllerAdvice
class GlobalHandler04 {

    // TODO: @ExceptionHandler(TaskNotFoundException04.class)
    public ProblemDetail handle(TaskNotFoundException04 ex) {
        // TODO: ProblemDetail pd = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
        // TODO: pd.setTitle("Задача не найдена");
        // TODO: pd.setProperty("taskId", ex.getTaskId());
        // TODO: return pd;
        return null;
    }
}
