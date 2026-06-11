package m04_conditionals.practice;

/**
 * Задача 03 — Модуль 04: Максимум из трёх чисел
 *
 * ЗАДАНИЕ:
 *   Считайте три целых числа и выведите наибольшее из них.
 *
 * ПРИМЕР:
 *   Ввод: 3 9 5  → Максимум: 9
 *
 * ПОДСКАЗКА:
 *   Сравнивайте числа между собой через if и логические операторы (&&),
 *   либо находите максимум поэтапно во временной переменной.
 */
import java.util.Scanner;

public class Task03 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int c = scanner.nextInt();
        // Ваш код здесь

        scanner.close();
    }
}
