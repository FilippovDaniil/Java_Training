/**
 * Задача 02 — Тема 20: Алгоритм Прима (ленивая версия с PriorityQueue)
 *
 * ЗАДАНИЕ:
 *   Постройте MST алгоритмом Прима: растите дерево от стартовой вершины,
 *   каждый раз добавляя самое дешёвое ребро, ведущее наружу. «Самое дешёвое
 *   ребро на границе» выдаёт min-heap. Верните суммарный вес MST.
 *
 * ПРИМЕР / ВЫВОД:
 *   граф (неориентир.): 0-1(3), 0-2(1), 1-3(2), 2-3(4), 1-2(2)
 *   вес MST = 5
 *
 * ТРЕБОВАНИЯ:
 *   - PriorityQueue рёбер по весу; начать с вершины 0;
 *   - при извлечении ребра пропускать те, что ведут в УЖЕ включённую вершину (ленивость);
 *   - включив новую вершину, добавить в кучу её рёбра; суммировать вес.
 *
 * ПОДСКАЗКА:
 *   inMST[] — включена ли вершина. Извлекаем минимальное ребро; если его конец уже в MST — skip.
 */

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Task02 {

    record Edge(int to, int w) {}

    static int prim(int n, List<List<Edge>> adj) {
        // TODO: ленивый Прим с PriorityQueue по весу; вернуть суммарный вес MST
        return 0;
    }

    static List<List<Edge>> build(int n, int[][] edges) {
        List<List<Edge>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
        for (int[] e : edges) { adj.get(e[0]).add(new Edge(e[1], e[2])); adj.get(e[1]).add(new Edge(e[0], e[2])); }
        return adj;
    }

    public static void main(String[] args) {
        var adj = build(4, new int[][]{{0,1,3},{0,2,1},{1,3,2},{2,3,4},{1,2,2}});
        System.out.println("вес MST = " + prim(4, adj));   // 5
    }
}
