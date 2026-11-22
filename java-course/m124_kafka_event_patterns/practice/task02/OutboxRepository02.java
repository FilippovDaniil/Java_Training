package m124_kafka_event_patterns.practice.task02;

import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.Limit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Выборка неотправленных записей для релэя.
 *
 * TODO 1: List<OutboxMessage02> findBySentAtIsNullOrderByOccurredAtAsc(Limit limit);
 * TODO 2: long countBySentAtIsNull();
 * TODO 3 (PostgreSQL): нативный запрос с FOR UPDATE SKIP LOCKED для нескольких реплик релэя.
 */
public interface OutboxRepository02 extends JpaRepository<OutboxMessage02, UUID> {

    List<OutboxMessage02> findBySentAtIsNullOrderByOccurredAtAsc(Limit limit);

    long countBySentAtIsNull();

    // для PostgreSQL: несколько реплик релэя не отправляют одни и те же записи
    @Query(value = "SELECT * FROM outbox2 WHERE sent_at IS NULL "
            + "ORDER BY occurred_at LIMIT :limit FOR UPDATE SKIP LOCKED",
            nativeQuery = true)
    List<OutboxMessage02> lockUnsent(@Param("limit") int limit);
}
