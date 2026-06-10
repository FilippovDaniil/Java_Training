/**
 * Двусвязный список целых чисел. Каждый узел знает prev и next.
 * Хранение head и tail даёт addLast/removeLast за O(1).
 */
public class DoublyLinkedList {

    static class DNode {
        int value; DNode prev, next;
        DNode(int value) { this.value = value; }
    }

    private DNode head, tail;
    private int size;

    /** Вставить в конец. O(1). */
    public void addLast(int value) {
        // TODO: новый узел n; если пуст — head=tail=n; иначе n.prev=tail, tail.next=n, tail=n; size++
    }

    /** Удалить и вернуть последний элемент. O(1). */
    public int removeLast() {
        // TODO: проверить пустоту; запомнить tail.value; tail = tail.prev;
        //       если tail!=null -> tail.next=null, иначе head=null; size--
        return 0;
    }

    public int size() { return size; }

    @Override public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (DNode c = head; c != null; c = c.next) { sb.append(c.value); if (c.next != null) sb.append(", "); }
        return sb.append("]").toString();
    }
}
