package m121_kafka_consumer_spring.practice.task07;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

/**
 * Слушатели событий Task Tracker — тонкие адаптеры над NotificationService07.
 *
 * TODO 1: onTaskCreated — topics = "task.created", groupId = "notifier",
 *         id = "notifier-created", concurrency = "3":
 *           service.notifyCreated(event); затем ack.acknowledge().
 * TODO 2: onStatusChanged — topics = "task.status.changed", groupId = "notifier",
 *         id = "notifier-status":
 *           service.notifyStatusChanged(event); затем ack.acknowledge().
 * TODO 3: при исключении из сервиса НЕ подтверждать офсет (событие придёт повторно) —
 *         убедитесь, что acknowledge() стоит после вызова сервиса, а не в finally.
 *
 * ВАЖНО: id слушателя — это имя контейнера в KafkaListenerEndpointRegistry; по нему работают
 *   pause()/resume() из ListenerAdminController07.
 */
@Component
public class TaskEventsListener07 {

    private final NotificationService07 service;

    public TaskEventsListener07(NotificationService07 service) {
        this.service = service;
    }

    @KafkaListener(topics = "task.created", groupId = "notifier", id = "notifier-created")
    public void onTaskCreated(TaskCreated07 event, Acknowledgment ack) {
        // TODO 1
    }

    @KafkaListener(topics = "task.status.changed", groupId = "notifier", id = "notifier-status")
    public void onStatusChanged(TaskStatusChanged07 event, Acknowledgment ack) {
        // TODO 2
    }
}
