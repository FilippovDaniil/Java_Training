package m103_spring_test_config.practice.task01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Value;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

// --- ТЕСТ (каркас) ---
// TODO: @org.springframework.boot.test.context.SpringBootTest(classes = Task01.class)
// TODO: @org.springframework.test.context.ActiveProfiles("test")
@SpringBootTest(classes = Task01.class)
@ActiveProfiles("test")
class ProfileTest01 {

    // TODO: @Value("${app.environment}") String env;
    // TODO: @Value("${app.max-tasks}") int maxTasks;
    @Value("${app.environment}") String env;
    @Value("${app.max-tasks}") int maxTasks;

    @Test
    void profile_props_loaded() {
        // TODO: assertThat(env).isEqualTo("test");
        // TODO: assertThat(maxTasks).isEqualTo(3);
        assertThat(env).isEqualTo("test");
        assertThat(maxTasks).isEqualTo(3);
    }
}
