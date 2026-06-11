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

public class Task03 {
    public static void main(String[] args) {
        Path path = Path.of("temp-data.txt");
        // Ваш код здесь
    }
}
