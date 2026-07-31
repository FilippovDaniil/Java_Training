package m123_kafka_reliability.practice.task03;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * Основной слушатель: падает на «плохих» сообщениях, чтобы они дошли до DLT.
 *
 * TODO 1: если значение содержит "bad" — бросить RuntimeException("не могу обработать " + value).
 * TODO 2: иначе напечатать «[main] обработано: {value}».
 */
@Component
public class MainListener03 {

    @KafkaListener(topics = "task.created", groupId = "dlq-demo")
    public void on(String value) {
        // TODO 1-2
    }
}
