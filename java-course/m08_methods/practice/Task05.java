package m08_methods.practice;

/**
 * Задача 05 — Модуль 08: Композиция методов
 *
 * ЗАДАНИЕ:
 *   Напишите три метода и используйте одни внутри других:
 *     - int square(int x)            — возвращает x в квадрате;
 *     - int sumOfSquares(int a, int b) — возвращает square(a)+square(b),
 *       ВЫЗЫВАЯ метод square;
 *     - double hypotenuse(int a, int b) — длина гипотенузы:
 *       корень из sumOfSquares(a, b) (используйте Math.sqrt).
 *   В main выведите результаты для a=3, b=4.
 *
 * ОЖИДАЕМЫЙ ВЫВОД:
 *   Квадрат 3: 9
 *   Сумма квадратов: 25
 *   Гипотенуза: 5.0
 *
 * ПОДСКАЗКА:
 *   Math.sqrt(double) возвращает квадратный корень.
 *   Метод может вызывать другой метод этого же класса.
 */
public class Task05 {
    public static void main(String[] args) {
        // Вызовите методы для a=3, b=4
        System.out.println(hypotenuse(3,4));
        System.out.println(square(3));
        System.out.println(sumOfSqueare(3,4));
    }

    // Объявите методы square, sumOfSquares, hypotenuse ниже

    private static int square(int x){
        return x * x;
    }

    private static int sumOfSqueare(int a, int b){
        return square(a) + square(b);
    }

    private static double hypotenuse(int a, int b){
        if ((a < 0) || (b < 0)) {
            throw new IllegalArgumentException("Нельзя извлечь корень из отрицательного числа: " + a + " and " + b);
        }
        return Math.sqrt(sumOfSqueare(a,b));
    }
}
