package m123_kafka_reliability.practice.task07;

import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Component;

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

    public boolean markProcessed(UUID eventId) {
        // TODO 1
        return true;
    }

    public int processedCount() {
        // TODO 2
        return 0;
    }

    public int duplicateCount() {
        // TODO 2
        return 0;
    }
}
