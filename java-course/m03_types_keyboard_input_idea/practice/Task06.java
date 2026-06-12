package m03_types_keyboard_input_idea.practice;

/**
 * Задача 06 — Модуль 03: Площадь и периметр прямоугольника
 *
 * ЗАДАНИЕ:
 *   Считайте с клавиатуры ширину и высоту прямоугольника (дробные числа).
 *   Вычислите и выведите площадь и периметр.
 *
 * ПРИМЕР:
 *   Ввод:  2.5
 *          4.0
 *   Вывод: Площадь: 10.0
 *          Периметр: 13.0
 *
 * ПОДСКАЗКА:
 *   scanner.nextDouble(); площадь = ширина * высота;
 *   периметр = 2 * (ширина + высота).
 */
import java.util.Locale;
import java.util.Scanner;

public class Task06 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        scanner.useLocale(Locale.US);
        System.out.println("Enter height and width: ");
        double a = scanner.nextDouble();
        double b = scanner.nextDouble();
        double ploshad = a * b;
        double perimeter = 2 * (a+b);
        System.out.println("Ploshad: " + ploshad);
        System.out.println("Perimeter: " + perimeter);


        scanner.close();
    }
}
