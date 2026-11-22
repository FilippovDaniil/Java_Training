package m124_kafka_event_patterns.practice.task07;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Репозиторий модели чтения (сущность — в TaskView07.java).
 *
 * TODO 1: List<TaskView07> findByStatus(String status);
 * TODO 2: запрос «количество задач по статусам» (@Query с group by) для /api/tasks/stats.
 */
public interface TaskViewRepository07 extends JpaRepository<TaskView07, Long> {
    List<TaskView07> findByStatus(String status);

    @Query("select v.status, count(v) from TaskView07 v group by v.status")
    List<Object[]> countByStatus();
}
