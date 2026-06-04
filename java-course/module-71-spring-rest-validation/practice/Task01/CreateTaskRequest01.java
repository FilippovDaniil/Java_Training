import jakarta.validation.constraints.*;
import java.time.LocalDate;

record CreateTaskRequest01(
        /* TODO: @NotBlank @Size(min = 3, max = 100) */ String title,
        /* TODO: @NotNull @Size(min = 2, max = 50) */ String assignee,
        /* TODO: @Min(1) @Max(5) */ int priority,
        /* TODO: @FutureOrPresent */ LocalDate dueDate
) {}
