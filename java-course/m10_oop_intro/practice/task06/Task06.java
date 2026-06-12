package m10_oop_intro.practice.task06;

/**
 * Задача 06 — Модуль 10: Массив объектов одной иерархии
 *
 * ЗАДАНИЕ:
 *   Используя иерархию Shape (поле name) → Circle, Square:
 *   1. Сделайте Circle и Square наследниками Shape.
 *   2. Добавьте каждому поля (Circle: radius; Square: side).
 *   3. Создайте массив Shape[] и положите в него объекты Circle и Square
 *      (потомок можно положить в переменную типа родителя!).
 *   4. В цикле выведите name каждой фигуры.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   Фигура: Круг
 *   Фигура: Квадрат
 *
 * ПОДСКАЗКА:
 *   Shape[] shapes = { new Circle(), new Square() };
 *   Это первый шаг к полиморфизму (подробнее — модуль 22).
 */

public class Task06 {
    public static void main(String[] args) {
        // Создайте массив Shape с разными фигурами и выведите их имена
        Shape[] shapes = {
                new Circle("circle",47),
                new Square("square", 56),
                new Shape("shape")
        };

        for (Shape shape : shapes) {
            System.out.println(shape.getName());
        }
    }
}
