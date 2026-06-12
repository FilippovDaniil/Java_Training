package m17_graph_representation.practice.task01;

/**
 * Задача 01 — Тема 17: Неориентированный граф через список смежности
 *
 * ЗАДАНИЕ:
 *   Реализуйте класс Graph (файл Graph.java) на списке смежности с методами
 *   addEdge(u, v) (неориентированное ребро — в обе стороны) и neighbors(u).
 *
 * ПРИМЕР / ВЫВОД:
 *   n=4, рёбра (0-1),(0-2),(2-3)
 *   соседи 0: [1, 2]
 *   соседи 2: [0, 3]
 *   соседи 3: [2]
 *
 * ТРЕБОВАНИЯ:
 *   - неориентированное ребро добавляется в ОБА списка (u->v и v->u);
 *   - память O(V+E); перебор соседей O(deg);
 *   - вершины 0..n-1.
 *
 * ПОДСКАЗКА:
 *   adj — это List<List<Integer>>, по списку на каждую вершину.
 */

public class Task01 {
    public static void main(String[] args) {
        Graph g = new Graph(4);
        g.addEdge(0, 1); g.addEdge(0, 2); g.addEdge(2, 3);
        System.out.println("соседи 0: " + g.neighbors(0));
        System.out.println("соседи 2: " + g.neighbors(2));
        System.out.println("соседи 3: " + g.neighbors(3));
    }
}
