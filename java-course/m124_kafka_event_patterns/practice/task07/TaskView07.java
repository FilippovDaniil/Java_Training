package m124_kafka_event_patterns.practice.task07;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

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

    protected TaskView07() {
    }

    // TODO
}
