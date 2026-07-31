package m124_kafka_event_patterns.practice.task01;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Бизнес-сервис: пишет задачу и событие ОДНОЙ транзакцией.
 *
 * TODO 1: @Transactional createTask(String title, long authorId):
 *           - tasks.save(new TaskEntity01(title, authorId));
 *           - payload = objectMapper.writeValueAsString(Map/record с полями события);
 *           - outbox.save(OutboxMessage01.of("task.created", "task.created",
 *                          String.valueOf(task.getId()), payload));
 *           - вернуть id задачи.
 * TODO 2: changeStatus(long id, String newStatus) — обновить задачу и записать событие
 *           "task.status.changed" в outbox (той же транзакцией).
 * TODO 3 (эксперимент задания 4): в конце метода бросить RuntimeException и проверить,
 *           что в БД не осталось ни задачи, ни записи outbox.
 *
 * ЗАПРЕЩЕНО В ЭТОМ КЛАССЕ: любые обращения к KafkaTemplate. Транзакция БД не умеет
 *   откатывать отправленное сообщение — именно поэтому существует outbox.
 */
@Service
public class TaskService01 {

    private final TaskRepository01 tasks;
    private final OutboxRepository01 outbox;
    private final ObjectMapper objectMapper;

    public TaskService01(TaskRepository01 tasks, OutboxRepository01 outbox, ObjectMapper objectMapper) {
        this.tasks = tasks;
        this.outbox = outbox;
        this.objectMapper = objectMapper;
    }

    @Transactional
    public long createTask(String title, long authorId) {
        // TODO 1
        return 0L;
    }

    @Transactional
    public void changeStatus(long id, String newStatus) {
        // TODO 2
    }
}
