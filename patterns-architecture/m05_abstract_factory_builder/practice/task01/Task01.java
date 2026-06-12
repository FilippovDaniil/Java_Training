package m05_abstract_factory_builder.practice.task01;

/**
 * Задача 01 — Тема 05: Builder (fluent)
 *
 * ЗАДАНИЕ:
 *   Реализуйте Pizza (файл Pizza.java) с вложенным fluent-Builder вместо
 *   «телескопических» конструкторов:
 *     - неизменяемые поля: size (int), cheese (boolean), pepperoni (boolean);
 *     - приватный конструктор Pizza(Builder b);
 *     - вложенный static class Builder с методами size(int), cheese(boolean),
 *       pepperoni(boolean) — каждый возвращает this — и build() → new Pizza;
 *     - метод описания toString() / describe() для вывода.
 *   В main соберите пиццу цепочкой вызовов и выведите её.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   Pizza{size=30, cheese=true, pepperoni=true}
 *
 * ТРЕБОВАНИЯ:
 *   - поля Pizza неизменяемы (final), задаются только через Builder;
 *   - методы Builder возвращают this (цепочка вызовов);
 *   - у Pizza нет публичного конструктора со всеми параметрами.
 *
 * ПОДСКАЗКА:
 *   new Pizza.Builder().size(30).cheese(true).pepperoni(true).build();
 *   Каждый сеттер билдера: { this.field = value; return this; }
 */

public class Task01 {
    public static void main(String[] args) {
        // TODO: соберите Pizza через Builder и выведите её
    }
}
