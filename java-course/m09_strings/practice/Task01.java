package m09_strings.practice;

/**
 * Задача 01 — Модуль 09: Базовые методы String
 *
 * ЗАДАНИЕ:
 *   Дана строка. Выведите:
 *     - её длину;
 *     - первый и последний символ;
 *     - строку в верхнем регистре.
 *
 * ПРИМЕР (для "Programming"):
 *   Длина: 11
 *   Первый символ: P
 *   Последний символ: g
 *   В верхнем регистре: PROGRAMMING
 *
 * ПОДСКАЗКА:
 *   length(), charAt(индекс), последний индекс = length()-1,
 *   toUpperCase().
 */
public class Task01 {
    public static void main(String[] args) {
        String s = "Programming";
        System.out.println("Length: " + s.length());
        System.out.println("First char: " + s.charAt(0));
        System.out.println("Last char: " + s.charAt(s.length()-1));
        System.out.println("Uppercase: " + s.toUpperCase());
    }
}
