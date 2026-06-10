/**
 * Модуль Data-Cruncher: линейная сортировка для целочисленных данных
 * с ИЗВЕСТНЫМ диапазоном. Если разброс значений (max-min) сопоставим с n,
 * counting sort за O(n+k) обгоняет O(n log n).
 */
public class RangeSorter {

    /**
     * Решает, выгодна ли counting sort: если диапазон k = max-min+1
     * не превышает factor * n — да; иначе лучше обычная сортировка.
     */
    public boolean shouldUseCounting(int n, int min, int max, int factor) {
        // TODO: long k = (long)max - min + 1; вернуть k <= (long)factor * n
        return false;
    }

    /** Counting sort со сдвигом (для произвольного диапазона целых). O(n + k). */
    public int[] countingSort(int[] a) {
        // TODO: min/max -> count размера k -> префиксные суммы -> раскладка (стабильно)
        return a.clone();
    }
}
