package m121_kafka_consumer_spring.practice.task07;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Service;

/**
 * Бизнес-логика уведомлений — отдельно от слушателя, чтобы её можно было тестировать без Kafka.
 *
 * TODO 1: notifyCreated(TaskCreated07 event) — «письмо автору {authorId}: задача {id} создана».
 * TODO 2: notifyStatusChanged(TaskStatusChanged07 event) — «письмо: задача {id} перешла
 *         {oldStatus} -> {newStatus}»; уведомлять ТОЛЬКО при newStatus = "DONE".
 * TODO 3: идемпотентность — перед отправкой проверить, что ключ дедупликации ещё не обработан
 *         (processed.add(key) вернёт false, если ключ уже был), иначе выйти без уведомления.
 *
 * ПОЧЕМУ ЭТО ОБЯЗАТЕЛЬНО: at-least-once означает, что одно и то же событие может прийти дважды
 *   (перебалансировка, рестарт до коммита, ретрай). Без дедупликации пользователь получит
 *   два письма. Полноценное решение (persistent-таблица обработанных событий) — модуль 123.
 */
@Service
public class NotificationService07 {

    private final Set<String> processed = ConcurrentHashMap.newKeySet();

    public void notifyCreated(TaskCreated07 event) {
        String key = "created:" + event.id();
        if (!processed.add(key)) {
            System.out.printf("[skip] повторное событие created id=%d%n", event.id());
            return;
        }
        System.out.printf("письмо автору %d: задача %d создана — %s%n",
                event.authorId(), event.id(), event.title());
    }

    public void notifyStatusChanged(TaskStatusChanged07 event) {
        if (!"DONE".equals(event.newStatus())) {
            return;   // уведомляем только о завершении
        }
        String key = "status:" + event.id() + ":" + event.newStatus();
        if (!processed.add(key)) {
            System.out.printf("[skip] повторное событие status id=%d newStatus=%s%n",
                    event.id(), event.newStatus());
            return;
        }
        System.out.printf("письмо: задача %d перешла %s -> %s%n",
                event.id(), event.oldStatus(), event.newStatus());
    }
}
