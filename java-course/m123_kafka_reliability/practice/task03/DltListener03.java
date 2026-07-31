package m123_kafka_reliability.practice.task03;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

/**
 * Слушатель DLT: место разбора инцидентов.
 *
 * TODO 1: принять значение + заголовки ORIGINAL_TOPIC, ORIGINAL_PARTITION, ORIGINAL_OFFSET,
 *         EXCEPTION_FQCN, EXCEPTION_MESSAGE (константы KafkaHeaders / DLT-имена kafka_dlt-*).
 * TODO 2: напечатать одну строку с полной диагностикой.
 * TODO 3: посчитать в счётчике, сколько записей попало в DLT — это будущая метрика для алерта
 *         (модуль 126: экспорт в Micrometer/Prometheus).
 *
 * ЧТО ДЕЛАЮТ В ПРОДЕ: DLT-слушатель обычно НЕ пытается обработать событие снова автоматически.
 *   Он логирует, поднимает алерт и сохраняет запись для ручного/полуавтоматического reprocessing:
 *   слепой автоповтор в DLT легко превращается в бесконечный цикл.
 */
@Component
public class DltListener03 {

    @KafkaListener(topics = "task.created.DLT", groupId = "dlt-monitor")
    public void onDlt(String value,
                      @Header(name = KafkaHeaders.DLT_ORIGINAL_TOPIC, required = false) String originalTopic,
                      @Header(name = KafkaHeaders.DLT_EXCEPTION_MESSAGE, required = false) String message) {
        // TODO 1-3
    }
}
