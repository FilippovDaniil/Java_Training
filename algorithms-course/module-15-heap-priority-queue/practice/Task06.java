/**
 * Задача 06 — Тема 15: Сравнить heapsort и quicksort на случайных данных
 *
 * ЗАДАНИЕ:
 *   Сравните время heapsort (тема 15) и quicksort (тема 09) на одном большом
 *   случайном массиве. Оба — O(n log n) в среднем, но quicksort обычно быстрее
 *   по «константе» (лучше работает с кэшем процессора), хотя heapsort даёт
 *   гарантию O(n log n) и in-place.
 *
 * ПРИМЕР / ВЫВОД (числа зависят от железа — важна тенденция):
 *   n=1_000_000
 *   heapsort:  t= 180 ms
 *   quicksort: t=  95 ms
 *   Оба O(n log n); quicksort быстрее по константе, heapsort — гарантированный худший случай.
 *
 * ТРЕБОВАНИЯ:
 *   - сортировать КОПИИ одного массива;
 *   - проверить, что обе дают одинаковый отсортированный результат;
 *   - не забыть прогрев перед замером.
 *
 * ПОДСКАЗКА:
 *   Реализации можно взять из задачи 03 (heapsort) и темы 09 (quicksort).
 */

public class Task06 {

    static void heapSort(int[] a) {
        // TODO: heapsort (из задачи 03)
    }
    static void quickSort(int[] a, int lo, int hi) {
        // TODO: quicksort (из темы 09)
    }

    public static void main(String[] args) {
        int n = 1_000_000;
        // TODO: случайный массив; замерить heapsort и quicksort на копиях; сравнить время и результат
    }
}
