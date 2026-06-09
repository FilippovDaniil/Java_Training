/**
 * Задача 03 — Тема 07: Decorator (базовый)
 *
 * ЗАДАНИЕ:
 *   Реализуйте напиток, который можно «обвешивать» добавками через декораторы:
 *     - интерфейс Coffee (файл Coffee.java): long costCents(); String description();
 *     - SimpleCoffee (файл SimpleCoffee.java): базовая цена 10000, описание "кофе";
 *     - абстрактный CoffeeDecorator (файл CoffeeDecorator.java) реализует Coffee
 *       и хранит обёрнутый Coffee inner;
 *     - Milk (+2000, " + молоко") и Sugar (+500, " + сахар") расширяют поведение,
 *       делегируя inner.
 *   В main соберите "кофе + сахар + молоко" обёртками и выведите цену и описание.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   кофе + сахар + молоко
 *   Итого: 12500
 *
 * ТРЕБОВАНИЯ:
 *   - декораторы и SimpleCoffee реализуют ОДИН интерфейс Coffee (можно вкладывать);
 *   - каждый декоратор вызывает inner.costCents()/inner.description() и дополняет;
 *   - базовый класс не теряется (молоко не «затирает» кофе, а добавляется к нему).
 *
 * ПОДСКАЗКА:
 *   new Milk(new Sugar(new SimpleCoffee())). Каждый costCents() =
 *   inner.costCents() + своя надбавка.
 */

public class Task03 {
    public static void main(String[] args) {
        // TODO: оберните SimpleCoffee в Sugar и Milk, выведите description() и costCents()
    }
}
