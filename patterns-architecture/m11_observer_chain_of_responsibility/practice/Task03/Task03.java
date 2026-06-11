package m11_observer_chain_of_responsibility.practice.task03;

/**
 * Задача 03 — Тема 11: Chain of Responsibility (базовый)
 *
 * ЗАДАНИЕ:
 *   Постройте цепочку обработчиков, каждый берёт «свой» диапазон чисел:
 *     - абстрактный Handler (файл Handler.java): поле next, setNext(Handler)
 *       (возвращает переданный, чтобы строить цепочку), abstract String handle(int n),
 *       protected passToNext(int n) — передать следующему или вернуть
 *       "никто не обработал";
 *     - LowHandler (n < 10 → "low: n"), MidHandler (n < 100 → "mid: n"),
 *       HighHandler (иначе → "high: n").
 *   В main соберите цепочку low → mid → high и прогоните несколько чисел.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   low: 5
 *   mid: 50
 *   high: 500
 *
 * ТРЕБОВАНИЯ:
 *   - каждый обработчик либо обрабатывает сам, либо передаёт дальше;
 *   - отправитель (main) не знает, кто именно обработает число;
 *   - порядок в цепочке определяет, кто получит запрос первым.
 *
 * ПОДСКАЗКА:
 *   low.setNext(mid).setNext(high); — setNext возвращает аргумент, поэтому
 *   цепочку можно строить «в строчку».
 */

public class Task03 {
    public static void main(String[] args) {
        // TODO: соберите цепочку low → mid → high, вызовите handle(5), handle(50), handle(500)
    }
}
