package m123_kafka_reliability.practice.task01;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * Слушатель с управляемой нестабильностью — эмулирует временный и постоянный сбой.
 *
 * TODO 1: считать номер попытки по значению сообщения (attempts).
 * TODO 2: если значение содержит "flaky" — падать (RuntimeException) на попытках 1 и 2,
 *         на 3-й обработать успешно.
 * TODO 3: если значение содержит "always-bad" — падать всегда.
 * TODO 4: печатать «попытка N для {value} -> падаю/успех».
 *
 * ЗАЧЕМ ЭТО НУЖНО: так тестируют устойчивость обработчика к реальным сбоям — недоступной БД,
 *   таймауту внешнего API. Позже (модуль 125) то же поведение проверяется автотестами.
 */
@Component
public class FlakyListener01 {

    private final Map<String, AtomicInteger> attempts = new ConcurrentHashMap<>();

    @KafkaListener(topics = "task.created", groupId = "retry-demo")
    public void on(String value) {
        // TODO 1-4
    }
}
