package m33_guava_apache_commons.practice;

/**
 * Задача 05 — Модуль 33: Joiner и Splitter
 *
 * ТРЕБУЕТСЯ ЗАВИСИМОСТЬ: com.google.guava:guava:33.2.1-jre
 *
 * ЗАДАНИЕ:
 *   1. С помощью Joiner соберите список слов в строку через ", ",
 *      пропуская null-значения (skipNulls).
 *   2. С помощью Splitter разберите «грязную» строку
 *      "a, b, , c ,d" в список, обрезая пробелы (trimResults) и
 *      пропуская пустые элементы (omitEmptyStrings).
 *   3. Дополнительно: Joiner.withKeyValueSeparator для Map → строка.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   Склейка: яблоко, банан, груша
 *   Разбор: [a, b, c, d]
 *
 * ПОДСКАЗКА:
 *   Joiner.on(", ").skipNulls().join(list);
 *   Splitter.on(',').trimResults().omitEmptyStrings().splitToList(s);
 */
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;

public class Task05 {
    public static void main(String[] args) {
        // Используйте Joiner и Splitter
        String joined = Joiner.on(", ").skipNulls().join("apple","banano", "grusha");  // "a, c"
        System.out.println(joined);
    }
}
