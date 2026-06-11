package m03_linked_lists.practice;

/**
 * Задача 05 — Тема 03: Обнаружить и убрать цикл (алгоритм Флойда)
 *
 * ЗАДАНИЕ:
 *   1) hasCycle(Node head): boolean — определить, есть ли в списке цикл
 *      (черепаха и заяц: если fast догонит slow — цикл есть).
 *   2) removeCycle(Node head) — если цикл есть, разорвать его (найти начало
 *      цикла и обнулить next у последнего узла, ведущего в начало цикла).
 *
 * ПРИМЕР / ВЫВОД:
 *   Список 1->2->3->4->5, где 5.next ведёт обратно на 3 (цикл).
 *   hasCycle: true
 *   после removeCycle: 1->2->3->4->5->null, hasCycle: false
 *
 * ТРЕБОВАНИЯ:
 *   - детекция за O(n) время, O(1) память;
 *   - поиск начала цикла: после встречи переместить один указатель в head,
 *     двигать оба по 1 шагу — встретятся в начале цикла;
 *   - removeCycle делает список снова линейным.
 *
 * ПОДСКАЗКА:
 *   Точка встречи slow==fast гарантирована при наличии цикла.
 *   Затем: p1=head, p2=meeting; идут по 1 шагу до p1==p2 — это начало цикла.
 */

public class Task05 {

    static class Node {
        int value; Node next;
        Node(int value) { this.value = value; }
    }

    static boolean hasCycle(Node head) {
        // TODO: slow/fast, вернуть true если встретились
        return false;
    }

    static void removeCycle(Node head) {
        // TODO: найти точку встречи; найти начало цикла; разорвать (last.next = null)
    }

    public static void main(String[] args) {
        // строим 1..5 и замыкаем 5 -> 3
        Node[] n = new Node[6];
        for (int i = 1; i <= 5; i++) n[i] = new Node(i);
        for (int i = 1; i <= 4; i++) n[i].next = n[i + 1];
        n[5].next = n[3];                 // цикл!
        System.out.println("hasCycle: " + hasCycle(n[1]));
        removeCycle(n[1]);
        System.out.println("после removeCycle, hasCycle: " + hasCycle(n[1]));
    }
}
