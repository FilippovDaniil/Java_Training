/**
 * Задача 03 — Тема 21: Количество различных путей между s и t в DAG
 *
 * ЗАДАНИЕ:
 *   Посчитайте число различных путей от s до t в DAG методом DP по
 *   топологическому порядку: ways[s]=1; для каждой вершины u в топопорядке
 *   прибавляйте ways[u] всем её преемникам.
 *
 * ПРИМЕР / ВЫВОД:
 *   рёбра: 0->1, 0->2, 1->3, 2->3, 0->3
 *   путей 0->3 = 3   (0->3; 0->1->3; 0->2->3)
 *
 * ТРЕБОВАНИЯ:
 *   - ways[s] = 1, остальные 0;
 *   - обрабатывать в топопорядке; ways[v] += ways[u] для ребра u->v;
 *   - вернуть ways[t].
 *
 * ПОДСКАЗКА:
 *   Считать имеет смысл только вершины, достижимые из s; топопорядок это учитывает.
 */

import java.util.ArrayList;
import java.util.List;

public class Task03 {

    static long countPaths(int n, List<List<Integer>> adj, int s, int t) {
        // TODO: топопорядок; ways[s]=1; ways[v]+=ways[u]; вернуть ways[t]
        return 0;
    }

    static List<List<Integer>> build(int n, int[][] edges) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
        for (int[] e : edges) adj.get(e[0]).add(e[1]);
        return adj;
    }

    public static void main(String[] args) {
        var adj = build(4, new int[][]{{0,1},{0,2},{1,3},{2,3},{0,3}});
        System.out.println("путей 0->3 = " + countPaths(4, adj, 0, 3));   // 3
    }
}
