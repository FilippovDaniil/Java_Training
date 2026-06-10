/**
 * Задача 07 (мини-проект Data-Cruncher) — Тема 17: загрузка графа дорог
 *
 * ЗАДАНИЕ:
 *   Заложите в Data-Cruncher RoadGraph (файл RoadGraph.java) — взвешенный
 *   неориентированный граф дорог. Поддержите addRoad(u,v,w), roadsFrom(u) и
 *   загрузку из текста "u v w". Этот граф станет основой навигации в темах 18–20.
 *
 * ПРИМЕР / ВЫВОД:
 *   загрузка:
 *     0 1 5
 *     0 2 3
 *     2 3 2
 *   дороги из 0: [Edge[to=1, weight=5], Edge[to=2, weight=3]]
 *   дороги из 2: [Edge[to=0, weight=3], Edge[to=3, weight=2]]
 *
 * ТРЕБОВАНИЯ:
 *   - дороги неориентированные (добавляются в обе стороны);
 *   - вес — int (расстояние/время);
 *   - fromText парсит строки "u v w".
 *
 * ПОДСКАЗКА:
 *   Это «взвешенная» версия Graph из задачи 01 — храним Edge(to, weight).
 */

public class Task07 {
    public static void main(String[] args) {
        String map = "0 1 5\n0 2 3\n2 3 2";
        RoadGraph g = RoadGraph.fromText(4, map);
        System.out.println("дороги из 0: " + g.roadsFrom(0));
        System.out.println("дороги из 2: " + g.roadsFrom(2));
    }
}
