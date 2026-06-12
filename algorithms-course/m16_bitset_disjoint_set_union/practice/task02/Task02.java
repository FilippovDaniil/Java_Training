package m16_bitset_disjoint_set_union.practice.task02;

/**
 * Задача 02 — Тема 16: DSU — makeSet, find, union
 *
 * ЗАДАНИЕ:
 *   Реализуйте DisjointSet (файл DisjointSet.java): инициализация (каждый
 *   элемент — своё множество), find (поиск корня), union (объединение),
 *   connected (в одном ли множестве).
 *
 * ПРИМЕР / ВЫВОД:
 *   n=6; union(0,1); union(2,3); union(3,4)
 *   connected(0,1) -> true
 *   connected(2,4) -> true   (2-3-4 в одной группе)
 *   connected(0,5) -> false
 *
 * ТРЕБОВАНИЯ:
 *   - изначально каждый элемент в своём множестве (parent[i]=i);
 *   - find возвращает корень; union связывает корни;
 *   - пока БЕЗ оптимизаций (сжатие пути и ранг — в задаче 03).
 *
 * ПОДСКАЗКА:
 *   connected(x,y) = (find(x) == find(y)).
 */

public class Task02 {
    public static void main(String[] args) {
        DisjointSet dsu = new DisjointSet(6);
        dsu.union(0, 1); dsu.union(2, 3); dsu.union(3, 4);
        System.out.println("connected(0,1) = " + dsu.connected(0, 1));
        System.out.println("connected(2,4) = " + dsu.connected(2, 4));
        System.out.println("connected(0,5) = " + dsu.connected(0, 5));
    }
}
