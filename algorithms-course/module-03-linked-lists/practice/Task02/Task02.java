/**
 * Задача 02 — Тема 03: remove(int index) + граничные случаи
 *
 * ЗАДАНИЕ:
 *   Добавьте в SinglyLinkedList метод remove(int index), корректно
 *   обрабатывающий ВСЕ граничные случаи: удаление головы (index 0),
 *   удаление последнего элемента, удаление из середины, неверный индекс.
 *
 * ПРИМЕР / ВЫВОД:
 *   [10, 20, 30, 40]
 *   remove(0) -> [20, 30, 40]
 *   remove(2) -> [20, 30]
 *   remove(5) -> IndexOutOfBoundsException
 *
 * ТРЕБОВАНИЯ:
 *   - index вне диапазона -> IndexOutOfBoundsException;
 *   - не терять хвост при перевязке;
 *   - size уменьшается на 1 при успешном удалении.
 *
 * ПОДСКАЗКА:
 *   Для index>0 нужен указатель на узел ПЕРЕД удаляемым (prev),
 *   тогда prev.next = prev.next.next.
 */

public class Task02 {
    public static void main(String[] args) {
        SinglyLinkedList list = new SinglyLinkedList();
        for (int v : new int[]{10, 20, 30, 40}) list.addLast(v);
        System.out.println(list);
        // TODO: вызовите remove для головы, середины, конца; поймайте неверный индекс
    }
}
