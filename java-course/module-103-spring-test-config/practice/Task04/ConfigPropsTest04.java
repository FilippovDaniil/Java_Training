import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

// --- ТЕСТ (каркас) ---
// TODO: @org.springframework.boot.test.context.SpringBootTest(classes = Task04.class)
// TODO: @org.springframework.test.context.TestPropertySource(properties = {
// TODO:     "app.max-tasks=7", "app.notifications=true"})
class ConfigPropsTest04 {

    // TODO: @org.springframework.beans.factory.annotation.Autowired AppProps04 props;

    @Test
    void binds_fields() {
        // TODO: assertThat(props.maxTasks()).isEqualTo(7);
        // TODO: assertThat(props.notifications()).isTrue();
    }
}
