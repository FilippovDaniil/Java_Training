/**
 * Задача 06 — Тема 17: Проверка двудольности графа (BFS, 2 цвета)
 *
 * ЗАДАНИЕ:
 *   Определите, является ли НЕориентированный граф ДВУДОЛЬНЫМ: можно ли
 *   раскрасить вершины в 2 цвета так, чтобы концы каждого ребра были разного
 *   цвета. Используйте BFS: красьте стартовую вершину в цвет 0, соседей — в 1,
 *   их соседей — в 0 и т.д.; конфликт цветов => НЕ двудольный.
 *
 * ПРИМЕР / ВЫВОД:
 *   цикл 0-1-2-3-0 (чётной длины) -> двудольный: true
 *   треугольник 0-1-2-0 (нечётный цикл) -> двудольный: false
 *
 * ТРЕБОВАНИЯ:
 *   - массив color (например -1 = не покрашен, 0/1 — цвета);
 *   - BFS от каждой непокрашенной вершины (граф может быть несвязным);
 *   - конфликт (сосед того же цвета) => false.
 *
 * ПОДСКАЗКА:
 *   Нечётный цикл — единственная причина недвудольности.
 */

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Task06 {

    static boolean isBipartite(int n, List<List<Integer>> adj) {
        int[] color = new int[n];
        java.util.Arrays.fill(color, -1);
        // TODO: BFS-раскраска от каждой непокрашенной вершины; конфликт -> false
        return true;
    }

    static List<List<Integer>> build(int n, int[][] edges) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
        for (int[] e : edges) { adj.get(e[0]).add(e[1]); adj.get(e[1]).add(e[0]); }
        return adj;
    }

    public static void main(String[] args) {
        System.out.println("цикл 4: " + isBipartite(4, build(4, new int[][]{{0,1},{1,2},{2,3},{3,0}})));
        System.out.println("треугольник: " + isBipartite(3, build(3, new int[][]{{0,1},{1,2},{2,0}})));
    }
}
