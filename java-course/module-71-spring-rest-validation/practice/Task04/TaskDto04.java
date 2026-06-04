import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

record TaskDto04(
        /* TODO: @Null(groups = OnCreate.class) @NotNull(groups = OnUpdate.class) */ Long id,
        /* TODO: @NotBlank(groups = {OnCreate.class, OnUpdate.class}) */ String title
) {}
