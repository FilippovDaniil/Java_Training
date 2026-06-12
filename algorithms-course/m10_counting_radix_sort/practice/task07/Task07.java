package m10_counting_radix_sort.practice.task07;

/**
 * Задача 07 (мини-проект Data-Cruncher) — Тема 10: выбор линейной сортировки
 *
 * ЗАДАНИЕ:
 *   Добавьте в Data-Cruncher RangeSorter (файл RangeSorter.java): для целых
 *   данных с известным (узким) диапазоном применяйте counting sort за O(n+k),
 *   а если диапазон слишком широкий — сообщайте, что выгоднее обычная сортировка.
 *
 * ПРИМЕР / ВЫВОД:
 *   данные оценок 0..100, n=20: counting выгодна -> [отсортировано]
 *   данные с диапазоном 0..10^9, n=20: counting НЕ выгодна (k >> n)
 *
 * ТРЕБОВАНИЯ:
 *   - shouldUseCounting сравнивает диапазон k с n (с коэффициентом);
 *   - countingSort корректно сортирует и обрабатывает сдвиг;
 *   - продемонстрируйте оба сценария (узкий и широкий диапазон).
 *
 * ПОДСКАЗКА:
 *   Узкий диапазон — например возраст/оценки/категории; широкий — произвольные id.
 */

import java.util.Arrays;

public class Task07 {
    public static void main(String[] args) {
        RangeSorter sorter = new RangeSorter();
        int[] grades = {72, 55, 90, 100, 0, 63, 88, 88, 41, 72};   // диапазон 0..100
        System.out.println("counting выгодна? " + sorter.shouldUseCounting(grades.length, 0, 100, 4));
        System.out.println(Arrays.toString(sorter.countingSort(grades)));
        // TODO: продемонстрируйте сценарий с широким диапазоном (shouldUseCounting -> false)
    }
}
