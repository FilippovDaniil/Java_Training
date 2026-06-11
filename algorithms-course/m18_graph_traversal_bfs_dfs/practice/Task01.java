package m18_graph_traversal_bfs_dfs.practice;

/**
 * Задача 01 — Тема 18: Рекурсивный DFS для орграфа
 *
 * ЗАДАНИЕ:
 *   Реализуйте рекурсивный поиск в глубину для ОРиентированного графа,
 *   собирая порядок посещения вершин начиная со стартовой.
 *
 * ПРИМЕР / ВЫВОД:
 *   орграф: 0->1, 0->2, 1->3, 2->3
 *   dfs от 0: [0, 1, 3, 2]   (порядок зависит от порядка соседей)
 *
 * ТРЕБОВАНИЯ:
 *   - массив visited; не посещать вершину дважды;
 *   - порядок посещения собирать в список (pre-order: добавляем при входе);
 *   - старт — заданная вершина.
 *
 * ПОДСКАЗКА:
 *   dfs(u): visited[u]=true; order.add(u); для каждого соседа v если !visited[v] dfs(v).
 */

import java.util.ArrayList;
import java.util.List;

public class Task01 {

    static List<Integer> order = new ArrayList<>();
    static boolean[] visited;

    static void dfs(int u, List<List<Integer>> adj) {
        // TODO: пометить, добавить в order, рекурсия по непосещённым соседям
    }

    static List<List<Integer>> build(int n, int[][] edges) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
        for (int[] e : edges) adj.get(e[0]).add(e[1]);   // орграф
        return adj;
    }

    public static void main(String[] args) {
        var adj = build(4, new int[][]{{0,1},{0,2},{1,3},{2,3}});
        visited = new boolean[4];
        dfs(0, adj);
        System.out.println("dfs от 0: " + order);
    }
}
