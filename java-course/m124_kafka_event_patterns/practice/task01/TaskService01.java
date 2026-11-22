package m124_kafka_event_patterns.practice.task01;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

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
        TaskEntity01 task = tasks.save(new TaskEntity01(title, authorId));

        try {
            String payload = objectMapper.writeValueAsString(Map.of(
                    "taskId", task.getId(),
                    "title", title,
                    "authorId", authorId,
                    "status", task.getStatus()));
            outbox.save(OutboxMessage01.of(
                    "task.created", "task.created", String.valueOf(task.getId()), payload));
        } catch (Exception e) {
            throw new RuntimeException("не удалось сериализовать событие", e);
        }

        // для эксперимента 4: раскомментировать и проверить откат
        // if (true) throw new RuntimeException("эксперимент: откат обеих вставок");

        return task.getId();
    }

    @Transactional
    public void changeStatus(long id, String newStatus) {
        TaskEntity01 task = tasks.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("задача не найдена: " + id));

        String oldStatus = task.getStatus();
        task.setStatus(newStatus);

        try {
            String payload = objectMapper.writeValueAsString(Map.of(
                    "taskId", id,
                    "oldStatus", oldStatus,
                    "newStatus", newStatus));
            outbox.save(OutboxMessage01.of(
                    "task.status.changed", "task.status.changed", String.valueOf(id), payload));
        } catch (Exception e) {
            throw new RuntimeException("не удалось сериализовать событие", e);
        }
    }
}
