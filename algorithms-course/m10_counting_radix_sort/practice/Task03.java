package m10_counting_radix_sort.practice;

/**
 * Задача 03 — Тема 10: LSD Radix sort для десятичных чисел
 *
 * ЗАДАНИЕ:
 *   Реализуйте поразрядную сортировку (LSD) неотрицательных целых по
 *   ДЕСЯТИЧНЫМ цифрам: от младшего разряда к старшему, каждый разряд —
 *   стабильным counting sort (10 корзин: цифры 0..9).
 *
 * ПРИМЕР / ВЫВОД:
 *   До:    [170, 45, 75, 90, 2, 802, 24, 66]
 *   После: [2, 24, 45, 66, 75, 90, 170, 802]
 *
 * ТРЕБОВАНИЯ:
 *   - число проходов = число цифр в максимальном значении;
 *   - каждый разряд сортируется СТАБИЛЬНО (иначе результат неверен);
 *   - цифра разряда d: (x / pow10) % 10, где pow10 = 1,10,100,...
 *
 * ПОДСКАЗКА:
 *   for (exp=1; max/exp>0; exp*=10) countingSortByDigit(a, exp);
 */

import java.util.Arrays;

public class Task03 {

    static void radixSort(int[] a) {
        // TODO: найти max; для каждого разряда exp — стабильный counting по цифре
    }

    static void countingSortByDigit(int[] a, int exp) {
        // TODO: counting sort по цифре (a[i]/exp)%10, стабильный, результат обратно в a
    }

    public static void main(String[] args) {
        int[] a = {170, 45, 75, 90, 2, 802, 24, 66};
        radixSort(a);
        System.out.println(Arrays.toString(a));
    }
}
