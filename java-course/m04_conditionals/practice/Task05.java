package m04_conditionals.practice;

/**
 * Задача 05 — Модуль 04: День недели (switch)
 *
 * ЗАДАНИЕ:
 *   Считайте число от 1 до 7 и с помощью switch выведите название
 *   дня недели (1 — Понедельник, ..., 7 — Воскресенье).
 *   Для неверного числа выведите "Нет такого дня".
 *
 * ПРИМЕРЫ:
 *   Ввод: 1 → Понедельник
 *   Ввод: 6 → Суббота
 *   Ввод: 9 → Нет такого дня
 *
 * ПОДСКАЗКА:
 *   Не забудьте break в каждом case (или используйте стрелочный switch).
 */
import java.util.Scanner;

public class Task05 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter number: ");
        int day = scanner.nextInt();
        switch (day){
            case 1 -> System.out.println("Monday");
            case 2 -> System.out.println("Tuesday");
            case 3 -> System.out.println("Wensday");
            case 4 -> System.out.println("Thuesday");
            case 5 -> System.out.println("Friday");
            case 6 -> System.out.println("Saturday");
            case 7 -> System.out.println("Sunday");
            default -> System.out.println("No day");
        }

        scanner.close();
    }
}
