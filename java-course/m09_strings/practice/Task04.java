package m09_strings.practice;

/**
 * Задача 04 — Модуль 09: Палиндром
 *
 * ЗАДАНИЕ:
 *   Определите, является ли введённая строка палиндромом
 *   (читается одинаково слева направо и справа налево).
 *   Игнорируйте регистр и пробелы.
 *
 * ПРИМЕРЫ:
 *   "ABBA"        → Палиндром
 *   "А роза упала на лапу Азора" → Палиндром
 *   "Hello"       → Не палиндром
 *
 * ПОДСКАЗКА:
 *   Уберите пробелы (replace(" ", "")), приведите к одному регистру,
 *   сравните строку с её разворотом через equals.
 */
import java.util.Scanner;

public class Task04 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        // Ваш код здесь

        scanner.close();
    }
}
