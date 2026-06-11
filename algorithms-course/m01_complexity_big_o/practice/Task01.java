package m01_complexity_big_o.practice;

/**
 * Задача 01 — Тема 01: Определение сложности по коду
 *
 * ЗАДАНИЕ:
 *   Ниже 10 методов-фрагментов. Для КАЖДОГО определите асимптотическую
 *   сложность по времени в нотации O(...) и впишите её в TODO-комментарий
 *   над методом. Затем в complexity(i) верните строку-ответ для фрагмента i
 *   (например "O(n)"), чтобы main вывел вашу таблицу ответов.
 *
 * ПРИМЕР / ВЫВОД:
 *   Фрагмент 1: O(1)
 *   Фрагмент 2: O(n)
 *   ...
 *   Фрагмент 10: O(n^2)
 *
 * ТРЕБОВАНИЯ:
 *   - отбрасывайте константы и младшие слагаемые (3n+5 -> O(n));
 *   - n — это размер входа (длина массива, значение параметра);
 *   - ответы пишите в виде "O(1)", "O(log n)", "O(n)", "O(n log n)", "O(n^2)", "O(2^n)".
 *
 * ПОДСКАЗКА:
 *   Вложенные циклы перемножаются; деление пополам на каждом шаге — log n;
 *   два последовательных цикла — это сумма, берём максимум.
 */

public class Task01 {

    // TODO: сложность? ___
    static int f1(int[] a) { return a.length > 0 ? a[0] : -1; }

    // TODO: сложность? ___
    static int f2(int[] a) { int s = 0; for (int x : a) s += x; return s; }

    // TODO: сложность? ___
    static long f3(int[] a) {
        long s = 0;
        for (int i = 0; i < a.length; i++)
            for (int j = 0; j < a.length; j++) s += (long) a[i] * a[j];
        return s;
    }

    // TODO: сложность? ___
    static int f4(int n) { int c = 0; while (n > 1) { n /= 2; c++; } return c; }

    // TODO: сложность? ___
    static int f5(int[] a) {
        int c = 0;
        for (int i = 0; i < a.length; i++)
            for (int j = i + 1; j < a.length; j++) c++;
        return c;
    }

    // TODO: сложность? ___
    static int f6(int n) {
        int c = 0;
        for (int i = 1; i < n; i *= 2) c++;   // i: 1,2,4,8,...
        return c;
    }

    // TODO: сложность? ___
    static int f7(int[] a) {
        int c = 0;
        for (int i = 0; i < a.length; i++) c++;
        for (int j = 0; j < a.length; j++) c++;
        return c;
    }

    // TODO: сложность? ___
    static long f8(int n) { if (n <= 1) return 1; return f8(n - 1) + f8(n - 2); }

    // TODO: сложность? ___
    static int f9(int[] a) {
        int c = 0;
        for (int i = 0; i < a.length; i++)
            for (int j = 1; j < a.length; j *= 2) c++;
        return c;
    }

    // TODO: сложность? ___
    static int f10(int[][] m) {
        int c = 0;
        for (int[] row : m) for (int v : row) c += v;
        return c;   // m — это n×n матрица; n — сторона
    }

    /** Верните строку-ответ для фрагмента i (1..10). */
    static String complexity(int i) {
        // TODO: верните ваш ответ, например "O(n)"
        return "?";
    }

    public static void main(String[] args) {
        for (int i = 1; i <= 10; i++)
            System.out.println("Фрагмент " + i + ": " + complexity(i));
    }
}
