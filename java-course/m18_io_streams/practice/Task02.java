package m18_io_streams.practice;

/**
 * Задача 02 — Модуль 18: Чтение файла построчно
 *
 * ЗАДАНИЕ:
 *   Прочитайте файл "output.txt" (созданный в Task01) построчно с
 *   помощью BufferedReader и выведите каждую строку с её номером.
 *   Если файл не найден — выведите понятное сообщение.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   1: Первая строка
 *   2: Вторая строка
 *   3: Третья строка
 *
 * ПОДСКАЗКА:
 *   try (BufferedReader r = new BufferedReader(new FileReader("output.txt"))) {
 *       String line;
 *       while ((line = r.readLine()) != null) { ... }
 *   }
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Task02 {
    public static void main(String[] args) {
        // Ваш код здесь
    }
}
