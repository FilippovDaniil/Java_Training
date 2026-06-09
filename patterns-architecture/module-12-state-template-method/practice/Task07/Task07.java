/**
 * Задача 07 — Тема 12 (МИНИ-ПРОЕКТ OPS): жизненный цикл заказа + пайплайн
 *
 * Развитие OPS. Объедините два поведенческих паттерна:
 *   - STATE — жизненный цикл заказа NEW → PAID → SHIPPED;
 *   - TEMPLATE METHOD — фиксированный пайплайн обработки, который проводит
 *     заказ по состояниям, с переопределяемым форматированием статуса.
 *
 * ЗАДАНИЕ:
 *   1. State: OrderState (файл OrderState.java): OrderState pay(); OrderState ship();
 *      String name(); NewState (pay→Paid, ship→остаётся), PaidState (ship→Shipped),
 *      ShippedState (терминальное).
 *   2. Order (файл Order.java) — контекст: pay()/ship() делегируют состоянию,
 *      status() → name().
 *   3. Template Method: абстрактный OrderPipeline (файл OrderPipeline.java) с
 *      final String run(Order o):
 *        формат статуса (NEW) → o.pay() → формат (PAID) → o.ship() → формат (SHIPPED),
 *        строки через "\n";
 *      protected abstract String format(String status);
 *      PlainPipeline (файл PlainPipeline.java): format → "статус: " + status.
 *   В main прогоните заказ через PlainPipeline и выведите результат.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   статус: NEW
 *   статус: PAID
 *   статус: SHIPPED
 *
 * ТРЕБОВАНИЯ:
 *   - переходы заказа заданы состояниями (State), контекст без switch;
 *   - run() — final: порядок шагов пайплайна фиксирован (Template Method);
 *   - формат статуса переопределяется в подклассе пайплайна (добавить
 *     VerbosePipeline можно без правок каркаса).
 *
 * ПОДСКАЗКА:
 *   Template Method «дирижирует» переходами State в нужном порядке, а конкретный
 *   вид вывода — за подклассом. В теме 21 (Saga) эта связка масштабируется на
 *   распределённый процесс.
 */

public class Task07 {
    public static void main(String[] args) {
        // TODO: создайте Order, прогоните через PlainPipeline.run(order), выведите результат
    }
}
