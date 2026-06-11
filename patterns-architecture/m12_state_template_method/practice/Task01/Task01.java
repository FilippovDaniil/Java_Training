package m12_state_template_method.practice.task01;

/**
 * Задача 01 — Тема 12: State (жизненный цикл заказа)
 *
 * ЗАДАНИЕ:
 *   Реализуйте состояния заказа как отдельные классы:
 *     - интерфейс OrderState (файл OrderState.java): OrderState pay();
 *       OrderState ship(); String name();
 *     - NewState (pay → PaidState; ship → остаётся NEW),
 *       PaidState (pay → остаётся PAID; ship → ShippedState),
 *       ShippedState (pay/ship → остаётся SHIPPED);
 *     - Order (файл Order.java) — контекст: хранит текущее OrderState, методы
 *       pay()/ship() делегируют состоянию и обновляют его, status() → name().
 *   В main: создайте заказ (NEW), оплатите, отгрузите, выводя статус после
 *   каждого шага; попробуйте отгрузить неоплаченный (останется NEW).
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   NEW
 *   попытка ship без оплаты: NEW
 *   после pay: PAID
 *   после ship: SHIPPED
 *
 * ТРЕБОВАНИЯ:
 *   - в Order НЕТ switch по статусу — поведение делегируется состоянию;
 *   - каждое состояние само знает допустимые переходы;
 *   - недопустимый переход не меняет состояние.
 *
 * ПОДСКАЗКА:
 *   pay()/ship() возвращают следующее состояние (или this, если переход
 *   недопустим). Order: state = state.pay();
 */

public class Task01 {
    public static void main(String[] args) {
        // TODO: создайте Order, выведите статус; ship (NEW), pay, ship, печатая статусы
    }
}
