package m18_io_streams.practice;

/**
 * Задача 01 — Модуль 18: Запись текста в файл
 *
 * ЗАДАНИЕ:
 *   Запишите в файл "output.txt" три строки текста, используя FileWriter
 *   и try-with-resources. После записи выведите "Файл записан".
 *
 * ОЖИДАЕМЫЙ РЕЗУЛЬТАТ:
 *   - создан файл output.txt с тремя строками;
 *   - в консоли: Файл записан
 *
 * ПОДСКАЗКА:
 *   try (FileWriter w = new FileWriter("output.txt")) {
 *       w.write("строка\n");
 *   } catch (IOException e) { ... }
 *   Не забудьте \n для перевода строки.
 */
import java.io.FileWriter;
import java.io.IOException;

public class Task01 {
    public static void main(String[] args) {
        // Ваш код здесь
        try (FileWriter w = new FileWriter("java-course/m18_io_streams/practice/output.txt")){
            w.write("Hello\n");
            w.write("I am\n");
            w.write("Danya\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
