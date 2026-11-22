package m123_kafka_reliability.practice.task07;

import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Реестр обработанных событий с метриками.
 *
 * TODO 1: markProcessed(UUID) — true, если событие новое; false для дубля.
 * TODO 2: processedCount() / duplicateCount() — счётчики для /api/dlt/stats и проверки сценариев.
 * TODO 3 (прод-вариант в комментарии): таблица processed_events + ON CONFLICT DO NOTHING
 *         в одной транзакции с эффектом + чистка записей старше 30 дней.
 */
@Component
public class ProcessedEvents07 {

    private final Set<UUID> processed = ConcurrentHashMap.newKeySet();
    private final AtomicInteger duplicates = new AtomicInteger();

    public boolean markProcessed(UUID eventId) {
        boolean isNew = processed.add(eventId);
        if (!isNew) {
            duplicates.incrementAndGet();
        }
        return isNew;
    }

    public int processedCount() {
        return processed.size();
    }

    public int duplicateCount() {
        return duplicates.get();
    }
}
