package m124_kafka_event_patterns.practice.task01;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Репозиторий outbox.
 *
 * TODO 1: findBySentAtIsNullOrderByOccurredAtAsc() — неотправленные в порядке возникновения.
 * TODO 2 (для задачи 02): метод выборки пачки с блокировкой:
 *         @Query(value = "SELECT * FROM outbox WHERE sent_at IS NULL ORDER BY occurred_at "
 *                      + "LIMIT :limit FOR UPDATE SKIP LOCKED", nativeQuery = true)
 *         List<OutboxMessage01> lockUnsent(int limit);
 *
 * ПОЧЕМУ SKIP LOCKED: две реплики релэя выбирают пачки одновременно; без блокировки они
 *   отправят одни и те же события дважды, с обычным FOR UPDATE — будут ждать друг друга.
 *   SKIP LOCKED позволяет второй реплике сразу взять другие строки. (H2 поддерживает не все
 *   варианты синтаксиса — на H2 можно ограничиться TODO 1.)
 */
public interface OutboxRepository01 extends JpaRepository<OutboxMessage01, UUID> {

    // TODO 1-2
}
