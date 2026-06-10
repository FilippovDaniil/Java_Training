/**
 * Задача 06 — Тема 06: sqrt(x) с точностью 1e-6 через бинарный поиск
 *
 * ЗАДАНИЕ:
 *   Вычислите квадратный корень из неотрицательного x с точностью EPS=1e-6,
 *   НЕ используя Math.sqrt. Применяйте бинарный поиск по ответу: ищем r в
 *   диапазоне [0, max(1, x)], где r*r ≈ x.
 *
 * ПРИМЕР / ВЫВОД:
 *   sqrt(2)   ≈ 1.414214
 *   sqrt(9)   ≈ 3.000000
 *   sqrt(0.25)≈ 0.500000
 *
 * ТРЕБОВАНИЯ:
 *   - не использовать Math.sqrt / Math.pow;
 *   - сужать [lo, hi], пока hi - lo > EPS;
 *   - учесть x < 1 (для них корень БОЛЬШЕ x, верхняя граница должна быть >= 1).
 *
 * ПОДСКАЗКА:
 *   hi = Math.max(1, x); while (hi - lo > EPS) { mid=(lo+hi)/2; if (mid*mid < x) lo=mid; else hi=mid; }
 */

public class Task06 {

    static final double EPS = 1e-6;

    static double sqrt(double x) {
        // TODO: бинарный поиск по вещественному диапазону
        return 0;
    }

    public static void main(String[] args) {
        System.out.printf("sqrt(2)    ≈ %.6f%n", sqrt(2));
        System.out.printf("sqrt(9)    ≈ %.6f%n", sqrt(9));
        System.out.printf("sqrt(0.25) ≈ %.6f%n", sqrt(0.25));
    }
}
