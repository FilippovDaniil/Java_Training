package m22_dynamic_programming_intro.practice.task07;

/**
 * Модуль Data-Cruncher: оптимизация набора объектов (рюкзак) + схожесть строк.
 *  - bestSelection: из объектов с весами и ценностями выбрать максимально ценный
 *    набор в пределах лимита (рюкзак 0/1).
 *  - similarity: «похожесть» двух строк через редакционное расстояние
 *    (например 1 - dist/maxLen).
 */
public class OptimizerModule {

    /** Максимальная суммарная ценность объектов, влезающих в лимит (рюкзак 0/1). */
    public int bestSelection(int[] weights, int[] values, int capacity) {
        // TODO: DP рюкзака (как в задаче 03)
        return 0;
    }

    /** Редакционное расстояние (Левенштейн) между строками. */
    public int editDistance(String a, String b) {
        // TODO: DP (как в задаче 05)
        return 0;
    }

    /** Похожесть в [0..1]: 1 - editDistance / max(len). */
    public double similarity(String a, String b) {
        int maxLen = Math.max(a.length(), b.length());
        if (maxLen == 0) return 1.0;
        // TODO: вернуть 1.0 - (double) editDistance(a,b) / maxLen
        return 0;
    }
}
