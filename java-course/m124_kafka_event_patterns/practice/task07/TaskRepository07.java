package m124_kafka_event_patterns.practice.task07;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Репозиторий команд (write-сторона).
 */
public interface TaskRepository07 extends JpaRepository<TaskEntity07, Long> {
}
