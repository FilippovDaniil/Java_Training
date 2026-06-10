/**
 * Модуль Data-Cruncher: прогоняет набор алгоритмов с замером времени.
 * Будет расширяться в следующих темах (сортировки, поиск, графы...).
 */
public class ComplexityAnalyzer {

    /** Прогнать все алгоритмы на массиве и напечатать таблицу замеров. */
    public void analyze(int[] data) {
        System.out.println("=== Data-Cruncher: ComplexityAnalyzer (n=" + data.length + ") ===");
        // TODO: замерить и напечатать каждый алгоритм (название, сложность, время)
        //       используйте time(...) для замера
    }

    /** O(n): минимум массива. */
    int min(int[] a) {
        // TODO
        return 0;
    }

    /** O(n^2): есть ли хотя бы одна пара равных элементов (наивно). */
    boolean hasDuplicatePairs(int[] a) {
        // TODO: вложенные циклы
        return false;
    }

    /** O(log n)-проба: например, сколько раз n делится пополам. */
    int probeLogarithmic(int n) {
        // TODO
        return 0;
    }

    /** Замерить время выполнения r в наносекундах. */
    long time(Runnable r) {
        long start = System.nanoTime();
        r.run();
        return System.nanoTime() - start;
    }
}
