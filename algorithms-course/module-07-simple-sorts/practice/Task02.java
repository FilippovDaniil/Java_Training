/**
 * Задача 02 — Тема 07: Сортировка выбором (selection sort)
 *
 * ЗАДАНИЕ:
 *   Реализуйте сортировку выбором: на каждом шаге найдите минимум в
 *   неотсортированной части и поставьте его в начало этой части.
 *
 * ПРИМЕР / ВЫВОД:
 *   До:    [5, 3, 8, 1, 2]
 *   После: [1, 2, 3, 5, 8]
 *
 * ТРЕБОВАНИЯ:
 *   - in-place; ровно n-1 обменов (минимум обменов среди простых сортировок);
 *   - всегда O(n²) сравнений (не адаптивна);
 *   - находите ИНДЕКС минимума, затем один swap.
 *
 * ПОДСКАЗКА:
 *   for i in [0, n-1): min=i; for j in [i+1, n): if a[j]<a[min] min=j; swap(a,i,min).
 */

import java.util.Arrays;

public class Task02 {

    static void selectionSort(int[] a) {
        // TODO: сортировка выбором
    }

    public static void main(String[] args) {
        int[] a = {5, 3, 8, 1, 2};
        selectionSort(a);
        System.out.println(Arrays.toString(a));
    }
}
