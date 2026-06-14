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

        Map<String, Contact> telephoneBook = new HashMap<>();
        telephoneBook.put("Anna", new Contact("+7 964 794 55 74", "AnnaFil@mail.ru"));
        telephoneBook.put("Alex", new Contact("+7 977 487 25 35", "AlexFil@mail.ru"));
        System.out.println(telephoneBook.get("Anna"));
        for (Map.Entry<String, Contact> contacts: telephoneBook.entrySet()){
            System.out.println(contacts.getKey() + ": " + contacts.getValue().getPhone());
        }
        if (telephoneBook.get("Oleg") == null){
            System.out.println("No such contact");
        }
    }
}
