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
    private Long taskId;

    protected TaskView04() {
    }

    // TODO
}
