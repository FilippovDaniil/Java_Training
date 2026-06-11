package m47_transactions.practice;

/**
 * Задача 02 — Модуль 47: ROLLBACK
 *
 * ЗАДАНИЕ (SQL) для таблицы accounts (см. Task01):
 *   1) начните транзакцию;
 *   2) сделайте два UPDATE (изменив балансы);
 *   3) вместо COMMIT выполните ROLLBACK;
 *   4) проверьте SELECT-ом, что балансы НЕ изменились — транзакция
 *      отменена.
 *   В комментарии опишите, в каких ситуациях нужен ROLLBACK
 *   (например, при ошибке в середине операции).
 *
 * ПОДСКАЗКА:
 *   BEGIN; UPDATE ...; UPDATE ...; ROLLBACK;
 *   После ROLLBACK данные останутся как до BEGIN.
 */
public class Task02 {
    public static void main(String[] args) {
        String sql = """
            -- Напишите транзакцию с ROLLBACK и проверку
            """;
        System.out.println(sql);
    }
}
