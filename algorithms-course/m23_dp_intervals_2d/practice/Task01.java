package m23_dp_intervals_2d.practice;

/**
 * Задача 01 — Тема 23: Наибольшая возрастающая подпоследовательность (LIS)
 *
 * ЗАДАНИЕ:
 *   Реализуйте длину LIS ДВУМЯ способами:
 *     - lisQuadratic — O(n²): dp[i] = 1 + max(dp[j]) по j<i, a[j]<a[i];
 *     - lisNLogN — O(n log n): массив tails + бинарный поиск.
 *   Оба должны давать одинаковый ответ.
 *
 * ПРИМЕР / ВЫВОД:
 *   [10,9,2,5,3,7,101,18] -> 4   (например 2,3,7,18 или 2,3,7,101)
 *   [0,1,0,3,2,3] -> 4
 *
 * ТРЕБОВАНИЯ:
 *   - подпоследовательность СТРОГО возрастающая;
 *   - lisNLogN: для каждого x найти первую позицию tails >= x (lower_bound);
 *     если её нет — добавить в конец, иначе заменить;
 *   - длина LIS = размер tails.
 *
 * ПОДСКАЗКА:
 *   tails хранит наименьшие хвосты для каждой длины; это НЕ сама LIS, но её длина верна.
 */

public class Task01 {

    static int lisQuadratic(int[] a) {
        // TODO: O(n^2) DP
        return 0;
    }

    static int lisNLogN(int[] a) {
        // TODO: tails + бинарный поиск lower_bound
        return 0;
    }

    public static void main(String[] args) {
        int[] a = {10, 9, 2, 5, 3, 7, 101, 18};
        System.out.println("O(n^2):   " + lisQuadratic(a));   // 4
        System.out.println("O(nlogn): " + lisNLogN(a));        // 4
    }
}
