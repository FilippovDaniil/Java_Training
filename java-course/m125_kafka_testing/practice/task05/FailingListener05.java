package m125_kafka_testing.practice.task05;

import java.util.concurrent.atomic.AtomicInteger;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * Слушатель, воспроизводящий сбои для тестов ретраев.
 *
 * TODO 1: считать попытки в attempts (AtomicInteger).
 * TODO 2: значение "bad" -> RuntimeException("не могу обработать") (повторяемая ошибка).
 * TODO 3: значение "invalid" -> IllegalArgumentException("некорректные данные") (не повторяемая).
 * TODO 4: иначе увеличить счётчик успешных обработок.
 * TODO 5: методы attempts() и processed() для утверждений теста.
 */
@Component
public class FailingListener05 {

    private final AtomicInteger attempts = new AtomicInteger();
    private final AtomicInteger processed = new AtomicInteger();

    @KafkaListener(topics = "task.created", groupId = "retry-test")
    public void on(String value) {
        // TODO 1-4
    }

    public int attempts() {
        return attempts.get();
    }

    public int processed() {
        return processed.get();
    }
}
