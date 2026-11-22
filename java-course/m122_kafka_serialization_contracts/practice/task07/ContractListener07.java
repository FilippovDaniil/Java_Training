package m122_kafka_serialization_contracts.practice.task07;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.ObjectMapper;
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

    private final ObjectMapper objectMapper = new ObjectMapper()
            .findAndRegisterModules();   // JavaTimeModule для Instant

    @KafkaListener(topics = "task.created", groupId = "legacy")
    public void onLegacy(EventEnvelope07 envelope,
                         @Header(name = "version", required = false) String version) {
        try {
            TaskCreatedV107 v1 = objectMapper.convertValue(envelope.payload(), TaskCreatedV107.class);
            System.out.printf("[legacy] version=%s taskId=%d title=%s%n",
                    version, v1.taskId(), v1.title());
        } catch (Exception e) {
            System.err.println("[legacy] ошибка разбора: " + e.getMessage());
        }
    }

    @KafkaListener(topics = "task.created", groupId = "modern")
    public void onModern(EventEnvelope07 envelope,
                         @Header(name = "version", required = false) String version) {
        try {
            TaskCreatedV207 v2 = objectMapper.convertValue(envelope.payload(), TaskCreatedV207.class);
            System.out.printf("[modern] version=%s taskId=%d title=%s priority=%s deadline=%s%n",
                    version, v2.taskId(), v2.title(), v2.priority(), v2.deadline());
        } catch (Exception e) {
            System.err.println("[modern] ошибка разбора: " + e.getMessage());
        }
    }

    @KafkaListener(topics = "task.created.v2", groupId = "modern-v2-topic")
    public void onV2Topic(EventEnvelope07 envelope) {
        try {
            TaskCreatedV207 v2 = objectMapper.convertValue(envelope.payload(), TaskCreatedV207.class);
            System.out.printf("[modern-v2-topic] taskId=%d title=%s priority=%s%n",
                    v2.taskId(), v2.title(), v2.priority());
        } catch (Exception e) {
            System.err.println("[modern-v2-topic] ошибка разбора: " + e.getMessage());
        }
    }
}
