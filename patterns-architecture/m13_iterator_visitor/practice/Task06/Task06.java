package m13_iterator_visitor.practice.task06;

/**
 * Задача 06 — Тема 13: Iterator + Visitor вместе
 *
 * ЗАДАНИЕ:
 *   Итератор перебирает фигуры, а визитор выполняет операцию над каждой:
 *     - интерфейс ShapeVisitor (файл ShapeVisitor.java): void visitCircle(Circle);
 *       void visitSquare(Square);
 *     - интерфейс Shape (файл Shape.java): void accept(ShapeVisitor v);
 *       Circle (radius) и Square (side) реализуют accept;
 *     - AreaVisitor (файл AreaVisitor.java): копит суммарную площадь, total();
 *     - ShapeBag (файл ShapeBag.java) implements Iterable<Shape>: add(Shape),
 *       свой iterator().
 *   В main: наполните ShapeBag, переберите его for-each (Iterator) и для каждой
 *   фигуры вызовите accept(areaVisitor) (Visitor); выведите суммарную площадь.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   Суммарная площадь: 28
 *
 * ТРЕБОВАНИЯ:
 *   - обход коллекции — через Iterator (for-each по ShapeBag);
 *   - операция над элементом — через Visitor (accept/visit, double dispatch);
 *   - площадь не лежит в классах фигур, обход не знает их типов.
 *
 * ПОДСКАЗКА:
 *   for (Shape s : bag) s.accept(areaVisitor); — Iterator даёт элементы,
 *   Visitor выполняет операцию для точного типа каждого.
 */

public class Task06 {
    public static void main(String[] args) {
        // TODO: наполните ShapeBag, обойдите for-each, accept(AreaVisitor), выведите total()
    }
}
