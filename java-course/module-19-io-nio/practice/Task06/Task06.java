/**
 * Задача 06 — Модуль 19: Паттерн Decorator
 *
 * ЗАДАНИЕ:
 *   Реализуйте паттерн «Декоратор» на примере кофе.
 *   1. Интерфейс Coffee с методами description() и cost().
 *   2. Базовый класс SimpleCoffee implements Coffee (Кофе, 100).
 *   3. Декораторы (каждый оборачивает Coffee и добавляет своё):
 *        - MilkDecorator (+ молоко, +30);
 *        - SugarDecorator (+ сахар, +10).
 *   4. В main соберите "Кофе + молоко + сахар", оборачивая объекты
 *      друг в друга, и выведите итоговое описание и стоимость.
 *
 * ОЖИДАЕМЫЙ ВЫВОД:
 *   Кофе + молоко + сахар = 140.0
 *
 * ПОДСКАЗКА:
 *   Coffee c = new SugarDecorator(new MilkDecorator(new SimpleCoffee()));
 *   Декоратор хранит ссылку на оборачиваемый Coffee и делегирует ему вызов.
 */

// TODO: SimpleCoffee, MilkDecorator, SugarDecorator
public class Task06 {
    public static void main(String[] args) {
        // Соберите кофе из декораторов и выведите описание + стоимость
    }
}
