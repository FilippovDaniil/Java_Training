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
        // Вызовите все методы библиотеки и выведите результаты
    }

    // Объявите методы isPrime, gcd, power, reverseNumber ниже
}
