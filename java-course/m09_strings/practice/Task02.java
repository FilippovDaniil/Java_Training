package m09_strings.practice;

/**
 * Задача 02 — Модуль 09: Разворот строки
 *
 * ЗАДАНИЕ:
 *   Считайте строку с клавиатуры и выведите её в обратном порядке.
 *   Решите ДВУМЯ способами (хотя бы один обязателен):
 *     а) с помощью StringBuilder.reverse();
 *     б) вручную циклом от конца к началу.
 *
 * ПРИМЕР:
 *   Ввод: Привет → тевирП
 *
 * ПОДСКАЗКА:
 *   new StringBuilder(s).reverse().toString()
 *   либо цикл с charAt от length()-1 до 0.
 */
import java.util.Scanner;

public class Task02 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the word: ");
        String s = scanner.nextLine();
        // Ваш код здесь
        StringBuilder stringBuilder = new StringBuilder(s);
        System.out.println(stringBuilder.reverse());

        for (int i = s.length()-1; i >= 0; i--){
            char c = s.charAt(i);
            System.out.print(c);
        }

        scanner.close();
    }
}
