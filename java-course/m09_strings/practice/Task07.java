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
        scanner.close();

        // 1. Количество символов (с пробелами)
        int charsWithSpaces = text.length();

        // 2. Количество символов без пробелов (все пробельные символы? по заданию просто пробелы)
        int charsWithoutSpaces = text.replace(" ", "").length();

        // 3. Количество слов (разбиваем по одному и более пробелам, игнорируем пустые строки)
        String[] words = text.trim().split("\\s+");
        int wordCount = (text.trim().isEmpty()) ? 0 : words.length;

        // 4. Количество предложений (по символам . ! ?)
        int sentenceCount = 0;
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (c == '.' || c == '!' || c == '?') {
                sentenceCount++;
            }
        }

        // 5. Title Case: каждое слово с заглавной буквы, остальные строчные
        StringBuilder titleCaseBuilder = new StringBuilder();
        for (String word : words) {
            if (word.isEmpty()) continue;
            // Первая буква — заглавная, остальная часть — строчная
            char first = Character.toUpperCase(word.charAt(0));
            String rest = word.substring(1).toLowerCase();
            titleCaseBuilder.append(first).append(rest).append(" ");
        }
        // Удаляем лишний пробел в конце
        String titleCase = titleCaseBuilder.toString().trim();

        // Вывод результатов
        System.out.println("Символов (с пробелами): " + charsWithSpaces);
        System.out.println("Символов (без пробелов): " + charsWithoutSpaces);
        System.out.println("Слов: " + wordCount);
        System.out.println("Предложений: " + sentenceCount);
        System.out.println("Title Case: " + titleCase);
    }
}