package m05_loops.practice;

/**
 * Задача 02 — Модуль 05: Сумма чисел (while)
 *
 * ЗАДАНИЕ:
 *   Считайте число N и вычислите сумму всех чисел от 1 до N,
 *   используя цикл while.
 *
 * ПРИМЕР:
 *   Ввод: 100 → Сумма: 5050
 *
 * ПОДСКАЗКА:
 *   Заведите аккумулятор sum = 0 ДО цикла и прибавляйте к нему i.
 */
import java.util.Scanner;

public class Task02 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number: ");
        int n = scanner.nextInt();
        int sum = 0;
        int i = 0;
        while (i <= n){
            sum = sum + i;
            i++;
        }
        System.out.println(sum);

        scanner.close();
    }
}
