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

    protected TaskEntity01() {
    }

    // TODO
}
