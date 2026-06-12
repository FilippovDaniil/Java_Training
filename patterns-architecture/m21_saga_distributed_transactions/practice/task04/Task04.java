package m21_saga_distributed_transactions.practice.task04;

/**
 * Задача 04 — Тема 21: Saga-хореография (реакция на события)
 *
 * ЗАДАНИЕ:
 *   Без центрального оркестратора: каждый участник реагирует на событие и
 *   публикует следующее.
 *     - события OrderCreated(orderId), PaymentDone(orderId) — records (готовы);
 *     - EventBus (файл EventBus.java) — шина: subscribe(Class, Consumer) и
 *       publish(event) (готова);
 *     - PaymentParticipant (файл PaymentParticipant.java): register(EventBus bus)
 *       подписывается на OrderCreated → печатает "оплата проведена" и публикует
 *       PaymentDone(orderId);
 *     - ShippingParticipant (файл ShippingParticipant.java): register(EventBus bus)
 *       подписывается на PaymentDone → печатает "отгрузка <orderId>".
 *   В main: создайте шину, зарегистрируйте обоих участников, опубликуйте
 *   OrderCreated — цепочка реакций пройдёт сама.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   оплата проведена
 *   отгрузка A-1
 *
 * ТРЕБОВАНИЯ:
 *   - нет центрального координатора — участники связаны только через события;
 *   - реакция на событие публикует следующее событие (цепочка хореографии);
 *   - участник знает только «своё» событие, не общий ход процесса.
 *
 * ПОДСКАЗКА:
 *   bus.subscribe(OrderCreated.class, e -> { ...; bus.publish(new PaymentDone(e.orderId())); });
 *   Минус хореографии — ход процесса «размазан» по участникам.
 */

public class Task04 {
    public static void main(String[] args) {
        // TODO: EventBus; register обоих участников; bus.publish(new OrderCreated("A-1"))
    }
}
