package m05_loops.practice;

/**
 * Задача 03 — Модуль 05: Факториал
 *
 * ЗАДАНИЕ:
 *   Считайте число N и вычислите его факториал (N!) с помощью цикла.
 *   N! = 1 * 2 * 3 * ... * N.  По определению 0! = 1.
 *
 * ПРИМЕРЫ:
 *   Ввод: 5 → 120
 *   Ввод: 0 → 1
 *
 * ПОДСКАЗКА:
 *   Аккумулятор для произведения начинайте с 1 (не с 0!).
 *   Для больших N используйте тип long.
 */
import java.util.Scanner;

public class Task03 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        // Ваш код здесь

        scanner.close();
    }
}
