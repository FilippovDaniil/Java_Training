package m23_oop_overloading_overriding_abstract.practice.task04;

/**
 * Задача 04 — Модуль 23: Иерархия фигур (площадь и периметр)
 *
 * ЗАДАНИЕ:
 *   Дан абстрактный класс Figure (поле name, абстрактные методы
 *   area() и perimeter(), обычный метод describe()).
 *   Реализуйте потомков:
 *     - Circle (radius);
 *     - Rectangle (width, height);
 *     - Triangle (три стороны a, b, c — площадь по формуле Герона).
 *   В main создайте массив Figure[] из разных фигур и в цикле
 *   вызовите describe() (полиморфизм).
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   Круг: площадь=78.54, периметр=31.42
 *   Прямоугольник: площадь=12.00, периметр=14.00
 *   Треугольник: площадь=6.00, периметр=12.00
 *
 * ПОДСКАЗКИ:
 *   - Math.PI, Math.sqrt;
 *   - формула Герона: p=(a+b+c)/2; S=sqrt(p*(p-a)*(p-b)*(p-c)).
 */

// TODO: классы Circle, Rectangle, Triangle extends Figure
public class Task04 {
    public static void main(String[] args) {
        // Создайте Figure[] из Circle, Rectangle, Triangle и обойдите его
    }
}
