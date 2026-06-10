/**
 * Задача 05 — Тема 18: Обнаружение цикла в орграфе (цветовая маркировка)
 *
 * ЗАДАНИЕ:
 *   Определите, есть ли в ОРиентированном графе цикл, с помощью DFS и трёх
 *   цветов: 0 (белый — не посещена), 1 (серый — в текущем стеке рекурсии),
 *   2 (чёрный — обработана). Встретили серую вершину => цикл.
 *
 * ПРИМЕР / ВЫВОД:
 *   0->1, 1->2, 2->0  (цикл) -> hasCycle: true
 *   0->1, 1->2, 0->2  (DAG)  -> hasCycle: false
 *
 * ТРЕБОВАНИЯ:
 *   - массив color (0/1/2); DFS от каждой белой вершины;
 *   - при переходе в серую вершину -> цикл найден;
 *   - корректно для несвязного орграфа.
 *
 * ПОДСКАЗКА:
 *   dfs(u): color[u]=1; для соседа v: if color[v]==1 -> цикл; if color[v]==0 -> dfs(v);
 *   в конце color[u]=2.
 */

import java.util.ArrayList;
import java.util.List;

public class Task05 {

    static int[] color;

    static boolean hasCycle(int n, List<List<Integer>> adj) {
        color = new int[n];
        // TODO: запустить DFS-проверку от каждой белой вершины
        return false;
    }

    // TODO: вспомогательный dfs(u, adj): boolean — true, если найден цикл

    static List<List<Integer>> build(int n, int[][] edges) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
        for (int[] e : edges) adj.get(e[0]).add(e[1]);
        return adj;
    }

    public static void main(String[] args) {
        System.out.println("цикл: " + hasCycle(3, build(3, new int[][]{{0,1},{1,2},{2,0}})));
        System.out.println("DAG:  " + hasCycle(3, build(3, new int[][]{{0,1},{1,2},{0,2}})));
    }
}
