package m13_iterator_visitor.practice.task01;

/**
 * Задача 01 — Тема 13: Iterator (своя Iterable-коллекция)
 *
 * ЗАДАНИЕ:
 *   Реализуйте простую коллекцию, по которой работает for-each:
 *     - Bag<T> (файл Bag.java) implements Iterable<T>: внутри List<T>,
 *       метод add(T), и iterator() возвращает свой Iterator (hasNext/next).
 *   В main: добавьте несколько строк и переберите Bag в цикле for-each.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   яблоко
 *   груша
 *   слива
 *
 * ТРЕБОВАНИЯ:
 *   - Bag реализует Iterable<T> (метод iterator());
 *   - возвращаемый Iterator корректно реализует hasNext() и next();
 *   - for-each по Bag работает «из коробки» (за счёт Iterable).
 *
 * ПОДСКАЗКА:
 *   Внутренний анонимный Iterator: поле int i; hasNext() = i < size;
 *   next() = items.get(i++).
 */

public class Task01 {
    public static void main(String[] args) {
        // TODO: создайте Bag<String>, добавьте элементы, переберите for-each
    }
}
