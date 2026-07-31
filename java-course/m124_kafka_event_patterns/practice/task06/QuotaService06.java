package m124_kafka_event_patterns.practice.task06;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * Шаг 1 саги: бронирование и возврат квоты автора.
 *
 * TODO 1: onReserveRequested — если у автора есть свободная квота: уменьшить, запомнить
 *         бронь по sagaId и опубликовать "quota.reserved"; иначе — "quota.rejected".
 * TODO 2: идемпотентность: если бронь по этому sagaId уже есть — повторно не бронировать,
 *         но событие "quota.reserved" отправить снова (повтор ответа безопасен).
 * TODO 3: onReleaseRequested (компенсация) — вернуть квоту, если бронь существует; удалить бронь.
 *         Повторная компенсация того же sagaId не должна возвращать квоту дважды.
 * TODO 4: печатать переходы: «квота автора {id}: {было} -> {стало}».
 *
 * ВАЖНО: компенсация обязана быть идемпотентной так же строго, как и прямой шаг: событие
 *   "quota.release.requested" может прийти повторно (at-least-once), и двойной возврат квоты —
 *   такая же ошибка, как двойное списание.
 */
@Service
public class QuotaService06 {

    private final Map<Long, Integer> quotaByAuthor = new ConcurrentHashMap<>();
    private final Map<String, Long> reservationsBySaga = new ConcurrentHashMap<>();
    private final KafkaTemplate<String, String> kafka;

    public QuotaService06(KafkaTemplate<String, String> kafka) {
        this.kafka = kafka;
    }

    @KafkaListener(topics = SagaTopics06.RESERVE_REQUESTED, groupId = "quota-service")
    public void onReserveRequested(String payload) {
        // TODO 1-2 + TODO 4
    }

    @KafkaListener(topics = SagaTopics06.RELEASE_REQUESTED, groupId = "quota-service")
    public void onReleaseRequested(String payload) {
        // TODO 3-4
    }
}
