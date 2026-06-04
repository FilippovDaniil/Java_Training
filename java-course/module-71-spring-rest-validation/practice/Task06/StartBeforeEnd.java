import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;
import jakarta.validation.Valid;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import java.lang.annotation.*;
import java.time.LocalDate;

// --- Ограничение уровня класса (каркас) ---
@Documented
// TODO: @Constraint(validatedBy = PeriodValidator06.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@interface StartBeforeEnd {
    String message() default "Дата начала позже даты конца";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
