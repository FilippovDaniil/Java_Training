package m15_heap_priority_queue.practice;

/**
 * Задача 05 — Тема 15: k наибольших элементов через кучу
 *
 * ЗАДАНИЕ:
 *   Найдите k наибольших элементов массива за O(n log k), поддерживая
 *   MIN-heap размера k. Идея: идём по массиву; если куча меньше k — добавляем;
 *   иначе, если текущий элемент больше минимума кучи — заменяем минимум.
 *   В конце в куче — ровно k наибольших.
 *
 * ПРИМЕР / ВЫВОД:
 *   a = [3, 1, 8, 5, 9, 2, 7], k=3
 *   три наибольших: [7, 8, 9] (в каком-то порядке)
 *
 * ТРЕБОВАНИЯ:
 *   - сложность O(n log k), память O(k) (не сортировать весь массив!);
 *   - использовать MIN-heap размера k (можно java.util.PriorityQueue);
 *   - вернуть k наибольших (порядок не важен).
 *
 * ПОДСКАЗКА:
 *   Min-heap хранит «k лучших на данный момент»; его корень — наименьший из них,
 *   и именно его вытесняет более крупный новый элемент.
 */

import java.util.PriorityQueue;

public class Task05 {

    static int[] kLargest(int[] a, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();   // min-heap
        // TODO: поддерживать кучу размера k; вернуть её содержимое массивом
        return new int[0];
    }

    public static void main(String[] args) {
        int[] a = {3, 1, 8, 5, 9, 2, 7};
        int[] top = kLargest(a, 3);
        java.util.Arrays.sort(top);
        System.out.println(java.util.Arrays.toString(top));   // [7, 8, 9]
    }
}
