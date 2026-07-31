package m124_kafka_event_patterns.practice.task04;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Репозиторий модели чтения.
 *
 * TODO 1: List<TaskView04> findByStatus(String status);
 * TODO 2: @Query("select v.status, count(v) from TaskView04 v group by v.status")
 *         List<Object[]> countByStatus();
 * TODO 3: подумайте, какие индексы нужны под эти запросы (status; при больших объёмах —
 *         составной (status, updatedAt)) — и запишите вывод комментарием.
 */
public interface TaskViewRepository04 extends JpaRepository<TaskView04, Long> {

    // TODO 1-2
}
