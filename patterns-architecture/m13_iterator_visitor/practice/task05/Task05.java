package m13_iterator_visitor.practice.task05;

/**
 * Задача 05 — Тема 13: Visitor по дереву (Composite + Visitor)
 *
 * ЗАДАНИЕ:
 *   Обойдите файловое дерево визитором, который суммирует размер:
 *     - интерфейс FileVisitor (файл FileVisitor.java): void visitFile(FileItem f);
 *       void visitDir(DirItem d);
 *     - интерфейс FileNode (файл FileNode.java): void accept(FileVisitor v);
 *     - FileItem (файл FileItem.java): name, size; accept → v.visitFile(this);
 *     - DirItem (файл DirItem.java): name, List<FileNode> children, add(...);
 *       accept → v.visitDir(this);
 *     - SizeVisitor (файл SizeVisitor.java): копит общий размер; visitFile
 *       добавляет size; visitDir перебирает детей и вызывает child.accept(this).
 *   В main соберите дерево (каталог с файлами и вложенным каталогом), обойдите
 *   SizeVisitor, выведите суммарный размер.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   Суммарный размер: 40
 *
 * ТРЕБОВАНИЯ:
 *   - рекурсия по дереву реализована в визиторе (visitDir вызывает accept детей);
 *   - классы FileItem/DirItem не содержат логики подсчёта размера;
 *   - новая операция над деревом = новый визитор, без правок узлов.
 *
 * ПОДСКАЗКА:
 *   visitDir(d): для каждого child из d.children() — child.accept(this).
 *   Это естественный способ обходить Composite (тема 09) операцией-визитором.
 */

public class Task05 {
    public static void main(String[] args) {
        // TODO: соберите DirItem с FileItem и вложенным DirItem; обойдите SizeVisitor;
        //       выведите суммарный размер
    }
}
