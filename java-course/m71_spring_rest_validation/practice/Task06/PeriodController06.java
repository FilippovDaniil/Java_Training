package m71_spring_rest_validation.practice.task06;

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

@RestController
@RequestMapping("/api/periods")
class PeriodController06 {

    @PostMapping
    public String create(/* TODO: @Valid */ @RequestBody PeriodDto06 dto) {
        // TODO: верните "Период принят: " + dto.start() + " — " + dto.end()
        return null;
    }
}
