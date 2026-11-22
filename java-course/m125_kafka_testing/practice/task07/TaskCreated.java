package m125_kafka_testing.practice.task07;

import java.time.Instant;

public record TaskCreated(long id, String title, long authorId, Instant occurredAt, String priority) {
    public TaskCreated(long id, String title, long authorId, Instant occurredAt) {
        this(id, title, authorId, occurredAt, "NORMAL");
    }
}
