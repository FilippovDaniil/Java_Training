package m124_kafka_event_patterns.practice.task02;

import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.Limit;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Выборка неотправленных записей для релэя.
 *
 * TODO 1: List<OutboxMessage02> findBySentAtIsNullOrderByOccurredAtAsc(Limit limit);
 * TODO 2: long countBySentAtIsNull();
 * TODO 3 (PostgreSQL): нативный запрос с FOR UPDATE SKIP LOCKED для нескольких реплик релэя.
 */
public interface OutboxRepository02 extends JpaRepository<OutboxMessage02, UUID> {

    // TODO 1-3
}
