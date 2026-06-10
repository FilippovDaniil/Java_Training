/**
 * Модуль Data-Cruncher: поиск по ключу + сравнение линейного и бинарного поиска.
 * Демонстрирует, во сколько раз бинарный поиск (O(log n)) обгоняет линейный (O(n))
 * на больших отсортированных данных.
 */
public class SearchBenchmark {

    /** Линейный поиск. O(n). */
    public int linearSearch(int[] a, int target) {
        // TODO
        return -1;
    }

    /** Бинарный поиск (массив отсортирован). O(log n). */
    public int binarySearch(int[] a, int target) {
        // TODO
        return -1;
    }

    /**
     * Прогнать оба поиска по queries раз на отсортированном массиве и
     * напечатать суммарное время каждого. Ожидается: бинарный многократно быстрее.
     */
    public void benchmark(int[] sortedData, int[] queries) {
        // TODO: замерить System.nanoTime() для серии linearSearch и для серии binarySearch,
        //       вывести время обоих и отметить, что бинарный значительно быстрее
    }
}
