package m18_io_streams.practice;

/**
 * Задача 03 — Модуль 18: Дозапись в файл (append)
 *
 * ЗАДАНИЕ:
 *   Реализуйте простой «журнал»: считывайте строки с клавиатуры и
 *   ДОПИСЫВАЙТЕ каждую в файл "log.txt" (не перезаписывая прежнее
 *   содержимое). Пустая строка завершает ввод.
 *
 * ПОДСКАЗКА:
 *   Режим дозаписи: new FileWriter("log.txt", true) — второй аргумент true.
 *   Запустите программу дважды — записи должны накапливаться.
 */
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Task03 {
    public static void main(String[] args) {
        // Создаём Scanner один раз
        Scanner scanner = new Scanner(System.in);

        // Используем try-with-resources для FileWriter, который будет закрыт автоматически
        try (FileWriter writer = new FileWriter("java-course/m18_io_streams/practice/output.txt", true)) {
            while (true) {
                System.out.println("Enter words: ");
                String line = scanner.nextLine();
                if (line.isEmpty()) {
                    break;
                }
                writer.write(line + System.lineSeparator()); // добавляем перевод строки
            }
        } catch (IOException e) {
            System.err.println("Error" + e.getMessage());
        } finally {
            scanner.close(); // закрываем Scanner
        }
    }
}
