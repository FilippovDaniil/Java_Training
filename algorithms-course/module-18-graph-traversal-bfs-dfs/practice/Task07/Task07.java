/**
 * Задача 07 (мини-проект Data-Cruncher) — Тема 18: связность + кратчайший путь
 *
 * ЗАДАНИЕ:
 *   Добавьте в Data-Cruncher GraphAnalyzer (файл GraphAnalyzer.java):
 *   подсчёт компонент связности и кратчайший путь (по числу рёбер) между
 *   двумя точками через BFS. Это анализ структуры загруженного графа (тема 17).
 *
 * ПРИМЕР / ВЫВОД:
 *   узлы 0..5, рёбра (0-1),(1-2),(3-4)
 *   компонент: 3
 *   кратчайший путь 0->2: [0, 1, 2]
 *   путь 0->4: []   (в разных компонентах)
 *
 * ТРЕБОВАНИЯ:
 *   - countComponents — число «островов» в графе;
 *   - shortestHops — BFS-путь; пустой список, если узлы несвязны;
 *   - переиспользуйте идеи из задач 03 и 04.
 *
 * ПОДСКАЗКА:
 *   Кратчайший путь в невзвешенном графе = BFS (НЕ DFS).
 */

public class Task07 {
    public static void main(String[] args) {
        GraphAnalyzer g = new GraphAnalyzer(6);
        g.addEdge(0, 1); g.addEdge(1, 2); g.addEdge(3, 4);
        System.out.println("компонент: " + g.countComponents());
        System.out.println("путь 0->2: " + g.shortestHops(0, 2));
        System.out.println("путь 0->4: " + g.shortestHops(0, 4));
    }
}
