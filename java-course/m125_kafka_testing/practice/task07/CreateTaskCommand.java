package m125_kafka_testing.practice.task07;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class CreateTaskCommand {

    private final TaskRepository tasks;
    private final OutboxRepository outbox;
    private final ObjectMapper mapper;

    public CreateTaskCommand(TaskRepository tasks, OutboxRepository outbox, ObjectMapper mapper) {
        this.tasks = tasks; this.outbox = outbox; this.mapper = mapper;
    }

    @Transactional
    public long create(String title, long authorId) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("title must not be blank");
        }
        long id = System.currentTimeMillis(); // в реальности — sequence/БД
        tasks.save(new TaskEntity());

        OutboxEntity row = new OutboxEntity();
        row.setAggregateId(String.valueOf(id));
        row.setEventType("task.created");
        try {
            row.setPayload(mapper.writeValueAsString(
                    new TaskCreated(id, title, authorId, Instant.now())));
        } catch (Exception e) { throw new IllegalStateException(e); }
        row.setOccurredAt(Instant.now());
        outbox.save(row);
        return id;
    }
}
