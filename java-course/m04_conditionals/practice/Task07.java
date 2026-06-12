package m04_conditionals.practice;

/**
 * Задача 07 — Модуль 04 (МИНИ-ПРОЕКТ): Калькулятор
 *
 * ЗАДАНИЕ:
 *   Реализуйте простой калькулятор. Считайте:
 *     - первое число (double)
 *     - знак операции (символ: + - * /)
 *     - второе число (double)
 *   С помощью switch по символу операции выполните вычисление
 *   и выведите результат.
 *
 * ОБРАБОТКА ОШИБОК:
 *   - при делении на ноль выведите "Деление на ноль невозможно"
 *   - при неизвестной операции выведите "Неизвестная операция"
 *
 * ПРИМЕРЫ:
 *   Ввод: 10 + 5  → Результат: 15.0
 *   Ввод: 8 / 0   → Деление на ноль невозможно
 *   Ввод: 3 ^ 2   → Неизвестная операция
 *
 * ПОДСКАЗКА:
 *   Знак операции прочитайте как строку (next()) и возьмите .charAt(0),
 *   либо считайте сразу символ. Перед делением проверьте второй операнд.
 */
import java.util.InputMismatchException;
import java.util.Scanner;

public class Task07 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        scanner.useLocale(java.util.Locale.US); // чтобы работала точка, а не запятая

        System.out.println("Enter extension, for example: 10 + 5");

        try {
            double a = scanner.nextDouble();   // первое число
            String op = scanner.next();        // оператор (+, -, *, /)
            double b = scanner.nextDouble();   // второе число

            double result = 0;
            boolean ok = true;

            switch (op) {
                case "+": result = a + b; break;
                case "-": result = a - b; break;
                case "*": result = a * b; break;
                case "/":
                    if (b != 0) result = a / b;
                    else {
                        System.out.println("Ошибка: деление на ноль");
                        ok = false;
                    }
                    break;
                default:
                    System.out.println("Неизвестный оператор: " + op);
                    ok = false;
            }

            if (ok) {
                System.out.println(a + " " + op + " " + b + " = " + result);
            }

        } catch (InputMismatchException e) {
            System.out.println("Ошибка: введите число, оператор и число через пробел (например, 10 + 5)");
        }

        scanner.close();
    }
}
