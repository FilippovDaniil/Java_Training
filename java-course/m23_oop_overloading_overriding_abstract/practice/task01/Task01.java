package m23_oop_overloading_overriding_abstract.practice.task01;

/**
 * Задача 01 — Модуль 23: Перегрузка методов
 *
 * ЗАДАНИЕ:
 *   В классе Calculator создайте ПЕРЕГРУЖЕННЫЕ методы sum:
 *     - int sum(int a, int b)
 *     - double sum(double a, double b)
 *     - int sum(int a, int b, int c)
 *   В main вызовите каждый и убедитесь, что выбирается нужная версия.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   2 + 3 = 5
 *   2.5 + 3.5 = 6.0
 *   1 + 2 + 3 = 6
 *
 * ПОДСКАЗКА:
 *   Перегрузка различается по числу/типам параметров.
 */

public class Task01 {
    public static void main(String[] args) {
        // Вызовите разные версии sum
        Calculator calculator = new Calculator();
        int a = 2;
        int b = 3;
        int c = 5;
        double a1 = 2.5;
        double b1 = 3.5;
        System.out.println(a + " + " + b + " = " + calculator.sum(a,b));
        System.out.println(a1 + " + " + b1 + " = " + calculator.sum(a1,b1));
        System.out.println(a + " + " + b + " + " + c + " = " + calculator.sum(a,b,c));
    }
}
