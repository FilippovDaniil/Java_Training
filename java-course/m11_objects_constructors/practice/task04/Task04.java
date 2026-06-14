package m11_objects_constructors.practice.task04;

/**
 * Задача 04 — Модуль 11: Переопределение toString()
 *
 * ЗАДАНИЕ:
 *   У класса Movie (title, director, year) переопределите toString()
 *   так, чтобы печать объекта давала читаемую строку.
 *   В main создайте фильм и выведите его через System.out.println(movie).
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   Фильм "Inception" (реж. Nolan, 2010)
 *
 * ПОДСКАЗКА:
 *   @Override public String toString() { return "..."; }
 *   println автоматически вызывает toString().
 */

public class Task04 {
    public static void main(String[] args) {
        // Создайте Movie и выведите его
        Movie movie = new Movie("Patrick","Bateman",1488);
        System.out.println(movie);
    }
}
