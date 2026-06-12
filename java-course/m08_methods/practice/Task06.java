package m08_methods.practice;

/**
 * Задача 06 — Модуль 08: Рекурсия
 *
 * ЗАДАНИЕ:
 *   Реализуйте РЕКУРСИВНО (без циклов) два метода:
 *     - long factorial(int n)  — факториал n! (базовый случай n<=1 → 1);
 *     - int sumDigits(int n)    — сумма цифр числа
 *       (базовый случай n<10 → n; шаг: n%10 + sumDigits(n/10)).
 *   Выведите результаты в main.
 *
 * ПРИМЕРЫ:
 *   factorial(5) → 120
 *   sumDigits(12345) → 15
 *
 * ПОДСКАЗКА:
 *   Каждый рекурсивный метод ОБЯЗАН иметь базовый случай —
 *   условие, при котором он перестаёт вызывать сам себя.
 */
public class Task06 {
    public static void main(String[] args) {
        // Вызовите factorial и sumDigits
        System.out.println(factorial(5));
        System.out.println(sumDigits(12345));
    }

    // Объявите рекурсивные методы factorial и sumDigits ниже

    private static long factorial(int n) {
        if (n <= 1) return 1;            // базовый случай
        return n * factorial(n - 1);     // рекурсивный шаг
    }

    public static int sumDigits(int n) {
        if (n < 10) {
            return n;                 // базовый случай
        }
        return (n % 10) + sumDigits(n / 10); // рекурсивный шаг
    }
}
