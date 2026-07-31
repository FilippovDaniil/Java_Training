package m125_kafka_testing.practice.task01;

import java.util.concurrent.atomic.AtomicInteger;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * Проверяемый класс: публикация событий задач.
 *
 * TODO 1: publishCreated(TaskCreated01 event) — send("task.created", String.valueOf(event.id()), event),
 *         в whenComplete при ошибке увеличить failures и залогировать.
 * TODO 2: publishMetric(Object metric) — send("app.metrics", null, metric): порядок не нужен.
 * TODO 3: failures() — счётчик сбоев отправки (его и проверяет тест).
 *
 * ЗАЧЕМ СЧЁТЧИК: тест не должен читать логи. Наблюдаемое состояние (счётчик, метрика) — это
 *   способ сделать поведение проверяемым; в проде тот же счётчик уходит в Micrometer (модуль 126).
 */
@Service
public class TaskEventPublisher01 {

    private final KafkaTemplate<String, Object> kafka;
    private final AtomicInteger failures = new AtomicInteger();

    public TaskEventPublisher01(KafkaTemplate<String, Object> kafka) {
        this.kafka = kafka;
    }

    public void publishCreated(TaskCreated01 event) {
        // TODO 1
    }

    public void publishMetric(Object metric) {
        // TODO 2
    }

    public int failures() {
        return failures.get();
    }
}
