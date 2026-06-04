import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Value;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

// --- ТЕСТ (каркас) ---
// TODO: @org.springframework.boot.test.context.SpringBootTest(classes = Task02.class)
// TODO: @org.springframework.test.context.TestPropertySource(properties = {
// TODO:     "app.max-tasks=10",
// TODO:     "app.feature.notifications=false"
// TODO: })
class PropertySourceTest02 {

    // TODO: @Value("${app.max-tasks}") int maxTasks;
    // TODO: @Value("${app.feature.notifications}") boolean notif;

    @Test
    void overrides_props() {
        // TODO: assertThat(maxTasks).isEqualTo(10);
        // TODO: assertThat(notif).isFalse();
    }
}
