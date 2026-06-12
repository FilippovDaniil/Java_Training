package m71_spring_rest_validation.practice.task05;

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

// --- DTO с кастомным ограничением ---
record StatusDto05(/* TODO: @ValidStatus */ String status) {}
