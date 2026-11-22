package m124_kafka_event_patterns.practice.task05;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Загрузка снимка состояния из compacted-топика в память сервиса.
 *
 * TODO 1: on(key, value): value != null -> state.put(key, value); value == null -> state.remove(key).
 * TODO 2: печатать текущий размер снимка и изменённый ключ.
 * TODO 3: метод snapshot() — неизменяемая копия карты (для чтения из других компонентов).
 * TODO 4 (наблюдение): перезапустите приложение и убедитесь, что снимок восстанавливается
 *         из топика без обращения к чужой БД.
 *
 * ГДЕ ЭТО ИСПОЛЬЗУЮТ: справочники и конфигурация (курсы валют, тарифы, feature-флаги),
 *   «последнее известное состояние» сущностей для сервисов, которым не нужна полная история.
 *   В Kafka Streams тот же приём встроен и называется KTable.
 *
 * ВАЖНО: у такого слушателя должна быть УНИКАЛЬНАЯ группа на каждый экземпляр сервиса
 *   (например, с суффиксом hostname): каждому экземпляру нужен ПОЛНЫЙ снимок, а члены одной
 *   группы поделили бы партиции между собой и каждый увидел бы только часть данных.
 */
@Component
public class StateBootstrapListener05 {

    private final Map<String, String> state = new ConcurrentHashMap<>();

    @KafkaListener(topics = "task.state", groupId = "state-bootstrap")
    public void on(ConsumerRecord<String, String> rec) {
        String key = rec.key();
        String value = rec.value();

        if (value == null) {
            state.remove(key);
            System.out.printf("[bootstrap] tombstone key=%s (размер снимка: %d)%n",
                    key, state.size());
        } else {
            state.put(key, value);
            System.out.printf("[bootstrap] key=%s -> %s (размер снимка: %d)%n",
                    key, value, state.size());
        }
    }

    public Map<String, String> snapshot() {
        return Map.copyOf(state);
    }
}