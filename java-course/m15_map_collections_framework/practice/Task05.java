package m15_map_collections_framework.practice;

/**
 * Задача 05 — Модуль 15: HashMap vs TreeMap
 *
 * ЗАДАНИЕ:
 *   Заполните HashMap и TreeMap одинаковыми парами (буква → число),
 *   добавляя ключи в произвольном порядке (например: "в", "а", "б", "д", "г").
 *   Выведите оба словаря и сравните порядок ключей.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   HashMap: {а=..., в=..., д=..., ...}  (порядок не гарантирован)
 *   TreeMap: {а=..., б=..., в=..., г=..., д=...}  (ключи отсортированы)
 *
 * ПОДСКАЗКА:
 *   Map<String, Integer> tree = new TreeMap<>();
 *   TreeMap автоматически сортирует записи по ключу.
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Task05 {
    public static void main(String[] args) {
        String[] words = {"X","Y","Z","B","A","D","C"};
        int[] numbers = {1, 4, 3, 65, 2, -2, -5};
        Map<String, Integer> hashMap = new HashMap<>();
        Map<String, Integer> treeMap = new TreeMap<>();
        for (int i = 0; i < words.length; i++){
            hashMap.put(words[i],numbers[i]);
            treeMap.put(words[i],numbers[i]);
        }

        System.out.println(hashMap);
        System.out.println(treeMap);
    }
}
