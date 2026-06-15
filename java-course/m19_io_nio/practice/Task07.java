package m19_io_nio.practice;

/**
 * Задача 07 — Модуль 19 (МИНИ-ПРОЕКТ): Файловый менеджер
 *
 * ЗАДАНИЕ:
 *   Реализуйте мини файловый менеджер для текущего каталога ("."),
 *   используя java.nio.file. Меню в цикле:
 *     1 — показать содержимое каталога ([DIR]/[FILE] + имя + размер);
 *     2 — создать текстовый файл (запросить имя и одну строку текста);
 *     3 — прочитать файл по имени (вывести содержимое или
 *         "Файл не найден");
 *     4 — удалить файл по имени (deleteIfExists, сообщить результат);
 *     5 — поиск: вывести все файлы, имя которых содержит подстроку;
 *     0 — выход.
 *
 * ПОДСКАЗКИ:
 *   - содержимое каталога: Files.list (в try-with-resources);
 *   - создание/запись: Files.writeString(Path.of(name), text);
 *   - чтение: Files.exists + Files.readAllLines или Files.readString;
 *   - размер: Files.size(path);
 *   - поиск: при обходе проверяйте p.getFileName().toString().contains(q);
 *   - помните о «ловушке» nextInt()/nextLine() (см. модуль 03).
 */
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;
import java.util.stream.Stream;

public class Task07 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Path currentDir = Path.of(".");

        while (true) {
            System.out.println("\n=== Файловый менеджер ===");
            System.out.println("1 — показать содержимое каталога");
            System.out.println("2 — создать текстовый файл");
            System.out.println("3 — прочитать файл");
            System.out.println("4 — удалить файл");
            System.out.println("5 — поиск файлов по имени");
            System.out.println("0 — выход");
            System.out.print("Ваш выбор: ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите число от 0 до 5.");
                continue;
            }

            switch (choice) {
                case 1:
                    listDirectory(currentDir);
                    break;
                case 2:
                    createTextFile(scanner, currentDir);
                    break;
                case 3:
                    readFile(scanner, currentDir);
                    break;
                case 4:
                    deleteFile(scanner, currentDir);
                    break;
                case 5:
                    searchFiles(scanner, currentDir);
                    break;
                case 0:
                    System.out.println("До свидания!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }
    }

    private static void listDirectory(Path dir) {
        System.out.println("\nСодержимое каталога " + dir.toAbsolutePath().normalize() + ":");
        try (Stream<Path> entries = Files.list(dir)) {
            entries.forEach(path -> {
                String type = Files.isDirectory(path) ? "[DIR]" : "[FILE]";
                long size;
                try {
                    size = Files.size(path);
                } catch (IOException e) {
                    size = -1;
                }
                System.out.printf("%s %s (%,d байт)%n", type, path.getFileName(), size);
            });
        } catch (IOException e) {
            System.err.println("Ошибка чтения каталога: " + e.getMessage());
        }
    }

    private static void createTextFile(Scanner scanner, Path currentDir) {
        System.out.print("Введите имя файла: ");
        String fileName = scanner.nextLine().trim();
        if (fileName.isEmpty()) {
            System.out.println("Имя файла не может быть пустым.");
            return;
        }
        System.out.print("Введите одну строку текста для записи: ");
        String content = scanner.nextLine();
        Path filePath = currentDir.resolve(fileName);
        try {
            Files.writeString(filePath, content);
            System.out.println("Файл '" + fileName + "' успешно создан.");
        } catch (IOException e) {
            System.err.println("Ошибка при создании файла: " + e.getMessage());
        }
    }

    private static void readFile(Scanner scanner, Path currentDir) {
        System.out.print("Введите имя файла для чтения: ");
        String fileName = scanner.nextLine().trim();
        Path filePath = currentDir.resolve(fileName);
        if (!Files.exists(filePath)) {
            System.out.println("Файл не найден.");
            return;
        }
        if (Files.isDirectory(filePath)) {
            System.out.println("Это каталог, а не файл.");
            return;
        }
        try {
            String content = Files.readString(filePath);
            System.out.println("Содержимое файла '" + fileName + "':");
            System.out.println(content);
        } catch (IOException e) {
            System.err.println("Ошибка при чтении файла: " + e.getMessage());
        }
    }

    private static void deleteFile(Scanner scanner, Path currentDir) {
        System.out.print("Введите имя файла для удаления: ");
        String fileName = scanner.nextLine().trim();
        Path filePath = currentDir.resolve(fileName);
        try {
            if (Files.deleteIfExists(filePath)) {
                System.out.println("Файл '" + fileName + "' удалён.");
            } else {
                System.out.println("Файл не найден.");
            }
        } catch (IOException e) {
            System.err.println("Ошибка при удалении: " + e.getMessage());
        }
    }

    private static void searchFiles(Scanner scanner, Path currentDir) {
        System.out.print("Введите подстроку для поиска: ");
        String query = scanner.nextLine().trim().toLowerCase();
        if (query.isEmpty()) {
            System.out.println("Подстрока не может быть пустой.");
            return;
        }
        System.out.println("Файлы, содержащие '" + query + "' в имени:");
        try (Stream<Path> entries = Files.list(currentDir)) {
            boolean found = false;
            for (Path path : (Iterable<Path>) entries::iterator) {
                String fileName = path.getFileName().toString().toLowerCase();
                if (fileName.contains(query)) {
                    System.out.println("  " + path.getFileName());
                    found = true;
                }
            }
            if (!found) {
                System.out.println("Совпадений не найдено.");
            }
        } catch (IOException e) {
            System.err.println("Ошибка при поиске: " + e.getMessage());
        }
    }
}
