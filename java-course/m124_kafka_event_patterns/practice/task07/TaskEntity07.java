package m124_kafka_event_patterns.practice.task07;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Источник правды команд.
 *
 * TODO: поля — Long id, String title, long authorId, String status, long version
 *       (version увеличивается при каждом изменении и попадает в событие — по ней проектор
 *        отличает свежее событие от повторного/устаревшего).
 */
@Entity
@Table(name = "tasks7")
public class TaskEntity07 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    protected TaskEntity07() {
    }

    // TODO
}
