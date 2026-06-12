package m03_types_keyboard_input_idea.practice;

/**
 * Задача 04 — Модуль 03: Среднее арифметическое
 *
 * ЗАДАНИЕ:
 *   Считайте три целых числа и выведите их среднее арифметическое
 *   как ДРОБНОЕ число (с точностью).
 *
 * ПРИМЕР:
 *   Ввод:  4 5 6
 *   Вывод: Среднее: 5.0
 *
 *   Ввод:  1 2 4
 *   Вывод: Среднее: 2.3333333333333335
 *
 * ПОДСКАЗКА:
 *   Чтобы деление было дробным, приведите сумму к double:
 *   double avg = (a + b + c) / 3.0;
 */
import java.util.Scanner;

public class Task04 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter 3 numbers: ");
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int c = scanner.nextInt();
        double d = (a+b+c)/3.0;
        System.out.println("Srednee arifm of numbers: " + d);
        scanner.close();
    }
}
