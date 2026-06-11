package m07_simple_sorts.practice.task07;

/**
 * Модуль Data-Cruncher: выбор алгоритма сортировки по размеру данных.
 * Для малых массивов (n < THRESHOLD) выгоднее insertion sort (мало накладных
 * расходов, адаптивна). Для больших — алгоритм O(n log n) (будет подключён в теме 08).
 */
public class AdaptiveSorter {

    static final int THRESHOLD = 50;

    /** Отсортировать массив, выбрав алгоритм по его размеру. */
    public void sort(int[] a) {
        // TODO: если a.length < THRESHOLD — insertionSort(a);
        //       иначе — пока заглушка (в теме 08 здесь будет mergeSort),
        //       можно временно тоже insertionSort + пометка TODO
    }

    void insertionSort(int[] a) {
        // TODO: сортировка вставками
    }

    /** Какой алгоритм будет выбран для массива данного размера (для наглядности). */
    public String chooseAlgorithm(int n) {
        // TODO: вернуть "insertion" или "mergeSort (тема 08)"
        return "?";
    }
}
