package m09_quick_sort.practice.task07;

/**
 * Модуль Data-Cruncher: быстрый поиск медианы и квантилей через Quick Select.
 * Не сортируя весь массив, находит k-ю порядковую статистику за O(n) в среднем.
 */
public class QuantileFinder {

    /** k-й наименьший элемент (0-индексация). O(n) в среднем. */
    public int select(int[] a, int k) {
        // TODO: Quick Select по копии массива (чтобы не портить исходные данные)
        return 0;
    }

    /** Медиана: при нечётной длине — средний элемент; при чётной — нижняя медиана. */
    public int median(int[] a) {
        // TODO: select(a, a.length / 2) (или среднее двух центральных — на ваш выбор)
        return 0;
    }

    /** q-й квантиль, q в (0,1): например q=0.25 — нижний квартиль. */
    public int quantile(int[] a, double q) {
        // TODO: индекс = (int)(q * (a.length - 1)); select по нему
        return 0;
    }
}
