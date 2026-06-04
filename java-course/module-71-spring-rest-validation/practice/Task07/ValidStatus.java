import jakarta.validation.*;
import jakarta.validation.constraints.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import java.lang.annotation.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

// ============================================================
// Кастомные ограничения (каркасы)
// ============================================================

@Documented
// TODO: @Constraint(validatedBy = StatusValidator07.class)
@Target({ElementType.FIELD, ElementType.PARAMETER, ElementType.RECORD_COMPONENT})
@Retention(RetentionPolicy.RUNTIME)
@interface ValidStatus {
    String message() default "Недопустимый статус";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
