package m124_kafka_event_patterns.practice.task01;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Репозиторий задач — обычный CRUD (модуль 79).
 */
public interface TaskRepository01 extends JpaRepository<TaskEntity01, Long> {
}
