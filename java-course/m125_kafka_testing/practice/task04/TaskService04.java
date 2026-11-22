package m125_kafka_testing.practice.task04;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.internals.RecordHeader;

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

    private static final String TOPIC = "task.created";

    private final AtomicLong sequence = new AtomicLong();
    private final KafkaTemplate<String, String> kafka;

    public TaskService04(KafkaTemplate<String, String> kafka) {
        this.kafka = kafka;
    }

    public long createTask(String title, long authorId) {
        // TODO 2: валидация ДО любых побочных эффектов — иначе «фантомное» событие
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("title must not be blank");
        }

        long id = sequence.incrementAndGet();

        ProducerRecord<String, String> record =
                new ProducerRecord<>(TOPIC, String.valueOf(id),
                        "{\"id\":" + id + ",\"title\":\"" + title + "\",\"authorId\":" + authorId + "}");
        record.headers().add(new RecordHeader("eventId",
                UUID.randomUUID().toString().getBytes()));

        kafka.send(record);
        return id;
    }
}
