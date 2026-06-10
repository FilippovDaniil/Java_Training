/**
 * Задача 04 — Тема 17: Степень каждой вершины
 *
 * ЗАДАНИЕ:
 *   Для НЕориентированного графа посчитайте степень (число соседей) каждой
 *   вершины. Дополнительно: для ОРиентированного — in-degree и out-degree.
 *
 * ПРИМЕР / ВЫВОД:
 *   неориентированный, рёбра (0-1),(0-2),(2-3):
 *   степени: [2, 1, 2, 1]
 *   орграф, рёбра 0->1, 0->2, 2->3:
 *   out: [2, 0, 1, 0], in: [0, 1, 1, 1]
 *
 * ТРЕБОВАНИЯ:
 *   - degrees(adj): степень = размер списка смежности вершины;
 *   - для орграфа out-degree = размер списка; in-degree = сколько раз вершина
 *     встречается как сосед у других;
 *   - вернуть массивы степеней.
 *
 * ПОДСКАЗКА:
 *   in-degree удобно считать одним проходом: для каждого ребра u->v делать in[v]++.
 */

import java.util.ArrayList;
import java.util.List;

public class Task04 {

    static int[] degrees(List<List<Integer>> adj) {
        // TODO: степень каждой вершины = adj.get(u).size()
        return new int[adj.size()];
    }

    static int[] inDegrees(List<List<Integer>> directedAdj) {
        // TODO: для орграфа — посчитать входящие степени
        return new int[directedAdj.size()];
    }

    static List<List<Integer>> build(int n, int[][] edges, boolean directed) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
        for (int[] e : edges) { adj.get(e[0]).add(e[1]); if (!directed) adj.get(e[1]).add(e[0]); }
        return adj;
    }

    public static void main(String[] args) {
        var undirected = build(4, new int[][]{{0,1},{0,2},{2,3}}, false);
        System.out.println("степени: " + java.util.Arrays.toString(degrees(undirected)));
        var directed = build(4, new int[][]{{0,1},{0,2},{2,3}}, true);
        System.out.println("out: " + java.util.Arrays.toString(degrees(directed)));
        System.out.println("in:  " + java.util.Arrays.toString(inDegrees(directed)));
    }
}
