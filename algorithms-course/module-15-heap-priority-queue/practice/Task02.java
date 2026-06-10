/**
 * Задача 02 — Тема 15: heapify из массива за O(n)
 *
 * ЗАДАНИЕ:
 *   Преобразуйте произвольный массив в min-heap НА МЕСТЕ за O(n), применяя
 *   siftDown ко всем «внутренним» узлам с конца к началу (от size/2-1 до 0).
 *   Это быстрее, чем вставлять элементы по одному (то было бы O(n log n)).
 *
 * ПРИМЕР / ВЫВОД:
 *   До:     [5, 3, 8, 1, 2, 7]
 *   heapify -> массив удовлетворяет свойству min-heap (a[0] = 1 — минимум)
 *   Проверка: для всех i a[i] <= a[2i+1] и a[i] <= a[2i+2] (если есть)
 *
 * ТРЕБОВАНИЯ:
 *   - heapify за O(n): siftDown от индекса size/2-1 до 0;
 *   - после heapify a[0] = минимум;
 *   - метод isMinHeap проверяет свойство кучи для всего массива.
 *
 * ПОДСКАЗКА:
 *   Последний внутренний узел — родитель последнего элемента: (size-2)/2 = size/2-1.
 */

import java.util.Arrays;

public class Task02 {

    static void heapify(int[] a) {
        // TODO: для i от a.length/2-1 до 0 — siftDown(a, i, a.length)
    }

    static void siftDown(int[] a, int i, int size) {
        // TODO: погрузить элемент i к меньшему ребёнку, пока нарушено свойство кучи
    }

    static boolean isMinHeap(int[] a) {
        for (int i = 0; i < a.length; i++) {
            int l = 2*i+1, r = 2*i+2;
            if (l < a.length && a[i] > a[l]) return false;
            if (r < a.length && a[i] > a[r]) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        int[] a = {5, 3, 8, 1, 2, 7};
        heapify(a);
        System.out.println(Arrays.toString(a));
        System.out.println("isMinHeap = " + isMinHeap(a) + ", min = " + a[0]);
    }
}
