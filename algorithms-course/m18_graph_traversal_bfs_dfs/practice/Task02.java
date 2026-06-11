package m18_graph_traversal_bfs_dfs.practice;

/**
 * Задача 02 — Тема 18: Итеративный DFS через стек
 *
 * ЗАДАНИЕ:
 *   Реализуйте DFS БЕЗ рекурсии, используя явный стек. Это спасает от
 *   StackOverflowError на глубоких графах. Соберите порядок посещения.
 *
 * ПРИМЕР / ВЫВОД:
 *   орграф: 0->1, 0->2, 1->3, 2->3
 *   итеративный dfs от 0: [0, 2, 3, 1]  (порядок может отличаться от рекурсивного
 *                                        из-за порядка снятия со стека)
 *
 * ТРЕБОВАНИЯ:
 *   - использовать java.util.Deque как стек;
 *   - помечать вершину посещённой при СНЯТИИ со стека (и пропускать повторные);
 *   - собрать порядок посещения.
 *
 * ПОДСКАЗКА:
 *   push(start); while(!empty){ u=pop(); if(visited[u]) continue; visited[u]=true;
 *   order.add(u); push непосещённых соседей }.
 */

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Task02 {

    static List<Integer> dfsIterative(int start, List<List<Integer>> adj) {
        List<Integer> order = new ArrayList<>();
        boolean[] visited = new boolean[adj.size()];
        Deque<Integer> stack = new ArrayDeque<>();
        // TODO: итеративный DFS
        return order;
    }

    static List<List<Integer>> build(int n, int[][] edges) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
        for (int[] e : edges) adj.get(e[0]).add(e[1]);
        return adj;
    }

    public static void main(String[] args) {
        var adj = build(4, new int[][]{{0,1},{0,2},{1,3},{2,3}});
        System.out.println("итеративный dfs от 0: " + dfsIterative(0, adj));
    }
}
