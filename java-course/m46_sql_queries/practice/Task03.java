package m46_sql_queries.practice;

/**
 * Задача 03 — Модуль 46: HAVING
 *
 * ЗАДАНИЕ (SQL) для таблицы sales:
 *   1) категории, у которых суммарная выручка больше 10000
 *      (GROUP BY + HAVING SUM);
 *   2) категории, в которых более 3 продаж (HAVING COUNT);
 *   3) категории со средним чеком выше 1000, при этом учитывайте
 *      только продажи с amount > 0 (WHERE до группировки + HAVING после).
 *
 * ЦЕЛЬ:
 *   Понять разницу: WHERE фильтрует строки ДО группировки,
 *   HAVING — группы ПОСЛЕ агрегации.
 *
 * ПОДСКАЗКА:
 *   SELECT category, SUM(amount) FROM sales
 *   GROUP BY category HAVING SUM(amount) > 10000;
 */
public class Task03 {
    public static void main(String[] args) {
        String sql = """
            -- Напишите запросы с GROUP BY ... HAVING
            """;
        System.out.println(sql);
    }
}
