/**
 * Задача 01 — Модуль 17: Первый try-catch
 *
 * ЗАДАНИЕ:
 *   Считайте два числа и выведите результат деления первого на второе.
 *   Перехватите деление на ноль (ArithmeticException) и выведите
 *   понятное сообщение вместо аварийного завершения.
 *
 * ПРИМЕРЫ:
 *   Ввод: 10 2 → Результат: 5
 *   Ввод: 10 0 → Ошибка: деление на ноль
 *
 * ПОДСКАЗКА:
 *   try { ... 10/0 ... } catch (ArithmeticException e) { ... }
 */
import java.util.Scanner;

public class Task01 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        // Ваш код здесь

        scanner.close();
    }
}
