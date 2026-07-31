package m125_kafka_testing.practice.task06;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * Слушатель, фиксирующий порядок полученных сообщений.
 *
 * TODO 1: складывать значения в received.
 * TODO 2: values() — копия списка для утверждений теста.
 * TODO 3 (для пункта 3 задания): убедиться, что concurrency НЕ задан (или равен 1) —
 *         иначе порядок в рамках одного ключа всё равно сохранится (одна партиция),
 *         но записи разных ключей перемешаются в списке и сравнение усложнится.
 */
@Component
public class OrderListener06 {

    private final List<String> received = new CopyOnWriteArrayList<>();

    @KafkaListener(topics = "task.created", id = "order-listener", groupId = "container-test")
    public void on(String value) {
        // TODO 1
    }

    public List<String> values() {
        return List.copyOf(received);
    }
}
