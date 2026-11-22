package m121_kafka_consumer_spring.practice.task06;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

/**
 * Батчевый слушатель: обрабатывает пачку событий одной операцией.
 *
 * TODO 1: напечатать размер батча и минимальный/максимальный id в нём.
 * TODO 2: «сохранить» батч одной операцией (в реальном проекте — saveAll / bulk insert).
 * TODO 3: ack.acknowledge() после успешной обработки батча.
 * TODO 4 (наблюдение): при появлении null в списке (результат ErrorHandlingDeserializer)
 *         напечатать предупреждение и не падать.
 *
 * ЗАЧЕМ БАТЧИ: 500 отдельных INSERT'ов против одного bulk-INSERT — разница в разы. Но цена —
 *   гранулярность ошибки: сбой на одной записи затрагивает весь батч.
 */
@Component
public class BatchListener06 {

    private static final Logger log = LoggerFactory.getLogger(BatchListener06.class);

    @KafkaListener(topics = "task.created", groupId = "analytics-batch")
    public void onBatch(List<TaskCreated06> events, Acknowledgment ack) {
        // при ErrorHandlingDeserializer «битые» записи приходят как null
        List<TaskCreated06> valid = events.stream().filter(Objects::nonNull).toList();
        long broken = events.size() - valid.size();

        if (broken > 0) {
            log.warn("в батче {} битых записей (null) — пропускаем", broken);
        }

        if (valid.isEmpty()) {
            ack.acknowledge();
            return;
        }

        long minId = valid.stream().mapToLong(TaskCreated06::id).min().orElse(-1);
        long maxId = valid.stream().mapToLong(TaskCreated06::id).max().orElse(-1);

        System.out.printf("батч: %d событий (id %d..%d)%n", valid.size(), minId, maxId);

        // эмуляция одной операции сохранения (в реальном проекте — saveAll / bulk insert)
        System.out.printf("сохранено одной операцией: %d записей%n", valid.size());

        ack.acknowledge();
    }
}
