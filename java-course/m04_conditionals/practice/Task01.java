package m04_conditionals.practice;

/**
 * Задача 01 — Модуль 04: Знак числа
 *
 * ЗАДАНИЕ:
 *   Считайте целое число и определите, положительное оно,
 *   отрицательное или равно нулю.
 *
 * ПРИМЕРЫ:
 *   Ввод: 5    → Положительное
 *   Ввод: -3   → Отрицательное
 *   Ввод: 0    → Ноль
 *
 * ПОДСКАЗКА:
 *   Используйте if / else if / else.
 */
import java.util.Scanner;

public class Task01 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter number:");
        int n = scanner.nextInt();
        if (n > 0){
            System.out.println("Polojitelnoe");
        } else if (n < 0){
            System.out.println("Otricatelnoe");
        } else {
            System.out.println("Ravno 0");
        }

        scanner.close();
    }
}
