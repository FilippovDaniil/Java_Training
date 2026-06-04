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

// TODO: @RestController + @RequestMapping("/api/users")
class RegistrationController04 {

    // TODO: @PostMapping
    public String register(/* TODO: @Valid @RequestBody */ CreateUserDto dto) {
        // TODO: верните "Пользователь " + dto.name() + " зарегистрирован"
        return null;
    }
}
