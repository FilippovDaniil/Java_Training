package m44_databases_intro.practice;

/**
 * Задача 01 — Модуль 44: Создание таблицы (DDL)
 *
 * ЗАДАНИЕ (SQL):
 *   Напишите SQL-скрипт, создающий таблицу books со столбцами:
 *     - id    — целое, первичный ключ, автонумерация;
 *     - title — строка до 200 символов, обязательная (NOT NULL);
 *     - author— строка до 100 символов;
 *     - year  — целое;
 *     - price — точное дробное (DECIMAL).
 *   Впишите готовый SQL в текст-блок ниже и выполните его в H2/любой СУБД.
 *
 * ЗАПУСК:
 *   H2 Console, DBeaver, или psql/mysql — любой SQL-клиент.
 *
 * ПОДСКАЗКА:
 *   CREATE TABLE books ( id INT PRIMARY KEY AUTO_INCREMENT, ... );
 */
public class Task01 {
    public static void main(String[] args) {
        String sql = """
            -- Напишите здесь CREATE TABLE books (...);
            """;
        System.out.println(sql);
    }
}
