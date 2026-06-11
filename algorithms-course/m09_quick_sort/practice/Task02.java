package m09_quick_sort.practice;

/**
 * Задача 02 — Тема 09: Quicksort с разбиением Хоара (обработка равных ключей)
 *
 * ЗАДАНИЕ:
 *   Реализуйте быструю сортировку с разбиением Хоара (два указателя навстречу).
 *   Хоар делает меньше обменов и устойчивее к массивам с дубликатами.
 *
 * ПРИМЕР / ВЫВОД:
 *   До:    [5, 3, 5, 1, 5, 2, 5, 4]   (много равных ключей)
 *   После: [1, 2, 3, 4, 5, 5, 5, 5]
 *
 * ТРЕБОВАНИЯ:
 *   - ВНИМАНИЕ: при разбиении Хоара рекурсия идёт по [lo, p] и [p+1, hi]
 *     (НЕ как у Ломуто!) — иначе зацикливание;
 *   - pivot — например a[lo] или средний; указатели i (слева) и j (справа);
 *   - корректно работать на массиве из одинаковых элементов (не зациклиться).
 *
 * ПОДСКАЗКА:
 *   Классический Hoare: do i++ while a[i]<pivot; do j-- while a[j]>pivot;
 *   if i>=j return j; swap(i,j). Стартовые i=lo-1, j=hi+1.
 */

import java.util.Arrays;

public class Task02 {

    static void quicksort(int[] a, int lo, int hi) {
        // TODO: базовый случай lo>=hi; p=partitionHoare; рекурсия [lo,p] и [p+1,hi]
    }

    static int partitionHoare(int[] a, int lo, int hi) {
        // TODO: два указателя навстречу, вернуть точку разделения j
        return lo;
    }

    public static void main(String[] args) {
        int[] a = {5, 3, 5, 1, 5, 2, 5, 4};
        quicksort(a, 0, a.length - 1);
        System.out.println(Arrays.toString(a));
    }
}
