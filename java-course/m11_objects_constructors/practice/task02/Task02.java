package m11_objects_constructors.practice.task02;

/**
 * Задача 02 — Модуль 11: Перегрузка конструкторов и this(...)
 *
 * ЗАДАНИЕ:
 *   У класса Rectangle сделайте ДВА конструктора:
 *     - Rectangle(int width, int height) — задаёт обе стороны;
 *     - Rectangle(int side) — квадрат; должен вызвать первый
 *       конструктор через this(side, side).
 *   Добавьте метод area(). В main создайте прямоугольник и квадрат,
 *   выведите их площади.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   Площадь прямоугольника: 12
 *   Площадь квадрата: 25
 *
 * ПОДСКАЗКА:
 *   Вызов this(...) должен быть ПЕРВОЙ строкой конструктора.
 */

public class Task02 {
    public static void main(String[] args) {
        // Создайте Rectangle(3,4) и Rectangle(5), выведите площади

        Rectangle pryam = new Rectangle(3,4);
        Rectangle kvadrat = new Rectangle(5);
        System.out.println("Square of pryam: " + pryam.area());
        System.out.println("Square of kvadrat: " + kvadrat.area());
    }
}
