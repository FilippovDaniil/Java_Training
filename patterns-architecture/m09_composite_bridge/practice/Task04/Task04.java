package m09_composite_bridge.practice.task04;

/**
 * Задача 04 — Тема 09: Bridge (фигура × способ отрисовки)
 *
 * ЗАДАНИЕ:
 *   Разнесите две оси изменчивости — «что рисуем» и «как рисуем» — по двум
 *   иерархиям, связанным композицией:
 *     - Renderer (файл Renderer.java) — реализация: String renderCircle(int r);
 *       String renderSquare(int side); реализации VectorRenderer ("vector ...")
 *       и RasterRenderer ("raster ...");
 *     - Shape (файл Shape.java) — абстракция: хранит Renderer, abstract String draw();
 *       Circle (файл Circle.java) и Square (файл Square.java) делегируют рендереру.
 *   В main нарисуйте круг вектором и квадрат растром (комбинируйте свободно).
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   vector circle r=5
 *   raster square side=4
 *
 * ТРЕБОВАНИЯ:
 *   - добавление новой фигуры или нового рендерера НЕ требует классов вида
 *     VectorCircle/RasterCircle (нет комбинаторного взрыва);
 *   - Shape хранит ссылку на Renderer (мост) и делегирует ему;
 *   - фигуру и рендерер можно сочетать в любой комбинации.
 *
 * ПОДСКАЗКА:
 *   new Circle(5, new VectorRenderer()). Circle.draw() = renderer.renderCircle(r).
 *   N фигур + M рендереров = N+M классов вместо N*M.
 */

public class Task04 {
    public static void main(String[] args) {
        // TODO: создайте Circle с VectorRenderer и Square с RasterRenderer, вызовите draw()
    }
}
