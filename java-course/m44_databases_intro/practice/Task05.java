package m44_databases_intro.practice;

/**
 * Задача 05 — Модуль 44: Ограничения (constraints)
 *
 * ЗАДАНИЕ (SQL):
 *   Создайте таблицу students с продуманными ограничениями:
 *     - id      — PK, автонумерация;
 *     - email   — UNIQUE, NOT NULL;
 *     - name    — NOT NULL;
 *     - gpa     — DECIMAL(3,2), с CHECK-ограничением 0.0..5.0;
 *     - group_name — VARCHAR(20), значение по умолчанию 'не назначена'.
 *   Затем попробуйте вставить строку, нарушающую UNIQUE или CHECK,
 *   и опишите в комментарии, что произойдёт.
 *
 * ПОДСКАЗКА:
 *   gpa DECIMAL(3,2) CHECK (gpa >= 0 AND gpa <= 5),
 *   group_name VARCHAR(20) DEFAULT 'не назначена'
 */
public class Task05 {
    public static void main(String[] args) {
        String sql = """
            -- Напишите здесь CREATE TABLE students с ограничениями
            """;
        System.out.println(sql);
    }
}
