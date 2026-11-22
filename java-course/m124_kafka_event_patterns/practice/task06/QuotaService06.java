package m124_kafka_event_patterns.practice.task06;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

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
    private final Map<String, String> sagaState = new ConcurrentHashMap<>();
    private final KafkaTemplate<String, String> kafka;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public QuotaService06(KafkaTemplate<String, String> kafka) {
        this.kafka = kafka;
        // стартовые квоты: автор 10 — 5, автор 11 — 0 (для эксперимента A)
        quotaByAuthor.put(10L, 5);
        quotaByAuthor.put(11L, 0);
    }

    @KafkaListener(topics = SagaTopics06.RESERVE_REQUESTED, groupId = "quota-service")
    public void onReserveRequested(String payload) throws Exception {
        JsonNode node = objectMapper.readTree(payload);
        String sagaId = node.get("sagaId").asText();
        long authorId = node.get("authorId").asLong();

        sagaState.put(sagaId, "RESERVE_REQUESTED");
        System.out.printf("saga %s: RESERVE_REQUESTED%n", sagaId);

        // идемпотентность: если бронь уже есть — не бронировать повторно, но ответить
        if (reservationsBySaga.containsKey(sagaId)) {
            System.out.printf("saga %s: бронь уже есть, повторяем quota.reserved%n", sagaId);
            kafka.send(SagaTopics06.RESERVED, sagaId,
                    "{\"sagaId\":\"" + sagaId + "\",\"authorId\":" + authorId + "}");
            return;
        }

        int available = quotaByAuthor.getOrDefault(authorId, 0);
        if (available <= 0) {
            sagaState.put(sagaId, "QUOTA_REJECTED");
            System.out.printf("saga %s: QUOTA_REJECTED (квота автора %d = 0)%n", sagaId, authorId);
            kafka.send(SagaTopics06.REJECTED, sagaId,
                    "{\"sagaId\":\"" + sagaId + "\",\"authorId\":" + authorId + ",\"reason\":\"no quota\"}");
            return;
        }

        quotaByAuthor.put(authorId, available - 1);
        reservationsBySaga.put(sagaId, authorId);
        sagaState.put(sagaId, "QUOTA_RESERVED");
        System.out.printf("квота автора %d: %d -> %d (saga %s)%n",
                authorId, available, available - 1, sagaId);

        kafka.send(SagaTopics06.RESERVED, sagaId,
                "{\"sagaId\":\"" + sagaId + "\",\"authorId\":" + authorId + "}");
    }

    @KafkaListener(topics = SagaTopics06.RELEASE_REQUESTED, groupId = "quota-service")
    public void onReleaseRequested(String payload) throws Exception {
        JsonNode node = objectMapper.readTree(payload);
        String sagaId = node.get("sagaId").asText();

        Long authorId = reservationsBySaga.remove(sagaId);   // атомарно: второй раз вернёт null
        if (authorId == null) {
            System.out.printf("saga %s: компенсация уже выполнена или брони нет — пропускаем%n", sagaId);
            return;
        }

        int current = quotaByAuthor.getOrDefault(authorId, 0);
        quotaByAuthor.put(authorId, current + 1);
        sagaState.put(sagaId, "QUOTA_RELEASED");
        System.out.printf("квота автора %d: %d -> %d (компенсация saga %s)%n",
                authorId, current, current + 1, sagaId);
    }

    public Map<String, String> states() {
        return Map.copyOf(sagaState);
    }
}
