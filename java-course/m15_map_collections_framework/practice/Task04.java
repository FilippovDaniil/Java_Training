package m15_map_collections_framework.practice;

/**
 * Задача 04 — Модуль 15: Перебор entrySet
 *
 * ЗАДАНИЕ:
 *   Дан словарь "студент → оценка". Используя entrySet:
 *     - выведите все пары "имя: оценка";
 *     - посчитайте среднюю оценку;
 *     - найдите студента с наивысшей оценкой.
 *
 * ПРИМЕР:
 *   Анна: 5
 *   Борис: 3
 *   Виктор: 4
 *   Средняя оценка: 4.0
 *   Лучший студент: Анна
 *
 * ПОДСКАЗКА:
 *   for (Map.Entry<String, Integer> e : map.entrySet()) {
 *       e.getKey(); e.getValue();
 *   }
 */
import java.util.HashMap;
import java.util.Map;

public class Task04 {
    public static void main(String[] args) {
        Map<String, Integer> grades = new HashMap<>();
        grades.put("Анна", 5);
        grades.put("Борис", 3);
        grades.put("Виктор", 4);
        // Ваш код здесь
    }
}
