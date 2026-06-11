package m06_search_linear_binary.practice;

/**
 * Задача 02 — Тема 06: Бинарный поиск (итеративно)
 *
 * ЗАДАНИЕ:
 *   Реализуйте итеративный бинарный поиск в ОТСОРТИРОВАННОМ массиве.
 *   Верните индекс найденного элемента или -1.
 *
 * ПРИМЕР / ВЫВОД:
 *   a = [1, 3, 5, 7, 9, 11, 13]
 *   search(7)  -> 3
 *   search(11) -> 5
 *   search(8)  -> -1
 *
 * ТРЕБОВАНИЯ:
 *   - O(log n); массив отсортирован по возрастанию;
 *   - mid считайте как lo + (hi - lo) / 2 (защита от переполнения);
 *   - инвариант: искомое (если есть) всегда в [lo, hi].
 *
 * ПОДСКАЗКА:
 *   while (lo <= hi): сравнить a[mid] с target, сдвинуть lo=mid+1 или hi=mid-1.
 */

public class Task02 {

    static int search(int[] a, int target) {
        // TODO: итеративный бинарный поиск
        return -1;
    }

    public static void main(String[] args) {
        int[] a = {1, 3, 5, 7, 9, 11, 13};
        System.out.println("search(7) = " + search(a, 7));
        System.out.println("search(11) = " + search(a, 11));
        System.out.println("search(8) = " + search(a, 8));
    }
}
