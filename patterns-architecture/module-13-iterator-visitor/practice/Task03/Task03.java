/**
 * Задача 03 — Тема 13: Visitor (операция над иерархией, double dispatch)
 *
 * ЗАДАНИЕ:
 *   Посчитайте суммарную площадь фигур, вынеся операцию в Visitor:
 *     - интерфейс ShapeVisitor (файл ShapeVisitor.java): void visitCircle(Circle c);
 *       void visitSquare(Square s);
 *     - интерфейс Shape (файл Shape.java): void accept(ShapeVisitor v);
 *     - Circle (radius) и Square (side): accept вызывает соответствующий метод
 *       визитора (this);
 *     - AreaVisitor (файл AreaVisitor.java): копит площади (круг ~ Math.PI*r*r,
 *       квадрат side*side), метод long total().
 *   В main: пройдите список фигур, у каждой вызовите accept(areaVisitor),
 *   выведите суммарную площадь.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   Суммарная площадь: 28   (круг r=2 ~12 + квадрат 4 = 16)
 *
 * ТРЕБОВАНИЯ:
 *   - операция площади НЕ лежит в классах фигур — она в визиторе;
 *   - double dispatch: shape.accept(v) → v.visitXxx(this);
 *   - добавление новой операции = новый визитор без правок Circle/Square.
 *
 * ПОДСКАЗКА:
 *   Circle.accept(v) { v.visitCircle(this); } — так визитор узнаёт точный тип.
 */

import java.util.List;

public class Task03 {
    public static void main(String[] args) {
        // TODO: List<Shape> из Circle и Square; для каждой accept(AreaVisitor);
        //       вывести total()
    }
}
