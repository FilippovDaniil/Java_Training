package m71_spring_rest_validation.practice.task07;

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

@Documented
// TODO: @Constraint(validatedBy = PriorityValidator07.class)
@Target({ElementType.FIELD, ElementType.PARAMETER, ElementType.RECORD_COMPONENT})
@Retention(RetentionPolicy.RUNTIME)
@interface ValidPriority {
    String message() default "Приоритет должен быть от 1 до 5";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
