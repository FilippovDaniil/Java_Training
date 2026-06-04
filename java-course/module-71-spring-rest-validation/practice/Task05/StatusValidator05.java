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

// --- Валидатор (каркас) ---
class StatusValidator05 implements ConstraintValidator<ValidStatus, String> {
    private static final Set<String> ALLOWED = Set.of("NEW", "IN_PROGRESS", "DONE");

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        // TODO: вернуть value == null || ALLOWED.contains(value)
        return false;
    }
}
