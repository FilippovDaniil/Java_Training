/**
 * Задача 07 (мини-проект Data-Cruncher) — Тема 19: навигация с весами + маршрут
 *
 * ЗАДАНИЕ:
 *   Добавьте в Data-Cruncher Navigator (файл Navigator.java) — навигацию по
 *   взвешенному графу дорог (тема 17) через Дейкстру: кратчайшее расстояние
 *   и сам маршрут между узлами.
 *
 * ПРИМЕР / ВЫВОД:
 *   дороги: 0-1(7), 0-2(3), 2-1(1), 1-3(2), 2-3(8)
 *   расстояние 0->3 = 6   (0->2->1->3 = 3+1+2)
 *   маршрут 0->3: [0, 2, 1, 3]
 *
 * ТРЕБОВАНИЯ:
 *   - distancesFrom — кратчайшие расстояния (Дейкстра, веса >= 0);
 *   - route — восстановленный маршрут (prev[]);
 *   - переиспользуйте Дейкстру из задач 01–02.
 *
 * ПОДСКАЗКА:
 *   Это «боевое» применение Дейкстры на графе дорог Data-Cruncher.
 */

public class Task07 {
    public static void main(String[] args) {
        Navigator nav = new Navigator(4);
        nav.addRoad(0, 1, 7); nav.addRoad(0, 2, 3); nav.addRoad(2, 1, 1);
        nav.addRoad(1, 3, 2); nav.addRoad(2, 3, 8);
        System.out.println("расстояния от 0: " + java.util.Arrays.toString(nav.distancesFrom(0)));
        System.out.println("маршрут 0->3: " + nav.route(0, 3));
    }
}
