package m03_linked_lists.practice;

/**
 * Задача 03 — Тема 03: reverse() односвязного списка (итеративно и рекурсивно)
 *
 * ЗАДАНИЕ:
 *   Разверните односвязный список ДВУМЯ способами:
 *     - reverseIterative(Node head): Node — тремя указателями (prev, cur, next);
 *     - reverseRecursive(Node head): Node — рекурсивно.
 *   Оба возвращают новую голову развёрнутого списка.
 *
 * ПРИМЕР / ВЫВОД:
 *   До:    10 -> 20 -> 30 -> null
 *   После: 30 -> 20 -> 10 -> null  (оба способа дают одинаковый результат)
 *
 * ТРЕБОВАНИЯ:
 *   - не создавать новые узлы — переставлять ссылки существующих;
 *   - корректно работать для пустого списка и списка из 1 узла;
 *   - итеративный — память O(1); рекурсивный — память O(n) на стеке.
 *
 * ПОДСКАЗКА:
 *   Итеративно: prev=null; while(cur!=null){ next=cur.next; cur.next=prev; prev=cur; cur=next; } return prev;
 *   Рекурсивно: дойти до конца, на возврате «переворачивать» ссылки.
 */

public class Task03 {

    static class Node {
        int value; Node next;
        Node(int value) { this.value = value; }
    }

    static Node reverseIterative(Node head) {
        // TODO: три указателя prev/cur/next
        return head;
    }

    static Node reverseRecursive(Node head) {
        // TODO: базовый случай head==null || head.next==null; иначе рекурсия + перевязка
        return head;
    }

    // helper: построить список из значений
    static Node build(int... values) {
        Node dummy = new Node(0), tail = dummy;
        for (int v : values) { tail.next = new Node(v); tail = tail.next; }
        return dummy.next;
    }

    // helper: напечатать список
    static void print(Node head) {
        StringBuilder sb = new StringBuilder();
        for (Node c = head; c != null; c = c.next) sb.append(c.value).append(" -> ");
        sb.append("null");
        System.out.println(sb);
    }

    public static void main(String[] args) {
        Node a = build(10, 20, 30);
        System.out.print("До:    "); print(a);
        Node r = reverseIterative(a);
        System.out.print("После (iter): "); print(r);
        // TODO: проверьте reverseRecursive на новом списке
    }
}
