package m18_io_streams.practice;

/**
 * Задача 06 — Модуль 18: Статистика файла
 *
 * ЗАДАНИЕ:
 *   Прочитайте текстовый файл "output.txt" и посчитайте:
 *     - количество строк;
 *     - количество слов;
 *     - количество символов (без учёта переводов строк).
 *   Выведите статистику.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   Строк: 3
 *   Слов: 6
 *   Символов: 36
 *
 * ПОДСКАЗКА:
 *   Читайте построчно; для слов используйте line.trim().split("\\s+"),
 *   для символов — line.length() (суммируйте по всем строкам).
 *   Учтите, что пустая строка не содержит слов.
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Task06 {
    public static void main(String[] args) {
        int lines = 0;
        int words = 0;
        int chars = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader("java-course/m18_io_streams/practice/output.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines++;
                chars += line.length();               // символы без учёта перевода строки

                if (!line.trim().isEmpty()) {         // пустая строка не даёт слов
                    String[] wordArray = line.trim().split("\\s+");
                    words += wordArray.length;
                }
            }
        } catch (IOException e) {
            System.err.println("Ошибка при чтении файла: " + e.getMessage());
            return;
        }

        System.out.println("Strok: " + lines);
        System.out.println("Slov: " + words);
        System.out.println("Simvolov: " + chars);
    }
}
