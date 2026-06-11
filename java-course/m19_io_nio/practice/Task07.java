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
        // Ваш код здесь (меню в цикле)

        scanner.close();
    }
}
