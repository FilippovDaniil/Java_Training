package m124_kafka_event_patterns.practice.task04;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.Instant;

/**
 * Модель чтения (materialized view) — денормализована под конкретный экран.
 *
 * TODO: поля — Long taskId (@Id, без генерации: ключ берём из события), String title,
 *       long authorId, String status, long lastEventVersion, Instant updatedAt.
 *
 * ПОЧЕМУ ID НЕ ГЕНЕРИРУЕТСЯ: проекция не создаёт сущности, она отражает уже существующие.
 *   Ключ приходит из события — это и есть основа идемпотентного upsert.
 */
@Entity
@Table(name = "task_view")
public class TaskView04 {

    @Id
    private Long taskId;          // ключ из события, НЕ генерируется

    private String title;
    private long authorId;
    private String status;
    private long lastEventVersion;
    private Instant updatedAt;

    protected TaskView04() {
    }

    public static TaskView04 partial(Long taskId) {
        TaskView04 v = new TaskView04();
        v.taskId = taskId;
        v.status = "UNKNOWN";
        v.lastEventVersion = -1;
        v.updatedAt = Instant.now();
        return v;
    }

    public Long getTaskId() { return taskId; }
    public String getTitle() { return title; }
    public long getAuthorId() { return authorId; }
    public String getStatus() { return status; }
    public long getLastEventVersion() { return lastEventVersion; }
    public Instant getUpdatedAt() { return updatedAt; }

    public void setTitle(String title) { this.title = title; }
    public void setAuthorId(long authorId) { this.authorId = authorId; }
    public void setStatus(String status) { this.status = status; }
    public void setLastEventVersion(long lastEventVersion) { this.lastEventVersion = lastEventVersion; }
    public void setUpdatedAt(Instant updatedAt) { this.updatedAt = updatedAt; }
}
