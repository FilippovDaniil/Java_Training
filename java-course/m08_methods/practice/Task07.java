package m08_methods.practice;

/**
 * Задача 07 — Модуль 08 (МИНИ-ПРОЕКТ): Библиотека математических методов
 *
 * ЗАДАНИЕ:
 *   Соберите небольшую «библиотеку» статических методов и проверьте
 *   их работу в main. Реализуйте:
 *     - boolean isPrime(int n)       — простое ли число;
 *     - int gcd(int a, int b)        — НОД двух чисел (алгоритм Евклида);
 *     - long power(int base, int exp) — возведение в степень (циклом);
 *     - int reverseNumber(int n)     — «перевернуть» цифры числа
 *                                       (123 → 321).
 *
 * ПРИМЕРЫ:
 *   isPrime(7)        → true
 *   isPrime(8)        → false
 *   gcd(48, 36)       → 12
 *   power(2, 10)      → 1024
 *   reverseNumber(123)→ 321
 *
 * ТРЕБОВАНИЯ:
 *   - каждый метод — отдельный static-метод с понятным именем;
 *   - main вызывает каждый метод и выводит результат.
 *
 * ПОДСКАЗКИ:
 *   - isPrime: число < 2 не простое; проверяйте делители от 2 до n/2.
 *   - gcd (Евклид): пока b != 0 { tmp=b; b=a%b; a=tmp; } вернуть a.
 *   - reverseNumber: result = result*10 + n%10; n /= 10.
 */
public class Task07 {
    public static void main(String[] args) {
        // Проверка метода isPrime
        System.out.println("isPrime(7) = " + isPrime(7));   // true
        System.out.println("isPrime(8) = " + isPrime(8));   // false
        System.out.println("isPrime(1) = " + isPrime(1));   // false
        System.out.println("isPrime(2) = " + isPrime(2));   // true

        // Проверка метода gcd
        System.out.println("gcd(48, 36) = " + gcd(48, 36)); // 12
        System.out.println("gcd(17, 13) = " + gcd(17, 13)); // 1

        // Проверка метода power
        System.out.println("power(2, 10) = " + power(2, 10)); // 1024
        System.out.println("power(5, 0) = " + power(5, 0));   // 1

        // Проверка метода reverseNumber
        System.out.println("reverseNumber(123) = " + reverseNumber(123));   // 321
        System.out.println("reverseNumber(1000) = " + reverseNumber(1000)); // 1 (или 0001 -> 1)
        System.out.println("reverseNumber(9) = " + reverseNumber(9));       // 9
    }

    /**
     * Проверяет, является ли число простым.
     * @param n натуральное число
     * @return true, если n простое, иначе false
     */
    public static boolean isPrime(int n) {
        if (n < 2) return false;
        // Проверяем делители до sqrt(n) (оптимизация: до n/2 тоже работает, но дольше)
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) return false;
        }
        return true;
    }

    /**
     * Вычисляет наибольший общий делитель (НОД) двух целых чисел по алгоритму Евклида.
     * @param a первое число
     * @param b второе число
     * @return НОД(a, b)
     */
    public static int gcd(int a, int b) {
        // Алгоритм Евклида: пока b != 0, заменяем a на b, b на a % b
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return Math.abs(a); // берём модуль для отрицательных чисел (хотя по заданию обычно положительные)
    }

    /**
     * Возводит основание в неотрицательную степень.
     * @param base основание
     * @param exp показатель степени (неотрицательный)
     * @return base^exp
     */
    public static long power(int base, int exp) {
        if (exp < 0) {
            throw new IllegalArgumentException("Степень должна быть неотрицательной");
        }
        long result = 1;
        for (int i = 0; i < exp; i++) {
            result *= base;
        }
        return result;
    }

    /**
     * Переворачивает цифры целого неотрицательного числа.
     * @param n неотрицательное число
     * @return число с обратным порядком цифр (например, 123 -> 321)
     */
    public static int reverseNumber(int n) {
        int reversed = 0;
        while (n != 0) {
            reversed = reversed * 10 + (n % 10);
            n /= 10;
        }
        return reversed;
    }
}
