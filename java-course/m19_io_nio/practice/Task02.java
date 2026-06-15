package m19_io_nio.practice;

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
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Task02 {
    public static void main(String[] args) throws IOException {
        // Ваш код здесь
        Path path = Paths.get("java-course","m19_io_nio","practice","report.txt");
        Files.write(path, List.of("Milk", "Hleb", "Eggs"));
        List<String> lines = Files.readAllLines(path);
        ArrayList<String> newLines = new ArrayList<>(lines);
        for (int i = 0; i < lines.size(); i++){
            System.out.println((i+1) + ": " + newLines.get(i));
        }


    }
}
