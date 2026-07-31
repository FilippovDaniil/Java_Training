package m121_kafka_consumer_spring.practice.task05;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * Слушатель в несколько потоков.
 *
 * TODO 1: добавить в @KafkaListener параметр concurrency = "3".
 * TODO 2: напечатать Thread.currentThread().getName(), partition, key, value.
 * TODO 3 (наблюдение): сделать паузу 100 мс в обработке, чтобы распределение по потокам
 *         было хорошо видно в выводе.
 *
 * ПОДСКАЗКА: concurrency создаёт N MessageListenerContainer'ов в одной группе; брокер
 *   распределяет партиции между ними так же, как между отдельными процессами.
 */
@Component
public class ParallelListener05 {

    @KafkaListener(topics = "task.created", groupId = "parallel")
    public void onTaskCreated(ConsumerRecord<String, String> rec) {
        // TODO 2 + TODO 3
    }
}
