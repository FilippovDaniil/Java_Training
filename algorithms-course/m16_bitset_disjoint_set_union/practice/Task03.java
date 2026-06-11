package m16_bitset_disjoint_set_union.practice;

/**
 * Задача 03 — Тема 16: DSU со сжатием пути и объединением по рангу
 *
 * ЗАДАНИЕ:
 *   Доработайте DSU двумя эвристиками:
 *     - сжатие пути (path compression) в find: подвешивать пройденные узлы к корню;
 *     - объединение по рангу (union by rank): меньшее дерево вешать к большему.
 *   Вместе они дают амортизированную сложность ~O(α(n)) ≈ O(1).
 *
 * ПРИМЕР / ВЫВОД:
 *   После серии union на 1_000_000 элементах find работает практически мгновенно.
 *   connected даёт верные ответы; глубина деревьев остаётся крошечной.
 *
 * ТРЕБОВАНИЯ:
 *   - find с path compression (например parent[x] = find(parent[x]));
 *   - union by rank: сравнивать ранги корней, меньший вешать к большему,
 *     при равных рангах — увеличить ранг нового корня;
 *   - корректность connected на нескольких объединениях.
 *
 * ПОДСКАЗКА:
 *   rank — это верхняя оценка высоты дерева; растёт только при объединении равных рангов.
 */

public class Task03 {

    static class DSU {
        private final int[] parent, rank;
        DSU(int n) {
            parent = new int[n]; rank = new int[n];
            for (int i = 0; i < n; i++) parent[i] = i;
        }
        int find(int x) {
            // TODO: путь со сжатием
            return x;
        }
        void union(int x, int y) {
            // TODO: union by rank
        }
        boolean connected(int x, int y) { return find(x) == find(y); }
    }

    public static void main(String[] args) {
        DSU dsu = new DSU(10);
        dsu.union(0, 1); dsu.union(1, 2); dsu.union(3, 4); dsu.union(2, 4);
        System.out.println("connected(0,4) = " + dsu.connected(0, 4));   // true
        System.out.println("connected(0,5) = " + dsu.connected(0, 5));   // false
    }
}
