package m09_quick_sort.practice;

/**
 * Задача 03 — Тема 09: Рандомизированный выбор опорного элемента
 *
 * ЗАДАНИЕ:
 *   Доработайте quicksort (Ломуто из задачи 01) так, чтобы pivot выбирался
 *   СЛУЧАЙНО: перед разбиением меняйте местами a[hi] со случайным элементом
 *   из [lo, hi]. Это делает худший случай O(n²) маловероятным независимо
 *   от входных данных (в т.ч. на уже отсортированных).
 *
 * ПРИМЕР / ВЫВОД:
 *   До:    [1, 2, 3, 4, 5, 6, 7, 8]   (уже отсортирован — худший вход для pivot=last)
 *   После: [1, 2, 3, 4, 5, 6, 7, 8]   (но без O(n²)-деградации благодаря рандомизации)
 *
 * ТРЕБОВАНИЯ:
 *   - случайный индекс в [lo, hi], swap с a[hi], затем обычный partition Ломуто;
 *   - используйте java.util.Random;
 *   - результат корректно отсортирован.
 *
 * ПОДСКАЗКА:
 *   int r = lo + rnd.nextInt(hi - lo + 1); swap(a, r, hi); далее как в задаче 01.
 */

import java.util.Arrays;
import java.util.Random;

public class Task03 {

    static final Random rnd = new Random(42);   // фикс. seed для воспроизводимости

    static void quicksort(int[] a, int lo, int hi) {
        // TODO: базовый случай; рандомизация pivot; partition; рекурсия
    }

    static int partition(int[] a, int lo, int hi) {
        // TODO: сначала swap случайного элемента с a[hi], затем разбиение Ломуто
        return lo;
    }

    public static void main(String[] args) {
        int[] a = {1, 2, 3, 4, 5, 6, 7, 8};
        quicksort(a, 0, a.length - 1);
        System.out.println(Arrays.toString(a));
    }
}
