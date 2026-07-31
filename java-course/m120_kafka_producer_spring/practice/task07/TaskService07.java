package m120_kafka_producer_spring.practice.task07;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Service;

/**
 * Бизнес-логика задач: сохраняет состояние и публикует события об изменениях.
 *
 * TODO 1: create(String title, long authorId):
 *           - id = sequence.incrementAndGet(); сохранить статус "NEW" в store;
 *           - опубликовать TaskCreated07 (occurredAt = Instant.now());
 *           - вернуть id.
 * TODO 2: changeStatus(long id, String newStatus):
 *           - взять старый статус из store (нет задачи -> исключение);
 *           - сохранить новый;
 *           - опубликовать TaskStatusChanged07(id, oldStatus, newStatus, Instant.now()).
 *
 * ВАЖНО (порядок действий): сначала фиксируем изменение состояния, потом публикуем событие.
 *   В реальном проекте с БД — публикация ПОСЛЕ коммита транзакции; иначе возможна ситуация
 *   «событие ушло, транзакция откатилась» (лечится outbox-паттерном, модуль 124).
 *
 * ПОДСКАЗКА: store здесь — учебная замена репозитория; хранит id -> статус.
 */
@Service
public class TaskService07 {

    private final Map<Long, String> store = new ConcurrentHashMap<>();
    private final AtomicLong sequence = new AtomicLong();
    private final TaskEventPublisher07 publisher;

    public TaskService07(TaskEventPublisher07 publisher) {
        this.publisher = publisher;
    }

    public long create(String title, long authorId) {
        // TODO 1
        return 0L;
    }

    public void changeStatus(long id, String newStatus) {
        // TODO 2
    }
}
