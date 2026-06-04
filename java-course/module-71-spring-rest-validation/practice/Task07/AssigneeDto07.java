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

// ============================================================
// DTO (каркасы)
// ============================================================
record AssigneeDto07(
        /* TODO: @NotNull */ Long id,
        /* TODO: @NotBlank @Size(max = 50) */ String name
) {}
