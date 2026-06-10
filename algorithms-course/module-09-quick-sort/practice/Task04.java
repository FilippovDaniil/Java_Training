/**
 * Задача 04 — Тема 09: Производительность на отсортированных данных (quicksort vs merge)
 *
 * ЗАДАНИЕ:
 *   Покажите «ахиллесову пяту» наивного quicksort (pivot=last) на УЖЕ
 *   ОТСОРТИРОВАННОМ массиве — это его худший случай O(n²). Сравните:
 *     - quicksort с pivot=last;
 *     - quicksort с рандомизированным pivot (задача 03);
 *     - merge sort (тема 08).
 *   на отсортированном входе. Замерьте время.
 *
 * ПРИМЕР / ВЫВОД (числа зависят от железа — важна тенденция):
 *   n=20000 (отсортированный вход)
 *   quicksort(last):  t= 900 ms   (деградация O(n²)!) или StackOverflowError
 *   quicksort(rand):  t=   4 ms
 *   mergeSort:        t=   3 ms
 *
 * ТРЕБОВАНИЯ:
 *   - вход — отсортированный массив 0..n-1;
 *   - сортируйте КОПИИ; если quicksort(last) падает с StackOverflowError —
 *     поймайте и отметьте это как проявление худшего случая;
 *   - сделайте вывод о важности выбора pivot.
 *
 * ПОДСКАЗКА:
 *   На отсортированном входе pivot=last всегда максимум -> разбиение даёт
 *   подмассивы размера n-1 и 0 -> глубина рекурсии n.
 */

public class Task04 {

    static void quicksortLast(int[] a, int lo, int hi) {
        // TODO: quicksort с pivot=last (Ломуто без рандомизации)
    }

    static void quicksortRandom(int[] a, int lo, int hi) {
        // TODO: quicksort с рандомизированным pivot (из задачи 03)
    }

    static void mergeSort(int[] a, int lo, int hi) {
        // TODO: merge sort (из темы 08)
    }

    public static void main(String[] args) {
        int n = 20_000;
        // TODO: отсортированный вход; замерить три алгоритма на копиях;
        //       quicksortLast — в try/catch (возможен StackOverflowError)
    }
}
