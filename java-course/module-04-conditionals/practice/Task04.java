/**
 * Задача 04 — Модуль 04: Тернарный оператор
 *
 * ЗАДАНИЕ:
 *   Считайте возраст. С помощью ТЕРНАРНОГО оператора (без if)
 *   определите строку "совершеннолетний" (>= 18)
 *   или "несовершеннолетний" и выведите её.
 *
 * ПРИМЕРЫ:
 *   Ввод: 20 → совершеннолетний
 *   Ввод: 15 → несовершеннолетний
 *
 * ПОДСКАЗКА:
 *   String status = (age >= 18) ? "..." : "...";
 */
import java.util.Scanner;

public class Task04 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int age = scanner.nextInt();
        // Ваш код здесь

        scanner.close();
    }
}
