package m19_io_nio.practice;

/**
 * Задача 05 — Модуль 19: Обход каталога
 *
 * ЗАДАНИЕ:
 *   Выведите список файлов и папок в ТЕКУЩЕМ каталоге (".").
 *   Для каждого элемента укажите, файл это или каталог, и его имя.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   [DIR]  src
 *   [FILE] output.txt
 *   [FILE] Task05.java
 *
 * ПОДСКАЗКА:
 *   try (Stream<Path> entries = Files.list(Path.of("."))) {
 *       entries.forEach(p -> { ... Files.isDirectory(p) ... });
 *   }
 *   Закрывайте Stream через try-with-resources.
 */
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class Task05 {
    public static void main(String[] args) {
        Path currentDir = Path.of(".");
        try (Stream<Path> entries = Files.list(currentDir)) {
            entries.forEach(path -> {
                if (Files.isDirectory(path)) {
                    System.out.println("[DIR]  " + path.getFileName());
                } else {
                    System.out.println("[FILE] " + path.getFileName());
                }
            });
        } catch (IOException e) {
            System.err.println("Ошибка при чтении каталога: " + e.getMessage());
        }
    }
}