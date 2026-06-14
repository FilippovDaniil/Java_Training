package m15_map_collections_framework.practice;

/**
 * Задача 02 — Модуль 15: Подсчёт частоты слов
 *
 * ЗАДАНИЕ:
 *   Дан массив слов. Посчитайте, сколько раз встречается каждое слово,
 *   используя Map<String, Integer>. Выведите все пары "слово: количество".
 *
 * ПРИМЕР (для {"кот","пёс","кот","кот","рыба","пёс"}):
 *   кот: 3
 *   пёс: 2
 *   рыба: 1
 *
 * ПОДСКАЗКА:
 *   map.put(word, map.getOrDefault(word, 0) + 1);
 */
import java.util.HashMap;
import java.util.Map;

public class Task02 {
    public static void main(String[] args) {
        String[] words = {"cat", "dog", "cat", "cat", "fish", "dog"};
        // Ваш код здесь

        Map<String,Integer> freq = new HashMap<>();
        for (String word : words){
            freq.put(word,freq.getOrDefault(word,0)+1);
        }

        System.out.println(freq);

    }
}
