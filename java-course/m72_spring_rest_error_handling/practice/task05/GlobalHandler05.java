package m72_spring_rest_error_handling.practice.task05;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

// TODO: @RestControllerAdvice
class GlobalHandler05 {

    // TODO: @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleValidation(MethodArgumentNotValidException ex) {
        // TODO: ProblemDetail pd = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        // TODO: pd.setTitle("Ошибка валидации");
        // TODO: pd.setProperty("timestamp", Instant.now());
        Map<String, String> errors = new HashMap<>();
        // TODO: заполнить errors из ex.getBindingResult().getFieldErrors()
        // TODO: pd.setProperty("errors", errors);
        // TODO: return pd;
        return null;
    }
}
