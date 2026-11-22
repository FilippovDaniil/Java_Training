package m125_kafka_testing.practice.task07;

import jakarta.transaction.Transactional;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.concurrent.TimeUnit;

@Component
public class OutboxRelay {

    private final OutboxRepository outbox;
    private final KafkaTemplate<String, Object> kafka;

    public OutboxRelay(OutboxRepository outbox, KafkaTemplate<String, Object> kafka) {
        this.outbox = outbox; this.kafka = kafka;
    }

    @Scheduled(fixedDelayString = "${app.outbox.relay.delay:1000}")
    public void relay() {
        relayOnce();
    }

    @Transactional
    public void relayOnce() {
        for (OutboxEntity row : outbox.findBySentAtIsNullOrderByOccurredAtAsc()) {
            try {
                kafka.send("task.created", row.getAggregateId(), row.getPayload())
                        .get(5, TimeUnit.SECONDS);        // ждём подтверждения
                row.setSentAt(Instant.now());           // ТОЛЬКО после успеха
            } catch (Exception e) {
                row.setAttempts(row.getAttempts() + 1); // не помечаем sentAt
            }
            outbox.save(row);
        }
    }
}
