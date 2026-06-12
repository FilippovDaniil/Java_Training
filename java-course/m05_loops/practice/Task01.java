package m05_loops.practice;

/**
 * Задача 01 — Модуль 05: Числа от 1 до N (for)
 *
 * ЗАДАНИЕ:
 *   Считайте число N и выведите все целые числа от 1 до N
 *   через пробел, используя цикл for.
 *
 * ПРИМЕР:
 *   Ввод: 5  → 1 2 3 4 5
 *
 * ПОДСКАЗКА:
 *   for (int i = 1; i <= n; i++) { ... }
 */
import java.util.Scanner;

public class Task01 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number: ");
        int n = scanner.nextInt();
        for (int i = 1; i <= n; i++){
            System.out.print(i + " ");
        }

        scanner.close();
    }
}
