package m122_kafka_serialization_contracts.practice.task07;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

/**
 * Три потребителя: «старый» сервис, «новый» сервис и подписчик топика .v2.
 *
 * TODO 1: onLegacy — группа "legacy", топик task.created: разобрать payload как v1
 *         и напечатать taskId/title; событие version = 2 обработать без ошибок.
 * TODO 2: onModern — группа "modern", топик task.created: разобрать как v2,
 *         напечатать priority (для событий v1 — дефолт).
 * TODO 3: onV2Topic — группа "modern-v2-topic", топик task.created.v2.
 * TODO 4: во всех методах печатать version из заголовка — видно, какая версия пришла.
 *
 * ПОДСКАЗКА: маршрутизация по версии без разбора тела — именно то, зачем version дублируют
 *   в заголовок: потребитель может решить «эту версию я не понимаю» до десериализации.
 */
@Component
public class ContractListener07 {

    @KafkaListener(topics = "task.created", groupId = "legacy")
    public void onLegacy(EventEnvelope07 envelope,
                         @Header(name = "version", required = false) String version) {
        // TODO 1 + TODO 4
    }

    @KafkaListener(topics = "task.created", groupId = "modern")
    public void onModern(EventEnvelope07 envelope,
                         @Header(name = "version", required = false) String version) {
        // TODO 2 + TODO 4
    }

    @KafkaListener(topics = "task.created.v2", groupId = "modern-v2-topic")
    public void onV2Topic(EventEnvelope07 envelope) {
        // TODO 3
    }
}
