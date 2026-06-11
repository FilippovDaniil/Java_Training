package m04_conditionals.practice;

/**
 * Задача 06 — Модуль 04: Високосный год
 *
 * ЗАДАНИЕ:
 *   Считайте год и определите, високосный он или нет.
 *   Год високосный, если он делится на 4, НО не делится на 100,
 *   ЛИБО делится на 400.
 *
 * ПРИМЕРЫ:
 *   Ввод: 2024 → Високосный
 *   Ввод: 1900 → Невисокосный
 *   Ввод: 2000 → Високосный
 *
 * ПОДСКАЗКА:
 *   Условие: (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)
 *   Используйте логические операторы && и ||.
 */
import java.util.Scanner;

public class Task06 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int year = scanner.nextInt();
        // Ваш код здесь

        scanner.close();
    }
}
