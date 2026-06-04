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

// --- Валидатор (каркас) ---
class PeriodValidator06 implements ConstraintValidator<StartBeforeEnd, PeriodDto06> {
    @Override
    public boolean isValid(PeriodDto06 dto, ConstraintValidatorContext context) {
        // TODO: true, если start == null || end == null || !start.isAfter(end)
        return false;
    }
}
