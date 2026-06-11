package m20_event_sourcing_cqrs.practice.task07;

/**
 * Задача 07 — Тема 20 (МИНИ-ПРОЕКТ OPS): заказ как поток событий + проекция
 *
 * Развитие OPS. Жизненный цикл заказа хранится НЕ как состояние, а как поток
 * событий (Event Sourcing), а текущая сводка строится проекцией (CQRS).
 *
 * ЗАДАНИЕ:
 *   1. События (records, готовы): OrderCreated(orderId), ItemAdded(sku, priceCents),
 *      OrderPaid().
 *   2. EventStore (файл EventStore.java): append(Object), List<Object> events().
 *   3. OrderProjection (файл OrderProjection.java) — read-модель: поля orderId,
 *      itemCount, totalCents, paid; on(Object event) обновляет их
 *      (Created → orderId; ItemAdded → itemCount++, totalCents += price; Paid → paid=true);
 *      summary() → "Заказ <id>: позиций <n>, сумма <total>, оплачен <paid>".
 *   В main: запишите в store события [Created A-1, ItemAdded BOOK 20000,
 *   ItemAdded PEN 12000, Paid]; постройте проекцию, проиграв лог; выведите summary().
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   Заказ A-1: позиций 2, сумма 32000, оплачен true
 *
 * ТРЕБОВАНИЯ:
 *   - источник истины — лог событий (append-only), не изменяемое состояние заказа;
 *   - сводка заказа получается проекцией из лога (read-модель);
 *   - проекция только собирает представление (никаких бизнес-правил/проверок).
 *
 * ПОДСКАЗКА:
 *   Это объединяет всё из темы: события (тема 19) → лог (ES) → проекция (CQRS).
 *   Можно добавить вторую проекцию (например, «оплачен ли») из того же лога.
 */

public class Task07 {
    public static void main(String[] args) {
        // TODO: append события в EventStore; постройте OrderProjection из events();
        //       выведите summary()
    }
}
