package m125_kafka_testing.practice.task07;

/**
 * Unit/JPA-уровень набора: транзакционность outbox и работа релэя.
 *
 * ЗАДАНИЕ:
 *   1) @DataJpaTest: команда сохраняет задачу И запись outbox — обе в одной транзакции.
 *   2) rollback_leaves_no_outbox_row(): при исключении внутри команды в БД нет ни задачи,
 *      ни записи outbox (проверка атомарности — главное свойство паттерна).
 *   3) relay_marks_sent_only_after_successful_send(): с моком KafkaTemplate:
 *        - успешная отправка -> sentAt заполнен;
 *        - отправка с ошибкой -> sentAt остался null, attempts увеличился.
 *   4) relay_keeps_order_by_occurred_at(): релэй отправляет записи в порядке occurredAt
 *      (проверка через InOrder у мока).
 *
 * ЦЕЛЬ: закрыть тестами самые дорогие ошибки outbox-контура: потерю атомарности и «пометил
 *       отправленным, хотя не отправил».
 *
 * ВАЖНО: пункт 3 — про порядок операций в релэе. Если markSent() вызывается до подтверждения
 *   отправки, событие исчезнет при сбое брокера, и тест обязан это ловить.
 */

import org.junit.jupiter.api.Test;

class OutboxFlowUnitTest07 {

    @Test
    void command_writes_task_and_outbox_in_one_transaction() {
        // TODO 1
    }

    @Test
    void rollback_leaves_no_outbox_row() {
        // TODO 2
    }

    @Test
    void relay_marks_sent_only_after_successful_send() {
        // TODO 3
    }

    @Test
    void relay_keeps_order_by_occurred_at() {
        // TODO 4
    }
}
