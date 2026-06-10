/**
 * Задача 06 — Тема 09: Quick Select — k-й по порядку элемент
 *
 * ЗАДАНИЕ:
 *   Найдите k-й наименьший элемент массива (0-индексация: k=0 — минимум)
 *   за O(n) в среднем, БЕЗ полной сортировки. Используйте разбиение (как в
 *   quicksort), но рекурсию делайте только в ту половину, где лежит k.
 *
 * ПРИМЕР / ВЫВОД:
 *   a = [7, 2, 1, 6, 8, 5, 3, 4], k=3 (4-й наименьший)
 *   quickSelect -> 4
 *   k=0 -> 1 (минимум), k=7 -> 8 (максимум)
 *
 * ТРЕБОВАНИЯ:
 *   - O(n) в среднем (одна ветвь рекурсии, не две);
 *   - массив можно переставлять (partition меняет его);
 *   - после partition: если индекс pivot == k -> ответ; иначе уйти влево или вправо.
 *
 * ПОДСКАЗКА:
 *   p = partition(a, lo, hi); if (p==k) return a[p]; if (k<p) select(lo,p-1) else select(p+1,hi).
 */

public class Task06 {

    static int quickSelect(int[] a, int k) {
        // TODO: запустить select(a, 0, a.length-1, k)
        return 0;
    }

    // TODO: select(int[] a, int lo, int hi, int k) + partition(...)

    public static void main(String[] args) {
        System.out.println(quickSelect(new int[]{7, 2, 1, 6, 8, 5, 3, 4}, 3));   // 4
        System.out.println(quickSelect(new int[]{7, 2, 1, 6, 8, 5, 3, 4}, 0));   // 1
        System.out.println(quickSelect(new int[]{7, 2, 1, 6, 8, 5, 3, 4}, 7));   // 8
    }
}
