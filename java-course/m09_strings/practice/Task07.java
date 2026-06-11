package m09_strings.practice;

/**
 * Задача 07 — Модуль 09 (МИНИ-ПРОЕКТ): Анализатор текста
 *
 * ЗАДАНИЕ:
 *   Считайте строку текста (целое предложение) и выведите статистику:
 *     - количество символов (с пробелами и без);
 *     - количество слов;
 *     - количество предложений (по точкам, '!' и '?');
 *     - текст, где каждое слово начинается с заглавной буквы
 *       (Title Case).
 *
 * ПРИМЕР:
 *   Ввод: "java это круто. учим core!"
 *   Вывод:
 *     Символов (с пробелами): 26
 *     Символов (без пробелов): 22
 *     Слов: 5
 *     Предложений: 2
 *     Title Case: Java Это Круто. Учим Core!
 *
 * ПОДСКАЗКИ:
 *   - слова: text.trim().split("\\s+").length
 *   - символы без пробелов: text.replace(" ", "").length()
 *   - предложения: считайте символы . ! ? в цикле
 *   - Title Case: разбейте по пробелам, у каждого слова сделайте
 *     первую букву заглавной (Character.toUpperCase + substring(1)),
 *     собирайте через StringBuilder.
 */
import java.util.Scanner;

public class Task07 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String text = scanner.nextLine();
        // Ваш код здесь

        scanner.close();
    }
}
