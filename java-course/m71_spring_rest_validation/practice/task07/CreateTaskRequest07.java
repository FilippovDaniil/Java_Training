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

record CreateTaskRequest07(
        /* TODO: @NotBlank @Size(min = 3, max = 100) */ @NotBlank @Size(min = 3, max = 100) String title,
        /* TODO: @ValidStatus */ @ValidStatus String status,
        /* TODO: @ValidPriority */ @ValidPriority Integer priority,
        /* TODO: @Valid @NotNull */ @Valid @NotNull AssigneeDto07 assignee
) {}
