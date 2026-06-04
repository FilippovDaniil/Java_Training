import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

@Service
class QuotaService07 {
    private final int maxTasks;
    QuotaService07(@Value("${app.max-tasks:100}") int maxTasks) { this.maxTasks = maxTasks; }
    boolean canAdd(int currentCount) { return currentCount < maxTasks; }
}
