package m19_io_nio.practice;

/**
 * Задача 04 — Модуль 19: Копирование файла через Files
 *
 * ЗАДАНИЕ:
 *   1. Создайте файл "source.txt" с несколькими строками.
 *   2. Скопируйте его в "destination.txt" через Files.copy
 *      (с заменой, если файл уже существует).
 *   3. Прочитайте копию и выведите её содержимое.
 *
 * ПОДСКАЗКА:
 *   Files.copy(src, dest, StandardCopyOption.REPLACE_EXISTING);
 *   импорт: java.nio.file.StandardCopyOption
 */
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

public class Task04 {
    public static void main(String[] args) {

        try{
            Path path = Paths.get("java-course","m19_io_nio","practice","report2.txt");
            Path path2 = Paths.get("java-course","m19_io_nio","practice","report22.txt");
            Files.write(path, List.of("Milk", "Hleb", "Eggs"));
            Files.copy(path,path2, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
