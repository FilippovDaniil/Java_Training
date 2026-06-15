package m19_io_nio.practice;

/**
 * Задача 01 — Модуль 19: Объект Path
 *
 * ЗАДАНИЕ:
 *   Создайте Path для файла "docs/report.txt" и выведите:
 *     - имя файла (getFileName);
 *     - родительский каталог (getParent);
 *     - абсолютный путь (toAbsolutePath);
 *     - является ли путь абсолютным (isAbsolute).
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   Имя файла: report.txt
 *   Каталог: docs
 *   Абсолютный путь: C:\...\docs\report.txt
 *   Абсолютный? false
 *
 * ПОДСКАЗКА:
 *   Path p = Path.of("docs/report.txt");  // или Paths.get(...)
 */
import java.nio.file.Path;
import java.nio.file.Paths;

public class Task01 {
    public static void main(String[] args) {
        // Ваш код здесь
        Path path = Paths.get("java-course","m19_io_nio","practice","report.txt");
        System.out.println(path.getFileName());
        System.out.println(path.getParent());
        System.out.println(path.toAbsolutePath());
        System.out.println(path.isAbsolute());
    }
}
