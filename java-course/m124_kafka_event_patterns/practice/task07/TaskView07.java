package m124_kafka_event_patterns.practice.task07;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.Instant;

/**
 * Модель чтения (проекция): производная от потока событий, её можно удалить и пересобрать.
 *
 * TODO: поля — Long taskId (@Id, ключ из события), String title, long authorId,
 *       String status, long version, java.time.Instant updatedAt.
 */
@Entity
@Table(name = "task_view7")
public class TaskView07 {

    @Id
    private Long taskId;

    private String title;
    private long authorId;
    private String status;
    private long version;
    private Instant updatedAt;

    protected TaskView07() {
    }

    public static TaskView07 partial(Long taskId) {
        TaskView07 v = new TaskView07();
        v.taskId = taskId;
        v.status = "UNKNOWN";
        v.version = -1;
        v.updatedAt = Instant.now();
        return v;
    }

    public Long getTaskId() { return taskId; }
    public String getTitle() { return title; }
    public long getAuthorId() { return authorId; }
    public String getStatus() { return status; }
    public long getVersion() { return version; }
    public Instant getUpdatedAt() { return updatedAt; }

    public void setTitle(String title) { this.title = title; }
    public void setAuthorId(long authorId) { this.authorId = authorId; }
    public void setStatus(String status) { this.status = status; }
    public void setVersion(long version) { this.version = version; }
    public void setUpdatedAt(Instant updatedAt) { this.updatedAt = updatedAt; }
}
