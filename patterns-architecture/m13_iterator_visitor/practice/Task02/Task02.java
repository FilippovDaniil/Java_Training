package m13_iterator_visitor.practice.task02;

/**
 * Задача 02 — Тема 13: Iterator, вычисляющий элементы на лету
 *
 * ЗАДАНИЕ:
 *   Реализуйте диапазон чисел БЕЗ внутреннего списка — элементы порождаются
 *   итератором по ходу обхода:
 *     - Range (файл Range.java) implements Iterable<Integer>: поля from, to
 *       (включительно); iterator() возвращает Iterator, который выдаёт from,
 *       from+1, ..., to, не храня их в коллекции.
 *   В main переберите Range(1, 5) в for-each и выведите числа.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   1 2 3 4 5
 *
 * ТРЕБОВАНИЯ:
 *   - Range НЕ хранит элементы в List — итератор считает следующее значение сам;
 *   - hasNext() истинно, пока текущее <= to;
 *   - for-each по Range работает за счёт Iterable.
 *
 * ПОДСКАЗКА:
 *   В итераторе держите поле current = from; hasNext() = current <= to;
 *   next() = current++ (вернуть и увеличить).
 */

public class Task02 {
    public static void main(String[] args) {
        // TODO: переберите new Range(1, 5) в for-each, печатая числа
    }
}
