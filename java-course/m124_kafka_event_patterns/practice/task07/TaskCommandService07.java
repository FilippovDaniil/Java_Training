package m124_kafka_event_patterns.practice.task07;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        // TODO 1
        return 0L;
    }

    @Transactional
    public void changeStatus(long id, String newStatus) {
        // TODO 2
    }
}
