package m15_map_collections_framework.practice.task06;

/**
 * Задача 06 — Модуль 15: Словарь объектов (телефонная книга)
 *
 * ЗАДАНИЕ:
 *   Создайте Map<String, Contact>, где ключ — имя, значение — объект
 *   Contact (phone, email). Добавьте несколько контактов, затем:
 *     - выведите контакт по имени;
 *     - выведите все контакты (перебором);
 *     - обработайте случай, когда контакт не найден.
 *
 * ПРИМЕР:
 *   Контакт Анна: тел +7-900-111, email anna@mail.ru
 *   Контакт Олег: не найден
 *
 * ПОДСКАЗКА:
 *   Значением Map может быть любой объект, не только число/строка.
 */

import java.util.HashMap;
import java.util.Map;

public class Task06 {
    public static void main(String[] args) {
        Map<String, Contact> phoneBook = new HashMap<>();
        // Добавьте контакты и реализуйте поиск
    }
}
