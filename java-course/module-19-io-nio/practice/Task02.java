/**
 * Задача 02 — Модуль 19: Files.write и Files.readAllLines
 *
 * ЗАДАНИЕ:
 *   1. Запишите список из нескольких строк в файл "nio-notes.txt"
 *      ОДНИМ вызовом Files.write.
 *   2. Прочитайте файл обратно через Files.readAllLines и выведите
 *      строки с номерами.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   1: Молоко
 *   2: Хлеб
 *   3: Яйца
 *
 * ПОДСКАЗКА:
 *   Files.write(path, List.of("...", "..."));
 *   List<String> lines = Files.readAllLines(path);
 */
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Task02 {
    public static void main(String[] args) {
        Path path = Path.of("nio-notes.txt");
        // Ваш код здесь
    }
}
