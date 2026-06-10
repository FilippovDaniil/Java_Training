/**
 * Задача 05 — Тема 21: Планирование проекта — критический путь
 *
 * ЗАДАНИЕ:
 *   Дан проект: задачи с длительностями и зависимостями (ребро u->v: v нельзя
 *   начать, пока не закончена u). Найдите минимальное время завершения всего
 *   проекта (= длина критического пути) через DP по топопорядку:
 *     finish[v] = dur[v] + max(finish[u]) по всем предшественникам u.
 *
 * ПРИМЕР / ВЫВОД:
 *   длительности: [3, 2, 4, 1]   зависимости: 0->1, 0->2, 1->3, 2->3
 *   критический путь: 0->2->3 (3+4+1=8)
 *   минимальное время проекта = 8
 *
 * ТРЕБОВАНИЯ:
 *   - finish[v] = dur[v] + max(finish[предшественников]); у истоков = dur[v];
 *   - обрабатывать в топопорядке;
 *   - ответ — максимум finish по всем вершинам.
 *
 * ПОДСКАЗКА:
 *   Это longest path (тема, задача 02), но вес «лежит на вершинах» (длительности), а не на рёбрах.
 */

import java.util.ArrayList;
import java.util.List;

public class Task05 {

    static int projectDuration(int n, int[] dur, List<List<Integer>> adj) {
        // TODO: топопорядок; finish[v] = dur[v] + max(finish[u]); вернуть max finish
        return 0;
    }

    static List<List<Integer>> build(int n, int[][] edges) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
        for (int[] e : edges) adj.get(e[0]).add(e[1]);
        return adj;
    }

    public static void main(String[] args) {
        int[] dur = {3, 2, 4, 1};
        var adj = build(4, new int[][]{{0,1},{0,2},{1,3},{2,3}});
        System.out.println("время проекта = " + projectDuration(4, dur, adj));   // 8
    }
}
