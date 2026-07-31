package m121_kafka_consumer_spring.practice.task03;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * Сервис уведомлений: реагирует на создание задачи.
 *
 * TODO: onTaskCreated(TaskCreated03 event) — напечатать
 *       «уведомление автору {authorId}: создана задача {id} — {title}».
 *
 * ПРИНЦИП: слушатель — тонкий адаптер. Он разбирает событие и вызывает бизнес-сервис;
 *   логику отправки писем/пушей внутрь @KafkaListener не пишут (иначе её не протестировать
 *   отдельно — см. модуль 125).
 */
@Component
public class NotificationListener03 {

    @KafkaListener(topics = "task.created", groupId = "notifier-json")
    public void onTaskCreated(TaskCreated03 event) {
        // TODO
    }
}
