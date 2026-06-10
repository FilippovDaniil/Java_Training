/**
 * Модуль Data-Cruncher: основной сортировщик больших данных (merge sort).
 * Стабильный, гарантированно O(n log n). Дополняет AdaptiveSorter из темы 07.
 */
public class MergeSorter {

    /** Стабильная сортировка слиянием. O(n log n), один буфер. */
    public void sort(int[] a) {
        if (a.length < 2) return;
        int[] buf = new int[a.length];
        // TODO: рекурсивный sort(a, buf, 0, a.length-1)
    }

    // TODO: private sort(int[] a, int[] buf, int lo, int hi) + merge(...)

    /** Проверка: отсортирован ли массив по возрастанию. */
    public boolean isSorted(int[] a) {
        // TODO: пройти и убедиться a[i] <= a[i+1]
        return true;
    }
}
