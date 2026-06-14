package m15_map_collections_framework.practice;

/**
 * Задача 01 — Модуль 15: Первый HashMap
 *
 * ЗАДАНИЕ:
 *   Создайте Map<String, Integer> — справочник "город → население".
 *   Добавьте 3-4 города. Выведите население конкретного города
 *   и проверьте наличие ключа.
 *
 * ПРИМЕР:
 *   Население Москвы: 12000000
 *   Есть ли Казань: true
 *   Есть ли Лондон: false
 *
 * ПОДСКАЗКА:
 *   map.put("Москва", 12000000); map.get("Москва"); map.containsKey("...").
 */
import java.util.HashMap;
import java.util.Map;

public class Task01 {
    public static void main(String[] args) {
        // Ваш код здесь
        Map<String,Integer> spravochnik = new HashMap<>();
        spravochnik.put("Moscow",555);
        spravochnik.put("Moscow",777);
        spravochnik.put("London",888);
        spravochnik.put("Kazan",333);

        System.out.println(spravochnik.get("Moscow"));
        System.out.println("Vhodit li kazan: " + spravochnik.containsKey("Kazan"));
        System.out.println(spravochnik);
    }
}
