/**
 * Задача 07 (мини-проект Data-Cruncher) — Тема 11: поиск паттернов в тексте
 *
 * ЗАДАНИЕ:
 *   Добавьте в Data-Cruncher PatternSearcher (файл PatternSearcher.java):
 *   поиск всех вхождений паттерна в загруженный текст (КМП), подсчёт числа
 *   вхождений и список номеров строк, где паттерн встречается.
 *
 * ПРИМЕР / ВЫВОД:
 *   текст: "error: disk\nok\nerror: net\nok"
 *   паттерн "error"
 *   вхождений: 2
 *   строки: [1, 3]
 *
 * ТРЕБОВАНИЯ:
 *   - поиск за O(n + m) (КМП), не наивный;
 *   - count и linesContaining согласованы с findAll;
 *   - текст для проверки — многострочная строка (имитация файла лога).
 *
 * ПОДСКАЗКА:
 *   Это шаблон «grep»: ищем строки, содержащие паттерн.
 */

public class Task07 {
    public static void main(String[] args) {
        String log = "error: disk\nok\nerror: net\nok";
        PatternSearcher searcher = new PatternSearcher();
        System.out.println("вхождений: " + searcher.count(log, "error"));
        System.out.println("строки: " + searcher.linesContaining(log, "error"));
    }
}
