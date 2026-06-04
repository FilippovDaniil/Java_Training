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

@RestController
@RequestMapping("/api/tasks")
class CustomValidationController05 {

    @PostMapping("/status")
    public String create(/* TODO: @Valid */ @RequestBody StatusDto05 dto) {
        // TODO: верните "Статус принят: " + dto.status()
        return null;
    }
}
