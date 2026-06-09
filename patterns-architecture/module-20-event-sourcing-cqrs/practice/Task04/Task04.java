/**
 * Задача 04 — Тема 20: Projection (read-модель из событий)
 *
 * ЗАДАНИЕ:
 *   Постройте проекцию-статистику, проигрывая поток событий:
 *     - события OrderPlaced(orderId), OrderShipped(orderId) — records (готовы);
 *     - OrderStatsProjection (файл OrderStatsProjection.java): счётчики placed и
 *       shipped; метод on(Object event) увеличивает нужный счётчик; placed(); shipped().
 *   В main прогоните лог [Placed, Placed, Shipped] через проекцию и выведите
 *   статистику.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   Размещено: 2, отгружено: 1
 *
 * ТРЕБОВАНИЯ:
 *   - проекция строит представление из событий, не хранит «исходное» состояние;
 *   - on(event) только обновляет read-модель (никаких бизнес-правил);
 *   - из того же лога можно построить другую проекцию, не меняя события.
 *
 * ПОДСКАЗКА:
 *   on(e): if (e instanceof OrderPlaced) placed++; if (e instanceof OrderShipped) shipped++;
 *   Несколько проекций = несколько представлений одного лога.
 */

import java.util.List;

public class Task04 {
    public static void main(String[] args) {
        // TODO: соберите List<Object> событий, прогоните через проекцию, выведите статистику
    }
}
