package m03_linked_lists.practice;

/**
 * Задача 04 — Тема 03: Середина списка за один проход (черепаха и заяц)
 *
 * ЗАДАНИЕ:
 *   Найдите узел-середину односвязного списка за ОДИН проход, используя два
 *   указателя: slow (на 1 шаг) и fast (на 2 шага). Когда fast достигнет конца,
 *   slow окажется в середине.
 *
 * ПРИМЕР / ВЫВОД:
 *   [1,2,3,4,5]      -> середина 3
 *   [1,2,3,4,5,6]    -> середина 4 (для чётной длины — второй из двух средних)
 *
 * ТРЕБОВАНИЯ:
 *   - ровно один проход, без подсчёта длины заранее;
 *   - память O(1);
 *   - для чётной длины верните второй средний узел (как делает fast=2).
 *
 * ПОДСКАЗКА:
 *   while (fast != null && fast.next != null) { slow = slow.next; fast = fast.next.next; }
 *   return slow;
 */

public class Task04 {

    static class Node {
        int value; Node next;
        Node(int value) { this.value = value; }
    }

    static Node middle(Node head) {
        // TODO: slow/fast
        return head;
    }

    static Node build(int... values) {
        Node dummy = new Node(0), tail = dummy;
        for (int v : values) { tail.next = new Node(v); tail = tail.next; }
        return dummy.next;
    }

    public static void main(String[] args) {
        System.out.println("середина [1..5]: " + middle(build(1, 2, 3, 4, 5)).value);
        // TODO: проверьте на чётной длине [1..6]
    }
}
