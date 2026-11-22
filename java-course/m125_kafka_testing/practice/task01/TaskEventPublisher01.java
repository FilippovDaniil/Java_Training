package m125_kafka_testing.practice.task01;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

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

    private static final Logger log = LoggerFactory.getLogger(TaskEventPublisher01.class);

    private final KafkaTemplate<String, Object> kafka;
    private final AtomicInteger failures = new AtomicInteger();

    public TaskEventPublisher01(KafkaTemplate<String, Object> kafka) {
        this.kafka = kafka;
    }

    /** Публикация события создания задачи: ключ = id задачи (обеспечивает порядок по партиции). */
    public void publishCreated(TaskCreated01 event) {
        kafka.send("task.created", String.valueOf(event.id()), event)
                .whenComplete((result, ex) -> {
                    if (ex != null) {
                        failures.incrementAndGet();
                        log.error("Не удалось опубликовать событие task.created для id={}", event.id(), ex);
                    }
                });
    }

    /** Публикация метрики: порядок не важен, ключ не задаём (null). */
    public void publishMetric(Object metric) {
        kafka.send("app.metrics", null, metric)
                .whenComplete((result, ex) -> {
                    if (ex != null) {
                        failures.incrementAndGet();
                        log.error("Не удалось опубликовать метрику", ex);
                    }
                });
    }

    /** Счётчик сбоев отправки (наблюдаемое состояние для теста). */
    public int failures() {
        return failures.get();
    }
}
