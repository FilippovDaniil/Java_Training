package m108_spring_test_full_context.practice.task05;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import java.util.concurrent.atomic.AtomicInteger;
import static org.assertj.core.api.Assertions.assertThat;

// --- ТЕСТ (каркас) ---
// TODO: @org.springframework.boot.test.context.SpringBootTest(classes = Task05.class)
// TODO: @org.springframework.test.annotation.DirtiesContext(
// TODO:     classMode = org.springframework.test.annotation.DirtiesContext.ClassMode.AFTER_CLASS)
class DirtiesContextTest05 {

    // TODO: @Autowired CounterBean05 counter;

    @Test
    void mutates_state() {
        // TODO: counter.increment(); counter.increment();
        // TODO: assertThat(counter.value()).isEqualTo(2);
    }
}
