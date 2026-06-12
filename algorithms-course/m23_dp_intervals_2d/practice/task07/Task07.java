package m23_dp_intervals_2d.practice.task07;

/**
 * Задача 07 (мини-проект Data-Cruncher) — Тема 23: анализ последовательностей
 *
 * ЗАДАНИЕ:
 *   Добавьте в Data-Cruncher SequenceAnalyzer (файл SequenceAnalyzer.java):
 *     - longestIncreasing — длина наибольшего возрастающего тренда (LIS) в ряду;
 *     - longestPalindrome — самая длинная палиндромная подстрока в тексте.
 *
 * ПРИМЕР / ВЫВОД:
 *   ряд [3,1,4,1,5,9,2,6] -> LIS = 4   (например 1,4,5,9 или 1,4,5,6)
 *   текст "forgeeksskeegfor" -> палиндром "geeksskeeg"
 *
 * ТРЕБОВАНИЯ:
 *   - longestIncreasing — O(n log n);
 *   - longestPalindrome — DP O(n²);
 *   - переиспользуйте реализации из задач 01 и 03.
 *
 * ПОДСКАЗКА:
 *   LIS — мера «восходящего тренда» в данных; палиндромы — симметрии в тексте.
 */

public class Task07 {
    public static void main(String[] args) {
        SequenceAnalyzer a = new SequenceAnalyzer();
        System.out.println("LIS = " + a.longestIncreasing(new int[]{3, 1, 4, 1, 5, 9, 2, 6}));
        System.out.println("палиндром = " + a.longestPalindrome("forgeeksskeegfor"));
    }
}
