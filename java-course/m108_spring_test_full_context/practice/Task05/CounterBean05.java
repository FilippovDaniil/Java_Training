package m108_spring_test_full_context.practice.task05;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import java.util.concurrent.atomic.AtomicInteger;
import static org.assertj.core.api.Assertions.assertThat;

@Component
class CounterBean05 {
    private final AtomicInteger value = new AtomicInteger();
    void increment() { value.incrementAndGet(); }
    int value() { return value.get(); }
}
