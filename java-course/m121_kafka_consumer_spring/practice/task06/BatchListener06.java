package m121_kafka_consumer_spring.practice.task06;

import java.util.List;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

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

    @KafkaListener(topics = "task.created", groupId = "analytics-batch")
    public void onBatch(List<TaskCreated06> events, Acknowledgment ack) {
        // TODO 1-4
    }
}
