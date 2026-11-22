package m124_kafka_event_patterns.practice.task01;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Бизнес-сущность «задача».
 *
 * TODO: поля — Long id (@Id @GeneratedValue), String title, long authorId, String status ("NEW").
 *
 * ВАЖНО: эта сущность НИКОГДА не публикуется в Kafka. В событие уходит DTO (модуль 122),
 *   сериализованный в payload записи outbox.
 */
@Entity
@Table(name = "tasks")
public class TaskEntity01 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private long authorId;
    private String status;

    protected TaskEntity01() {
    }

    public TaskEntity01(String title, long authorId) {
        this.title = title;
        this.authorId = authorId;
        this.status = "NEW";
    }

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public long getAuthorId() { return authorId; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
