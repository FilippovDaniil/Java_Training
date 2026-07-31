package m124_kafka_event_patterns.practice.task07;

import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.Limit;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Репозиторий outbox капстоуна.
 *
 * TODO 1: findBySentAtIsNullOrderByOccurredAtAsc(Limit limit).
 * TODO 2: countBySentAtIsNull() — метрика для /api/ops.
 */
public interface OutboxRepository07 extends JpaRepository<OutboxMessage07, UUID> {

    // TODO 1-2
}
