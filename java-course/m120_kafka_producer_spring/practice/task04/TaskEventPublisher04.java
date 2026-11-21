package m120_kafka_producer_spring.practice.task04;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * Сервис публикации событий: единственное место, которое знает имена топиков и правила ключей.
 *
 * TODO 1: реализуйте publish(Long taskId, TaskStatusChanged04 event):
 *           - ключ: taskId == null ? null : String.valueOf(taskId);
 *           - kafka.send(TOPIC, key, event);
 *           - в whenComplete напечатать partition/offset либо ошибку.
 * TODO 2: реализуйте flush(): kafka.flush() — чтобы учебный main не завершился раньше отправки.
 *
 * ЗАЧЕМ ОТДЕЛЬНЫЙ СЕРВИС: бизнес-код не должен знать про KafkaTemplate, имена топиков и
 *   правила формирования ключа — иначе смена контракта расползётся по всему приложению.
 */
@Service
public class TaskEventPublisher04 {

    static final String TOPIC = "task.status.changed";

    private final KafkaTemplate<String, Object> kafka;

    public TaskEventPublisher04(KafkaTemplate<String, Object> kafka) {
        this.kafka = kafka;
    }

    public void publish(Long taskId, TaskStatusChanged04 event) {
        String key = (taskId == null) ? null : String.valueOf(taskId);

        kafka.send(TOPIC, key, event).whenComplete((result, ex) -> {
            if (ex != null) {
                System.err.printf("ошибка отправки %s: %s%n", event, ex.getMessage());
                return;
            }
            var md = result.getRecordMetadata();
            System.out.printf("%s: %s -> %s ушло в partition %d, offset %d%n",
                    (key == null ? "без ключа" : key),
                    event.oldStatus(),
                    event.newStatus(),
                    md.partition(),
                    md.offset());
        });
    }

    public void flush() {
        kafka.flush();
    }
}
