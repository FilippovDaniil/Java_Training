package m124_kafka_event_patterns.practice.task07;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * Write-сторона: команды меняют состояние и записывают события в outbox одной транзакцией.
 *
 * TODO 1: create(title, authorId) — сохранить задачу (status "NEW", version 1) + событие
 *         "task.created" в outbox; вернуть id.
 * TODO 2: changeStatus(id, newStatus) — обновить статус и version++, записать событие
 *         "task.status.changed" (payload включает version — проектору нужна именно она).
 * TODO 3: НИ ОДНОГО обращения к KafkaTemplate в этом классе.
 *
 * ПОЧЕМУ VERSION В СОБЫТИИ: проекция может получить события повторно и не по порядку.
 *   Версия сущности даёт проектору однозначный критерий «применять или игнорировать»,
 *   не завязанный на офсеты и время брокера.
 */
@Service
public class TaskCommandService07 {

    private final TaskRepository07 tasks;
    private final OutboxRepository07 outbox;
    private final ObjectMapper objectMapper;

    public TaskCommandService07(TaskRepository07 tasks, OutboxRepository07 outbox, ObjectMapper objectMapper) {
        this.tasks = tasks;
        this.outbox = outbox;
        this.objectMapper = objectMapper;
    }

    @Transactional
    public long create(String title, long authorId) {
        TaskEntity07 task = tasks.save(new TaskEntity07(title, authorId));

        try {
            String payload = objectMapper.writeValueAsString(Map.of(
                    "taskId", task.getId(),
                    "title", task.getTitle(),
                    "authorId", task.getAuthorId(),
                    "status", task.getStatus(),
                    "version", task.getVersion()));
            outbox.save(OutboxMessage07.of(
                    "task.created", "task.created",
                    String.valueOf(task.getId()), payload));
        } catch (Exception e) {
            throw new RuntimeException("не удалось сериализовать", e);
        }

        return task.getId();
    }

    @Transactional
    public void changeStatus(long id, String newStatus) {
        TaskEntity07 task = tasks.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("задача не найдена: " + id));

        String oldStatus = task.getStatus();
        task.setStatus(newStatus);   // version++

        try {
            String payload = objectMapper.writeValueAsString(Map.of(
                    "taskId", id,
                    "oldStatus", oldStatus,
                    "newStatus", newStatus,
                    "version", task.getVersion()));
            outbox.save(OutboxMessage07.of(
                    "task.status.changed", "task.status.changed",
                    String.valueOf(id), payload));
        } catch (Exception e) {
            throw new RuntimeException("не удалось сериализовать", e);
        }
    }
}
