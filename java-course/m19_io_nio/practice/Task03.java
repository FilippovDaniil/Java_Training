package m19_io_nio.practice;

/**
 * Задача 03 — Модуль 19: Управление файлом (exists, size, delete)
 *
 * ЗАДАНИЕ:
 *   Для файла "temp-data.txt":
 *     1) проверьте, существует ли он (Files.exists);
 *     2) если нет — создайте и запишите в него строку (Files.writeString);
 *     3) выведите его размер в байтах (Files.size);
 *     4) удалите файл (Files.deleteIfExists) и подтвердите удаление.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   Файл не существует, создаём...
 *   Размер: 24 байт
 *   Файл удалён: true
 *
 * ПОДСКАЗКА:
 *   Files.exists(p), Files.writeString(p, "..."), Files.size(p),
 *   Files.deleteIfExists(p).
 */
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Task03 {
    public static void main(String[] args) throws IOException{
        Path path = Paths.get("java-course","m19_io_nio","practice","report2.txt");
        // Ваш код здесь
        if (!Files.exists(path)){
            System.out.println("File does not exist, creating ...");
            Files.write(path, List.of("Milk", "Hleb", "Eggs"));
        }
        System.out.println("Size: " + Files.size(path));
        Files.delete(path);
        if (!Files.exists(path)){
            System.out.println("File deleted");
        }
    }
}
