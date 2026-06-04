import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;
import jakarta.validation.Valid;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import java.lang.annotation.*;
import java.util.Set;

// --- Аннотация ограничения (каркас) ---
@Documented
// TODO: @Constraint(validatedBy = StatusValidator05.class)
@Target({ElementType.FIELD, ElementType.PARAMETER, ElementType.RECORD_COMPONENT})
@Retention(RetentionPolicy.RUNTIME)
@interface ValidStatus {
    String message() default "Недопустимый статус";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
