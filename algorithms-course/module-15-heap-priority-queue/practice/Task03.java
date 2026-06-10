/**
 * Задача 03 — Тема 15: Heapsort
 *
 * ЗАДАНИЕ:
 *   Отсортируйте массив по возрастанию через heapsort:
 *     1) построить MAX-heap (heapify) за O(n);
 *     2) n раз: поменять корень (максимум) с последним элементом кучи,
 *        уменьшить размер кучи, siftDown нового корня.
 *   In-place, O(n log n), O(1) доп. памяти.
 *
 * ПРИМЕР / ВЫВОД:
 *   До:    [5, 3, 8, 1, 2, 7]
 *   После: [1, 2, 3, 5, 7, 8]
 *
 * ТРЕБОВАНИЯ:
 *   - именно MAX-heap (чтобы сортировать по возрастанию, складывая максимумы в конец);
 *   - in-place, без доп. массива;
 *   - siftDown работает в пределах текущего (уменьшающегося) размера кучи.
 *
 * ПОДСКАЗКА:
 *   max-heap: родитель >= детей. После heapify: for end=n-1..1: swap(0,end); siftDown(0, end).
 */

import java.util.Arrays;

public class Task03 {

    static void heapSort(int[] a) {
        // TODO: heapify в max-heap; затем извлекать максимум в конец, siftDown
    }

    static void siftDownMax(int[] a, int i, int size) {
        // TODO: погружение для MAX-heap (менять с БОЛЬШИМ ребёнком)
    }

    public static void main(String[] args) {
        int[] a = {5, 3, 8, 1, 2, 7};
        heapSort(a);
        System.out.println(Arrays.toString(a));
    }
}
