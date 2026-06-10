/**
 * Задача 01 — Тема 15: MinHeap (insert, extractMin)
 *
 * ЗАДАНИЕ:
 *   Реализуйте MinHeap (файл MinHeap.java) на массиве: insert (в конец + siftUp),
 *   extractMin (корень <-> последний + siftDown), peek. Все — O(log n)/O(1).
 *
 * ПРИМЕР / ВЫВОД:
 *   insert 5, 3, 8, 1, 2
 *   extractMin -> 1
 *   extractMin -> 2
 *   extractMin -> 3
 *   peek -> 5
 *
 * ТРЕБОВАНИЯ:
 *   - свойство min-heap: родитель <= детей;
 *   - insert/extractMin — O(log n); peek — O(1);
 *   - extractMin/peek на пустой куче — исключение;
 *   - НЕ использовать java.util.PriorityQueue — пишем свою.
 *
 * ПОДСКАЗКА:
 *   siftDown должен сравнивать ОБОИХ детей и опускаться к меньшему.
 */

public class Task01 {
    public static void main(String[] args) {
        MinHeap heap = new MinHeap();
        for (int v : new int[]{5, 3, 8, 1, 2}) heap.insert(v);
        System.out.println(heap.extractMin());   // 1
        System.out.println(heap.extractMin());   // 2
        System.out.println(heap.extractMin());   // 3
        System.out.println("peek = " + heap.peek());   // 5
    }
}
