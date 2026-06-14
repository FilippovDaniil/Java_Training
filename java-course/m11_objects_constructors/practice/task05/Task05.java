package m11_objects_constructors.practice.task05;

/**
 * Задача 05 — Модуль 11: equals() и hashCode()
 *
 * ЗАДАНИЕ:
 *   Класс Book (title, year). Переопределите equals() и hashCode()
 *   так, чтобы две книги считались равными при совпадении title и year.
 *   В main создайте три книги (две одинаковые по содержимому, одну
 *   другую) и сравните их через equals(). Сравните также через ==.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   b1.equals(b2): true
 *   b1 == b2: false
 *   b1.equals(b3): false
 *   hashCode b1 == hashCode b2: true
 *
 * ПОДСКАЗКА:
 *   Используйте java.util.Objects: Objects.hash(title, year)
 *   и title.equals(other.title). == сравнивает ссылки.
 */

import java.util.Objects;

public class Task05 {
    public static void main(String[] args) {
        // Создайте книги и сравните их
        Book book1 = new Book("Bob",1337);
        Book book2 = new Book("Bob",1337);
        Book book3 = new Book("Bob",1555);

        System.out.println(book1==book2);//false
        System.out.println(book1.equals(book2));//true
        System.out.println(book1==book3);//false
        System.out.println(book1.equals(book3));//false

    }
}
