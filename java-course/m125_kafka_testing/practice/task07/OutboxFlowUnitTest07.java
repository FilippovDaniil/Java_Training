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
import org.mockito.InOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.kafka.core.KafkaTemplate;

import java.time.Instant;
import java.util.concurrent.CompletableFuture;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@DataJpaTest
@Import({CreateTaskCommand.class, OutboxRelay.class})
class OutboxFlowUnitTest07 {

    @Autowired CreateTaskCommand command;
    @Autowired OutboxRelay relay;
    @Autowired TaskRepository tasks;
    @Autowired OutboxRepository outbox;

    @MockBean KafkaTemplate<String, Object> kafka;

    @Test
    void command_writes_task_and_outbox_in_one_transaction() {
        command.create("Отчёт", 10);

        assertThat(tasks.count()).isEqualTo(1);
        assertThat(outbox.count()).isEqualTo(1);

        OutboxEntity row = outbox.findAll().get(0);
        assertThat(row.getSentAt()).isNull();          // ещё не отправлено
        assertThat(row.getEventType()).isEqualTo("task.created");
        assertThat(row.getPayload()).contains("Отчёт");
    }

    @Test
    void rollback_leaves_no_outbox_row() {
        // команда с невалидным title бросает исключение ДО коммита
        assertThatThrownBy(() -> command.create("", 10))
                .isInstanceOf(IllegalArgumentException.class);

        // атомарность: ни задачи, ни записи outbox
        assertThat(tasks.count()).isZero();
        assertThat(outbox.count()).isZero();
    }

    @Test
    void relay_marks_sent_only_after_successful_send() {
        // успешная отправка -> sentAt заполнен
        given(kafka.send(any(), any(), any()))
                .willReturn(CompletableFuture.completedFuture(null));
        command.create("Отчёт", 10);
        relay.relayOnce();

        OutboxEntity sent = outbox.findAll().get(0);
        assertThat(sent.getSentAt()).isNotNull();
        assertThat(sent.getAttempts()).isZero();

        // сбой отправки -> sentAt остался null, attempts увеличился
        reset(kafka);
        given(kafka.send(any(), any(), any()))
                .willReturn(CompletableFuture.failedFuture(new RuntimeException("брокер недоступен")));
        command.create("Второй", 10);
        relay.relayOnce();

        OutboxEntity failed = outbox.findAll().stream()
                .filter(r -> r.getSentAt() == null)
                .findFirst().orElseThrow();
        assertThat(failed.getSentAt()).isNull();       // НЕ помечен отправленным
        assertThat(failed.getAttempts()).isGreaterThan(0);
    }

    @Test
    void relay_keeps_order_by_occurred_at() {
        given(kafka.send(any(), any(), any()))
                .willReturn(CompletableFuture.completedFuture(null));

        // три записи с разным occurredAt, вставленные «вперемешку»
        OutboxEntity e1 = outbox.save(newOutbox("1", Instant.parse("2026-01-01T00:00:03Z")));
        OutboxEntity e2 = outbox.save(newOutbox("2", Instant.parse("2026-01-01T00:00:01Z")));
        OutboxEntity e3 = outbox.save(newOutbox("3", Instant.parse("2026-01-01T00:00:02Z")));

        relay.relayOnce();

        InOrder inOrder = inOrder(kafka);
        inOrder.verify(kafka).send(eq("task.created"), eq("2"), any());
        inOrder.verify(kafka).send(eq("task.created"), eq("3"), any());
        inOrder.verify(kafka).send(eq("task.created"), eq("1"), any());
    }

    private OutboxEntity newOutbox(String aggregateId, Instant at) {
        OutboxEntity e = new OutboxEntity();
        e.setAggregateId(aggregateId);
        e.setEventType("task.created");
        e.setPayload("{\"id\":" + aggregateId + "}");
        e.setOccurredAt(at);
        return e;
    }
}
