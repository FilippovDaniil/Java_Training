/**
 * Задача 02 — Модуль 26: Исследование полей
 *
 * ЗАДАНИЕ:
 *   Используя рефлексию, выведите все поля класса Person:
 *   для каждого поля — его тип и имя.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   Поля класса Person:
 *   String name
 *   int age
 *   double salary
 *
 * ПОДСКАЗКА:
 *   for (Field f : Person.class.getDeclaredFields())
 *       f.getType().getSimpleName(); f.getName();
 */

import java.lang.reflect.Field;

public class Task02 {
    public static void main(String[] args) {
        // Переберите getDeclaredFields() класса Person
    }
}
