package m16_bitset_disjoint_set_union.practice.task07;

/**
 * Задача 07 (мини-проект Data-Cruncher) — Тема 16: уникальные (BitSet) + кластеры (DSU)
 *
 * ЗАДАНИЕ:
 *   Добавьте в Data-Cruncher Clusterizer (файл Clusterizer.java):
 *     - uniqueCount — подсчёт уникальных значений через BitSet (когда диапазон известен);
 *     - cluster — группировка элементов по парам похожести через DSU.
 *
 * ПРИМЕР / ВЫВОД:
 *   values=[3,1,3,7,1,7,7], maxValue=10
 *   уникальных: 3   (это 1,3,7)
 *   n=6, пары похожести: (0-1),(1-2),(4-5)
 *   кластеров: 3   ({0,1,2},{3},{4,5})
 *
 * ТРЕБОВАНИЯ:
 *   - uniqueCount: O(n + диапазон/64), без сортировки и без HashSet;
 *   - cluster: DSU; число кластеров = число различных корней;
 *   - оба метода — практические применения структур темы 16.
 *
 * ПОДСКАЗКА:
 *   BitSet хорош, когда значения в ограниченном диапазоне; иначе берут HashSet.
 */

public class Task07 {
    public static void main(String[] args) {
        Clusterizer c = new Clusterizer();
        System.out.println("уникальных: " + c.uniqueCount(new int[]{3, 1, 3, 7, 1, 7, 7}, 10));
        System.out.println("кластеров: " + c.cluster(6, new int[][]{{0, 1}, {1, 2}, {4, 5}}));
    }
}
