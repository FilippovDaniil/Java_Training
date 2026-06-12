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

class PriorityValidator07 implements ConstraintValidator<ValidPriority, Integer> {
    @Override public boolean isValid(Integer v, ConstraintValidatorContext c) {
        // TODO: v == null || (v >= 1 && v <= 5)
        return false;
    }
}
