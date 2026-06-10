/**
 * Задача 04 — Тема 05: Рекурсивный обход папок (listFiles)
 *
 * ЗАДАНИЕ:
 *   Рекурсивно обойдите дерево каталогов от заданного корня и выведите все
 *   файлы и папки с отступом по глубине вложенности. Это прямой пример
 *   рекурсии на «древовидной» структуре файловой системы.
 *
 * ПРИМЕР / ВЫВОД (для какого-то каталога):
 *   [D] practice
 *     [F] Task01.java
 *     [F] Task02.java
 *     [D] Task07
 *       [F] Task07.java
 *   Файлов: 3, папок: 2
 *
 * ТРЕБОВАНИЯ:
 *   - используйте java.io.File и File.listFiles();
 *   - папки помечайте [D], файлы [F]; отступ = 2 пробела на уровень;
 *   - посчитайте и выведите общее число файлов и папок;
 *   - корень берите из args[0], а если аргумента нет — текущий каталог ".".
 *
 * ПОДСКАЗКА:
 *   walk(File dir, int depth): listFiles() может вернуть null (нет прав / не папка) —
 *   проверяйте. Для каждого потомка: печать с отступом; если папка — рекурсия depth+1.
 */

import java.io.File;

public class Task04 {

    static int files = 0, dirs = 0;

    static void walk(File node, int depth) {
        // TODO: напечатать node с отступом depth; если папка — dirs++ и рекурсия по listFiles;
        //       если файл — files++
    }

    public static void main(String[] args) {
        File root = new File(args.length > 0 ? args[0] : ".");
        walk(root, 0);
        System.out.println("Файлов: " + files + ", папок: " + dirs);
    }
}
