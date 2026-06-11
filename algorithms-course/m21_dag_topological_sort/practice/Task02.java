package m21_dag_topological_sort.practice;

/**
 * Задача 02 — Тема 21: Самый длинный путь в DAG (DP на топопорядке)
 *
 * ЗАДАНИЕ:
 *   Найдите длину самого длинного пути во взвешенном DAG. Обрабатывайте вершины
 *   в топологическом порядке (задача 01) и релаксируйте «на максимум»:
 *   dist[v] = max(dist[v], dist[u] + w(u,v)).
 *
 * ПРИМЕР / ВЫВОД:
 *   рёбра с весами: 0->1(3), 0->2(2), 1->3(4), 2->3(1)
 *   самый длинный путь = 7   (0->1->3 = 3+4)
 *
 * ТРЕБОВАНИЯ:
 *   - сначала топопорядок; затем DP с релаксацией по max;
 *   - dist[исток]=0; ответ — максимум по всем dist (или dist до заданной цели);
 *   - O(V + E).
 *
 * ПОДСКАЗКА:
 *   Когда обрабатываем u в топопорядке, dist[u] уже финально (все предки учтены).
 */

import java.util.ArrayList;
import java.util.List;

public class Task02 {

    record Edge(int to, int w) {}

    static int longestPath(int n, List<List<Edge>> adj) {
        // TODO: топопорядок (по indeg); DP dist[v]=max(dist[v], dist[u]+w); вернуть max dist
        return 0;
    }

    static List<List<Edge>> build(int n, int[][] edges) {
        List<List<Edge>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
        for (int[] e : edges) adj.get(e[0]).add(new Edge(e[1], e[2]));
        return adj;
    }

    public static void main(String[] args) {
        var adj = build(4, new int[][]{{0,1,3},{0,2,2},{1,3,4},{2,3,1}});
        System.out.println("самый длинный путь = " + longestPath(4, adj));   // 7
    }
}
