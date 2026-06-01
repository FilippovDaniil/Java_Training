/**
 * Задача 07 — Модуль 23 (МИНИ-ПРОЕКТ): Геометрический калькулятор
 *
 * ЗАДАНИЕ:
 *   Соберите законченную систему фигур, объединяющую всю тему модуля.
 *   1. Абстрактный класс Shape: поле name; абстрактные area() и
 *      perimeter(); реализованный compareTo-подобный метод
 *      isLargerThan(Shape other) — сравнивает площади.
 *   2. Потомки: Circle, Square, Rectangle (переопределяют area/perimeter).
 *   3. Класс ShapeUtils со СТАТИЧЕСКИМИ перегруженными методами:
 *        - static double totalArea(Shape[] shapes)
 *        - static Shape largest(Shape[] shapes)  — фигура с макс. площадью
 *   4. В main:
 *        - создайте массив из нескольких разных фигур;
 *        - выведите каждую (площадь, периметр);
 *        - выведите суммарную площадь и самую большую фигуру.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   Круг: S=78.54, P=31.42
 *   Квадрат: S=25.00, P=20.00
 *   Прямоугольник: S=24.00, P=20.00
 *   Суммарная площадь: 127.54
 *   Самая большая фигура: Круг
 *
 * ПОДСКАЗКА:
 *   Объедините: абстрактный класс + переопределение (area/perimeter) +
 *   полиморфизм (массив Shape) + статические утилиты.
 */
public class Task07 {
    public static void main(String[] args) {
        // Создайте массив фигур и обработайте через ShapeUtils
    }
}

abstract class Shape {
    String name;

    Shape(String name) {
        this.name = name;
    }

    abstract double area();
    abstract double perimeter();

    boolean isLargerThan(Shape other) {
        return this.area() > other.area();
    }
}

// TODO: Circle, Square, Rectangle (extends Shape) и класс ShapeUtils со static-методами
