package m10_strategy_command.practice.task07;

/**
 * Задача 07 — Тема 10 (МИНИ-ПРОЕКТ OPS): редактирование заказа + расчёт
 *
 * Развитие OPS. Объедините два поведенческих паттерна:
 *   - COMMAND — операции над заказом (добавить позицию) с поддержкой undo;
 *   - STRATEGY — стратегия скидки при подсчёте итога.
 *
 * ЗАДАНИЕ:
 *   1. DiscountStrategy (файл DiscountStrategy.java): long apply(long sumCents);
 *      NoDiscount и PercentDiscount(percent).
 *   2. Order (файл Order.java): список позиций (long, в копейках) и текущая
 *      DiscountStrategy; addItem(long), removeLastItem(), setDiscount(strategy),
 *      long total() = discount.apply(сумма позиций).
 *   3. OrderCommand (файл OrderCommand.java): void execute(); void undo();
 *   4. AddItemCommand (файл AddItemCommand.java): execute() добавляет позицию,
 *      undo() удаляет последнюю.
 *   5. OrderHistory (файл OrderHistory.java): run(OrderCommand) и undoLast().
 *   В main: добавьте 3 позиции командами, поставьте скидку -10%, выведите итог;
 *   отмените последнюю команду, снова выведите итог.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   Итог со скидкой -10%: 27000   (позиции 10000+12000+8000 = 30000)
 *   После undo: 19800             (осталось 10000+12000 = 22000)
 *
 * ТРЕБОВАНИЯ:
 *   - добавление позиции идёт через команду, undo() удаляет именно её;
 *   - скидка применяется стратегией (смена скидки без правок Order.total());
 *   - история команд позволяет откатывать операции редактирования.
 *
 * ПОДСКАЗКА:
 *   Это «репетиция» будущего OrderService: команды дают undo/redo и журнал
 *   операций, стратегия — гибкое ценообразование. В теме 20 команды станут
 *   основой CQRS.
 */

public class Task07 {
    public static void main(String[] args) {
        // TODO: через OrderHistory выполните три AddItemCommand, setDiscount(-10%),
        //       выведите total(); undoLast(); снова total()
    }
}
