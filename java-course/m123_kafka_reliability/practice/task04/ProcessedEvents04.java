package m123_kafka_reliability.practice.task04;

import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Component;

/**
 * Реестр обработанных событий (учебная замена таблицы processed_events).
 *
 * TODO 1: markProcessed(UUID eventId) — вернуть true, если ключ добавлен впервые (событие новое),
 *         false — если ключ уже был (дубль). Учтите потокобезопасность: слушатель может работать
 *         в несколько потоков (concurrency).
 * TODO 2: size() — сколько событий обработано (для проверки в эксперименте).
 * TODO 3 (продуктовый вариант, в комментарии): опишите DDL таблицы и SQL вставки:
 *         CREATE TABLE processed_events(event_id uuid PRIMARY KEY, processed_at timestamptz NOT NULL);
 *         INSERT INTO processed_events(event_id, processed_at) VALUES (?, now()) ON CONFLICT DO NOTHING;
 *         + периодическая чистка старых записей.
 *
 * ПОЧЕМУ Set.add(): он атомарно отвечает «был ли элемент» — это ровно семантика
 *   INSERT ... ON CONFLICT DO NOTHING, только в памяти (переживёт рестарт только БД-вариант).
 */
@Component
public class ProcessedEvents04 {

    private final Set<UUID> processed = ConcurrentHashMap.newKeySet();

    public boolean markProcessed(UUID eventId) {
        // TODO 1
        return true;
    }

    public int size() {
        // TODO 2
        return 0;
    }
}
