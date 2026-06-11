package m19_shortest_paths_dijkstra_bellman_ford.practice;

/**
 * Задача 02 — Тема 19: Восстановление пути источник -> цель
 *
 * ЗАДАНИЕ:
 *   Расширьте Дейкстру: помимо расстояний верните сам ПУТЬ от source до target,
 *   запоминая prev[v] (предыдущую вершину на оптимальном пути).
 *
 * ПРИМЕР / ВЫВОД:
 *   граф: 0-1(4), 0-2(1), 2-1(2), 1-3(1), 2-3(5)
 *   path(0, 3) -> [0, 2, 1, 3]  (стоимость 4)
 *   path(0, 0) -> [0]
 *
 * ТРЕБОВАНИЯ:
 *   - вести prev[]: при успешной релаксации prev[v] = u;
 *   - восстановить путь от target по prev до source, развернуть;
 *   - если target недостижим — вернуть пустой список.
 *
 * ПОДСКАЗКА:
 *   prev[start] = -1. Восстановление как в BFS (тема 18): идём от target по prev.
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class Task02 {

    record Edge(int to, int w) {}

    static List<Integer> shortestPath(int source, int target, List<List<Edge>> adj) {
        int n = adj.size();
        int[] dist = new int[n]; int[] prev = new int[n];
        java.util.Arrays.fill(dist, Integer.MAX_VALUE);
        java.util.Arrays.fill(prev, -1);
        // TODO: Дейкстра с заполнением prev; затем восстановить путь source->target
        return new ArrayList<>();
    }

    static List<List<Edge>> build(int n, int[][] edges) {
        List<List<Edge>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
        for (int[] e : edges) { adj.get(e[0]).add(new Edge(e[1], e[2])); adj.get(e[1]).add(new Edge(e[0], e[2])); }
        return adj;
    }

    public static void main(String[] args) {
        var adj = build(4, new int[][]{{0,1,4},{0,2,1},{2,1,2},{1,3,1},{2,3,5}});
        System.out.println("path 0->3: " + shortestPath(0, 3, adj));
    }
}
