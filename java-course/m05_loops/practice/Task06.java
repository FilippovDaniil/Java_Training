package m05_loops.practice;

import java.util.Scanner;

/**
 * Задача 06 — Модуль 05: Таблица умножения (вложенные циклы)
 *
 * ЗАДАНИЕ:
 *   Выведите таблицу умножения от 1 до 9 в виде сетки,
 *   используя вложенные циклы.
 *
 * ФРАГМЕНТ ОЖИДАЕМОГО ВЫВОДА:
 *   1  2  3  4  5  6  7  8  9
 *   2  4  6  8  10 12 14 16 18
 *   ...
 *   9  18 27 36 45 54 63 72 81
 *
 * ПОДСКАЗКА:
 *   Внешний цикл — строки (row), внутренний — столбцы (col).
 *   Печатайте row*col с табуляцией "\t", после внутреннего цикла —
 *   System.out.println() для перехода на новую строку.
 */
public class Task06 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number: ");
        int n = scanner.nextInt();

        for (int row = 1; row <= n; row++){
            for (int col = 1; col <= n; col++){
                System.out.print(row * col + "\t");
            }
            System.out.println();
        }
    }
}
