/**
 * Задача 05 — Модуль 47: Уровни изоляции
 *
 * ЗАДАНИЕ (SQL + анализ):
 *   1) посмотрите текущий уровень изоляции вашей СУБД (в H2/PostgreSQL/
 *      MySQL команда отличается — найдите её);
 *   2) установите уровень READ COMMITTED для транзакции
 *      (SET TRANSACTION ISOLATION LEVEL READ COMMITTED);
 *   3) в комментарии заполните таблицу: какой уровень какие аномалии
 *      (грязное/неповторяющееся/фантомное чтение) допускает.
 *
 * АНАЛИЗ (заполните в комментарии):
 *   READ UNCOMMITTED → допускает: ...
 *   READ COMMITTED   → допускает: ...
 *   REPEATABLE READ  → допускает: ...
 *   SERIALIZABLE     → допускает: ...
 *
 * ПОДСКАЗКА:
 *   SET TRANSACTION ISOLATION LEVEL <уровень>;
 *   Сверьтесь с таблицей аномалий в theory.md.
 */
public class Task05 {
    public static void main(String[] args) {
        String sql = """
            -- Установите уровень изоляции; в комментарии заполните таблицу аномалий
            """;
        System.out.println(sql);
    }
}
