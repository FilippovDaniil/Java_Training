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

class ListenerUnitTest02 {

    @Test
    void processes_event_once() {
        // TODO 1
    }

    @Test
    void duplicate_event_produces_single_effect() {
        // TODO 2
    }

    @Test
    void different_events_produce_two_effects() {
        // TODO 3
    }

    @Test
    void does_not_acknowledge_on_failure() {
        // TODO 4
    }
}
