/**
 * Задача 01 — Тема 19: Алгоритм Дейкстры с PriorityQueue
 *
 * ЗАДАНИЕ:
 *   Найдите кратчайшие расстояния от стартовой вершины до всех остальных во
 *   взвешенном графе с НЕОТРИЦАТЕЛЬНЫМИ весами, используя min-heap (PriorityQueue).
 *
 * ПРИМЕР / ВЫВОД:
 *   граф (неориентир., с весами): 0-1(4), 0-2(1), 2-1(2), 1-3(1), 2-3(5)
 *   dist от 0: [0, 3, 1, 4]
 *   (до 1: 0->2->1 = 1+2 = 3; до 3: 0->2->1->3 = 3+1 = 4)
 *
 * ТРЕБОВАНИЯ:
 *   - dist инициализировать бесконечностью (Integer.MAX_VALUE), dist[start]=0;
 *   - PriorityQueue по возрастанию dist; пропускать устаревшие записи (d > dist[u]);
 *   - релаксация: если dist[u] + w < dist[v] -> обновить и добавить в очередь;
 *   - веса неотрицательные.
 *
 * ПОДСКАЗКА:
 *   Перед сложением проверяйте dist[u] != INF, чтобы не переполнить int.
 */

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Task01 {

    record Edge(int to, int w) {}

    static int[] dijkstra(int start, List<List<Edge>> adj) {
        int n = adj.size();
        int[] dist = new int[n];
        java.util.Arrays.fill(dist, Integer.MAX_VALUE);
        // TODO: PriorityQueue по dist; релаксация рёбер; пропуск устаревших
        return dist;
    }

    static List<List<Edge>> build(int n, int[][] edges) {
        List<List<Edge>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
        for (int[] e : edges) { adj.get(e[0]).add(new Edge(e[1], e[2])); adj.get(e[1]).add(new Edge(e[0], e[2])); }
        return adj;
    }

    public static void main(String[] args) {
        var adj = build(4, new int[][]{{0,1,4},{0,2,1},{2,1,2},{1,3,1},{2,3,5}});
        System.out.println(java.util.Arrays.toString(dijkstra(0, adj)));   // [0, 3, 1, 4]
    }
}
