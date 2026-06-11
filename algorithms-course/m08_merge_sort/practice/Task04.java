package m08_merge_sort.practice;

/**
 * Задача 04 — Тема 08: Сортировка слиянием для связного списка
 *
 * ЗАДАНИЕ:
 *   Отсортируйте односвязный список merge sort'ом за O(n log n), ПЕРЕСТАВЛЯЯ
 *   ССЫЛКИ (без копирования значений в массив). Для списков это естественно:
 *   доп. память на элементы не нужна.
 *
 * ПРИМЕР / ВЫВОД:
 *   До:    4 -> 2 -> 1 -> 3 -> null
 *   После: 1 -> 2 -> 3 -> 4 -> null
 *
 * ТРЕБОВАНИЯ:
 *   - разбиение пополам через «черепаху и зайца» (тема 03);
 *   - merge двух отсортированных списков перевязкой next;
 *   - не создавать массивов; работать со ссылками.
 *
 * ПОДСКАЗКА:
 *   sort(head): если head==null||head.next==null -> head;
 *   найти середину, разорвать на две половины, sort каждую, merge.
 */

public class Task04 {

    static class Node {
        int value; Node next;
        Node(int value) { this.value = value; }
    }

    static Node sort(Node head) {
        // TODO: базовый случай; split пополам; рекурсия; merge
        return head;
    }

    static Node merge(Node a, Node b) {
        // TODO: слить два отсортированных списка перевязкой ссылок
        return a;
    }

    static Node build(int... values) {
        Node dummy = new Node(0), tail = dummy;
        for (int v : values) { tail.next = new Node(v); tail = tail.next; }
        return dummy.next;
    }

    static void print(Node head) {
        StringBuilder sb = new StringBuilder();
        for (Node c = head; c != null; c = c.next) sb.append(c.value).append(" -> ");
        System.out.println(sb.append("null"));
    }

    public static void main(String[] args) {
        Node head = build(4, 2, 1, 3);
        System.out.print("До:    "); print(head);
        System.out.print("После: "); print(sort(head));
    }
}
