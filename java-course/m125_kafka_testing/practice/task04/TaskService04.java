package m125_kafka_testing.practice.task04;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * Сервис под тестом: создаёт задачу и публикует событие.
 *
 * TODO 1: createTask(String title, long authorId):
 *           - валидация: пустой title -> IllegalArgumentException ДО публикации;
 *           - id = sequence.incrementAndGet();
 *           - собрать ProducerRecord с ключом String.valueOf(id) и заголовком eventId (UUID);
 *           - отправить в "task.created"; вернуть id.
 * TODO 2: убедитесь, что при исключении валидации отправки не происходит вообще
 *         (порядок операций в методе имеет значение).
 */
@Service
public class TaskService04 {

    private final AtomicLong sequence = new AtomicLong();
    private final KafkaTemplate<String, String> kafka;

    public TaskService04(KafkaTemplate<String, String> kafka) {
        this.kafka = kafka;
    }

    public long createTask(String title, long authorId) {
        // TODO 1-2
        return 0L;
    }
}
