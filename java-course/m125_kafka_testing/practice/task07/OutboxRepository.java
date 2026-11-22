package m125_kafka_testing.practice.task07;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OutboxRepository extends JpaRepository<OutboxEntity, Long> {
    long countBySentAtIsNull();
    List<OutboxEntity> findBySentAtIsNullOrderByOccurredAtAsc();
}
