package m03_types_keyboard_input_idea.practice;

/**
 * Задача 07 — Модуль 03 (МИНИ-ПРОЕКТ): Анкета пользователя
 *
 * ЗАДАНИЕ:
 *   Создайте интерактивную анкету. Считайте с клавиатуры:
 *     - имя (строка)
 *     - возраст (целое)
 *     - рост в метрах (дробное, например 1.75)
 *   Затем выведите красиво оформленную карточку и дополнительно:
 *     - год рождения (примерно): 2026 - возраст
 *     - рост в сантиметрах (рост * 100), приведённый к int
 *
 * ПРИМЕР:
 *   Ввод:  Иван / 30 / 1.8
 *   Вывод:
 *   ===== АНКЕТА =====
 *   Имя: Иван
 *   Возраст: 30
 *   Год рождения (~): 1996
 *   Рост: 1.8 м (180 см)
 *
 * ВНИМАНИЕ (ловушка):
 *   После nextInt()/nextDouble() перед nextLine() добавьте
 *   лишний scanner.nextLine(), иначе строка прочитается пустой.
 *
 * ПОДСКАЗКА:
 *   Порядок чтения продумайте сами; для см используйте (int)(рост*100).
 */
import java.util.Locale;
import java.util.Scanner;

public class Task07 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        scanner.useLocale(Locale.US);
        System.out.println("Enter your name:");
        String name = scanner.nextLine();
        System.out.println("Enter your age:");
        int age = scanner.nextInt();
        System.out.println("Enter your rost:");
        double rost = scanner.nextDouble();
        int god_of_birth = 2026 - age;
        double sm_rost = rost * 100;
        System.out.println("=====Anketa=====");
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Year of birth: " + god_of_birth);
        System.out.println("Rost: " + rost + "m (" + (int) sm_rost + " sm)");


        scanner.close();
    }
}
