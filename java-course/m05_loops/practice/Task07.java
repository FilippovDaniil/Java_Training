package m05_loops.practice;

/**
 * Задача 07 — Модуль 05 (МИНИ-ПРОЕКТ): Анализ потока чисел
 *
 * ЗАДАНИЕ:
 *   Считывайте целые числа с клавиатуры в цикле, пока не будет
 *   введён 0 (ноль — сигнал окончания, в подсчёте НЕ участвует).
 *   После завершения выведите статистику:
 *     - сколько всего чисел введено (без нуля)
 *     - сколько среди них положительных и сколько отрицательных
 *     - их сумму
 *     - максимальное и минимальное число
 *
 * ПРИМЕР:
 *   Ввод: 5 -2 10 -7 3 0
 *   Вывод:
 *     Количество: 5
 *     Положительных: 3
 *     Отрицательных: 2
 *     Сумма: 9
 *     Максимум: 10
 *     Минимум: -7
 *
 * ПОДСКАЗКА:
 *   Используйте while(true) + break при вводе 0, либо do-while.
 *   Заведите счётчики и аккумуляторы ДО цикла. Для max/min
 *   обновляйте текущее значение при каждом числе.
 */
import java.util.Scanner;

public class Task07 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int count = 0;        // общее количество чисел (без нуля)
        int positive = 0;     // количество положительных
        int negative = 0;     // количество отрицательных
        int sum = 0;          // сумма всех чисел
        int max = Integer.MIN_VALUE;  // начальное значение – минимально возможное
        int min = Integer.MAX_VALUE;  // начальное значение – максимально возможное

        System.out.println("Enter numbers: ");

        while (true) {
            int n = scanner.nextInt();
            if (n == 0) break;   // признак окончания ввода

            count++;
            sum += n;

            if (n > 0) positive++;
            else if (n < 0) negative++;

            if (n > max) max = n;
            if (n < min) min = n;
        }

        // Проверка: были ли введены числа, кроме нуля?
        if (count == 0) {
            System.out.println("Null");
        } else {
            System.out.println("Count: " + count);
            System.out.println("Poloj: " + positive);
            System.out.println("Otric: " + negative);
            System.out.println("Sum: " + sum);
            System.out.println("Max: " + max);
            System.out.println("Min: " + min);
        }

        scanner.close();
    }
}
