package m11_objects_constructors.practice.task01;

import jdk.swing.interop.SwingInterOpUtils;

/**
 * Задача 01 — Модуль 11: Конструктор с параметрами
 *
 * ЗАДАНИЕ:
 *   Добавьте классу Point конструктор с параметрами (x, y),
 *   инициализирующий поля через this. В main создайте две точки
 *   и выведите их координаты.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   Точка (3, 5)
 *   Точка (-2, 7)
 *
 * ПОДСКАЗКА:
 *   Point(int x, int y) { this.x = x; this.y = y; }
 */

public class Task01 {
    public static void main(String[] args) {
        // Создайте объекты Point через конструктор и выведите их

        Point point1 = new Point(55,44);
        Point point2 = new Point(33,22);

        System.out.println(point1);
        System.out.println(point2);
    }
}
