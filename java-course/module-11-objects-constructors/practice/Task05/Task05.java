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
    }
}
