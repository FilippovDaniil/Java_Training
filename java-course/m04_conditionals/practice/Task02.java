package m04_conditionals.practice;

/**
 * Задача 02 — Модуль 04: Чётное или нечётное
 *
 * ЗАДАНИЕ:
 *   Считайте целое число и определите его чётность.
 *
 * ПРИМЕРЫ:
 *   Ввод: 8  → Чётное
 *   Ввод: 7  → Нечётное
 *
 * ПОДСКАЗКА:
 *   Число чётное, если остаток от деления на 2 равен нулю: n % 2 == 0
 */
import java.util.Scanner;

public class Task02 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter number: ");
        int n = scanner.nextInt();
        String result = (n % 2 == 0) ? "Chetnoe" : "Nechetnoe";
        System.out.println(result);
        scanner.close();
    }
}
