package m103_spring_test_config.practice.task06;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

// --- ТЕСТ (каркас) ---
// TODO: @org.springframework.boot.test.context.SpringBootTest(classes = Task06.class)
class DynamicPropsTest06 {

    // TODO: @DynamicPropertySource
    // TODO: static void props(DynamicPropertyRegistry registry) {
    // TODO:     registry.add("broker.url", FakeBroker06::url);
    // TODO: }

    // TODO: @Value("${broker.url}") String url;

    @Test
    void dynamic_prop_resolved() {
        // TODO: assertThat(url).isEqualTo("tcp://localhost:61616");
    }
}
