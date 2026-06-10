/**
 * Задача 06 — Тема 18: Топологическая сортировка (DFS + стек)
 *
 * ЗАДАНИЕ:
 *   Для ОРиентированного АЦИКЛИЧЕСКОГО графа (DAG) постройте топологический
 *   порядок через DFS: добавляйте вершину в стек ПОСЛЕ обработки всех её
 *   потомков (post-order), затем разверните стек.
 *
 * ПРИМЕР / ВЫВОД:
 *   зависимости: 5->2, 5->0, 4->0, 4->1, 2->3, 3->1
 *   топопорядок (один из возможных): [4, 5, 2, 3, 1, 0]
 *   (важно: каждая вершина идёт РАНЬШЕ всех, на кого указывает)
 *
 * ТРЕБОВАНИЯ:
 *   - DFS со стеком результата; вершина пушится после рекурсии по потомкам;
 *   - итог — развёрнутый стек;
 *   - предполагаем, что граф ацикличен (DAG).
 *
 * ПОДСКАЗКА:
 *   После dfs(u) (все потомки обработаны) — result.push(u). В конце развернуть/читать стек сверху.
 */

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Task06 {

    static boolean[] visited;
    static Deque<Integer> stack = new ArrayDeque<>();

    static void dfs(int u, List<List<Integer>> adj) {
        // TODO: пометить; рекурсия по непосещённым потомкам; push(u) после
    }

    static List<Integer> topoSort(int n, List<List<Integer>> adj) {
        visited = new boolean[n];
        stack = new ArrayDeque<>();
        // TODO: dfs от каждой непосещённой вершины; собрать стек в список (сверху вниз)
        return new ArrayList<>();
    }

    static List<List<Integer>> build(int n, int[][] edges) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
        for (int[] e : edges) adj.get(e[0]).add(e[1]);
        return adj;
    }

    public static void main(String[] args) {
        var adj = build(6, new int[][]{{5,2},{5,0},{4,0},{4,1},{2,3},{3,1}});
        System.out.println("топопорядок: " + topoSort(6, adj));
    }
}
