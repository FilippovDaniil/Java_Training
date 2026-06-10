/**
 * Односвязный список целых чисел. Хранит head (и опционально tail для O(1) addLast).
 */
public class SinglyLinkedList {

    static class Node {
        int value; Node next;
        Node(int value) { this.value = value; }
    }

    private Node head;
    private Node tail;   // для addLast за O(1)
    private int size;

    /** Вставить в начало. O(1). */
    public void addFirst(int value) {
        // TODO: новый узел, n.next = head, head = n; если был пуст — tail = n; size++
    }

    /** Вставить в конец. O(1) при наличии tail. */
    public void addLast(int value) {
        // TODO: если пуст — addFirst; иначе tail.next = n, tail = n; size++
    }

    /** Удалить и вернуть первый элемент. O(1). Бросить исключение, если пусто. */
    public int removeFirst() {
        // TODO: проверить пустоту; вернуть head.value, head = head.next; если стал пуст — tail=null; size--
        return 0;
    }

    public int size() { return size; }

    @Override public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (Node c = head; c != null; c = c.next) { sb.append(c.value); if (c.next != null) sb.append(", "); }
        return sb.append("]").toString();
    }
}
