import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// TODO: разметьте поля аннотациями валидации
record CreateUserDto(
        /* TODO: @NotBlank @Size(min = 2, max = 50) */ String name,
        /* TODO: @NotBlank @Email */ String email,
        /* TODO: @Min(18) @Max(120) */ int age
) {}
