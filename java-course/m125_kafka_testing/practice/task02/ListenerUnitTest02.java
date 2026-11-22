package m125_kafka_testing.practice.task02;

/**
 * Задача 02 — Модуль 125: unit-тест слушателя (идемпотентность и подтверждение офсета)
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: spring-kafka, spring-boot-starter-test. Тест-класс без main. Брокер не нужен.
 *
 * ФАЙЛЫ ЗАДАЧИ: ListenerUnitTest02.java (тест), IdempotentListener02.java (слушатель),
 *               NotificationService02.java (эффект), EventEnvelope02.java (событие).
 *
 * ЗАДАНИЕ — вызывайте метод слушателя НАПРЯМУЮ, как обычный метод:
 *   1) processes_event_once(): одно событие -> notificationService вызван 1 раз, ack 1 раз.
 *   2) duplicate_event_produces_single_effect(): то же событие (тот же eventId) дважды ->
 *      эффект РОВНО один, но ack вызван ДВА раза (дубль тоже нужно подтверждать!).
 *   3) different_events_produce_two_effects(): разные eventId с одинаковым payload -> 2 эффекта.
 *   4) does_not_acknowledge_on_failure(): notificationService бросает исключение ->
 *      слушатель пробрасывает его наружу и НЕ вызывает ack (иначе событие потеряется).
 *
 * ЦЕЛЬ: покрыть тестами то, из-за чего в проде появляются двойные письма и потерянные события, —
 *       без поднятия брокера и без ожиданий.
 *
 * ВАЖНО: пункт 4 — самый ценный. Он фиксирует, что подтверждение офсета стоит ПОСЛЕ эффекта
 *       и не выполняется в finally. Такую ошибку легко внести при рефакторинге.
 *
 * ПОДСКАЗКА: Acknowledgment — интерфейс, его мокает Mockito. Проверка «не вызван»:
 *   then(ack).should(never()).acknowledge().
 */

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.support.Acknowledgment;

import java.time.Instant;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.then;
import static org.mockito.BDDMockito.willThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;


@ExtendWith(MockitoExtension.class)
class ListenerUnitTest02 {

    @Mock
    NotificationService02 notificationService;

    @Mock
    Acknowledgment ack;

    @InjectMocks
    IdempotentListener02 listener;

    private static EventEnvelope02 event(UUID id, String payload) {
        return new EventEnvelope02(id, "task.created", Instant.now(), payload);
    }

    @Test
    void processes_event_once() {
        EventEnvelope02 e = event(UUID.randomUUID(), "{\"id\":42}");

        listener.on(e, ack);

        then(notificationService).should(times(1)).notify(e);
        then(ack).should(times(1)).acknowledge();
    }

    @Test
    void duplicate_event_produces_single_effect() {
        EventEnvelope02 e = event(UUID.randomUUID(), "{\"id\":42}");

        listener.on(e, ack);   // первая доставка
        listener.on(e, ack);   // дубль: тот же eventId

        then(notificationService).should(times(1)).notify(e); // эффект ровно один
        then(ack).should(times(2)).acknowledge();             // но подтверждаем оба раза
    }

    @Test
    void different_events_produce_two_effects() {
        EventEnvelope02 first  = event(UUID.randomUUID(), "{\"id\":42}");
        EventEnvelope02 second = event(UUID.randomUUID(), "{\"id\":42}"); // тот же payload

        listener.on(first, ack);
        listener.on(second, ack);

        then(notificationService).should(times(1)).notify(first);
        then(notificationService).should(times(1)).notify(second);
        then(ack).should(times(2)).acknowledge();
    }

    @Test
    void does_not_acknowledge_on_failure() {
        EventEnvelope02 e = event(UUID.randomUUID(), "{\"id\":42}");
        willThrow(new RuntimeException("SMTP недоступен"))
                .given(notificationService).notify(e);

        assertThatThrownBy(() -> listener.on(e, ack))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("SMTP недоступен");

        then(ack).should(never()).acknowledge(); // офсет НЕ подтверждён → событие не потеряно
    }
}
