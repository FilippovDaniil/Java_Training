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

class StatusValidator07 implements ConstraintValidator<ValidStatus, String> {
    private static final Set<String> ALLOWED = Set.of("NEW", "IN_PROGRESS", "DONE");
    @Override public boolean isValid(String v, ConstraintValidatorContext c) {
        // TODO: v == null || ALLOWED.contains(v)
        return false;
    }
}
