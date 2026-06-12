package m16_bitset_disjoint_set_union.practice.task07;

/**
 * Модуль Data-Cruncher: уникальные элементы через BitSet и кластеризация через DSU.
 *  - uniqueCount: если значения в известном диапазоне, отметить их в BitSet и
 *    посчитать установленные биты (число уникальных) за O(n + диапазон/64).
 *  - cluster: объединить элементы по парам «похожести» (DSU) и вернуть число групп.
 */
import java.util.BitSet;

public class Clusterizer {

    /** Число уникальных неотрицательных значений в массиве (через BitSet). */
    public int uniqueCount(int[] values, int maxValue) {
        BitSet seen = new BitSet(maxValue + 1);
        // TODO: пометить каждое значение; вернуть seen.cardinality()
        return 0;
    }

    /**
     * Кластеризация: n элементов, pairs — пары «похожих» (их объединяем в кластер).
     * Вернуть число получившихся кластеров (через DSU).
     */
    public int cluster(int n, int[][] pairs) {
        // TODO: DSU на n; union по парам; число различных корней = число кластеров
        return n;
    }
}
