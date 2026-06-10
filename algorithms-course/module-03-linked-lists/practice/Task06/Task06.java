/**
 * Задача 06 — Тема 03: Двусвязный список — addLast и removeLast за O(1)
 *
 * ЗАДАНИЕ:
 *   Реализуйте DoublyLinkedList (файл DoublyLinkedList.java), где addLast и
 *   removeLast работают за O(1) благодаря ссылкам prev и хранимому tail.
 *   Сравните: в односвязном списке removeLast — O(n) (надо найти предпоследний).
 *
 * ПРИМЕР / ВЫВОД:
 *   addLast 10,20,30 -> [10, 20, 30]
 *   removeLast -> 30, список [10, 20]
 *
 * ТРЕБОВАНИЯ:
 *   - addLast/removeLast строго O(1);
 *   - removeLast на пустом — исключение;
 *   - аккуратно с head/tail, когда список становится пустым или из одного элемента.
 *
 * ПОДСКАЗКА:
 *   removeLast: tail = tail.prev; если tail стал null — список пуст (head=null),
 *   иначе tail.next = null.
 */

public class Task06 {
    public static void main(String[] args) {
        DoublyLinkedList list = new DoublyLinkedList();
        // TODO: addLast нескольких значений; removeLast; вывод списка и size
    }
}
