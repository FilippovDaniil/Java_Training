import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

@Component
class AppInfo03 {
    private final String name;
    AppInfo03(@Value("${app.name:unknown}") String name) { this.name = name; }
    String name() { return name; }
}
