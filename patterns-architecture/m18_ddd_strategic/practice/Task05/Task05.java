package m18_ddd_strategic.practice.task05;

/**
 * Задача 05 — Тема 18: Aggregate (корень стережёт инварианты)
 *
 * ЗАДАНИЕ:
 *   Реализуйте агрегат «Заказ», где доступ к строкам — только через корень:
 *     - OrderLine (файл OrderLine.java): sku, qty, priceCents; subtotal();
 *       (внутренность агрегата);
 *     - Order (файл Order.java) — Aggregate Root: список строк + флаг confirmed;
 *       addLine(sku, qty, price) — ЗАПРЕЩЁН после confirm() (IllegalStateException);
 *       confirm(); total(); lines() возвращает КОПИЮ списка (не ссылку).
 *   В main: добавьте строки, посчитайте total, подтвердите заказ, попробуйте
 *   добавить строку после подтверждения (исключение); убедитесь, что lines()
 *   отдаёт копию (изменение полученного списка не меняет агрегат).
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   Итого: 32000
 *   Добавление после confirm: запрещено
 *   Строк в заказе: 2 (внешний список не повлиял)
 *
 * ТРЕБОВАНИЯ:
 *   - инвариант «нельзя менять подтверждённый заказ» проверяет корень;
 *   - внешний код не может изменить внутренние строки в обход корня (lines() — копия);
 *   - вся работа с агрегатом идёт через Order, не через OrderLine напрямую.
 *
 * ПОДСКАЗКА:
 *   lines() → List.copyOf(internalList). addLine: if (confirmed) throw ...
 */

public class Task05 {
    public static void main(String[] args) {
        // TODO: addLine x2, total, confirm, addLine после confirm (поймать),
        //       проверить что lines() — копия
    }
}
