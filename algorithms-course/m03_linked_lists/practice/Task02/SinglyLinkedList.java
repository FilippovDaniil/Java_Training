package m03_linked_lists.practice.task02;

/**
 * Односвязный список с методом remove(int index). Точка фокуса задачи 02.
 */
public class SinglyLinkedList {

    static class Node {
        int value; Node next;
        Node(int value) { this.value = value; }
    }

    private Node head;
    private int size;

    public void addLast(int value) {
        Node n = new Node(value);
        if (head == null) { head = n; }
        else { Node c = head; while (c.next != null) c = c.next; c.next = n; }
        size++;
    }

    /**
     * Удалить элемент по индексу (0-based). O(n).
     * Граничные случаи: index==0 (удаление головы), последний индекс, неверный индекс.
     */
    public void remove(int index) {
        // TODO: проверить 0 <= index < size (иначе IndexOutOfBoundsException);
        //       index==0 -> head = head.next;
        //       иначе дойти до узла перед index, перевязать prev.next = prev.next.next;
        //       size--
    }

    public int size() { return size; }

    @Override public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (Node c = head; c != null; c = c.next) { sb.append(c.value); if (c.next != null) sb.append(", "); }
        return sb.append("]").toString();
    }
}
