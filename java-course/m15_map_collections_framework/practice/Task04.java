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
        grades.put("Anna", 5);
        grades.put("Boris", 3);
        grades.put("Victor", 4);
        // Ваш код здесь
        int sum_of_grades = 0;
        int max_of_grades = 0;
        for (Map.Entry<String, Integer> entry : grades.entrySet()){
            if (entry.getValue() > max_of_grades){
                max_of_grades = entry.getValue();
            }
            sum_of_grades = sum_of_grades + entry.getValue();
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        Integer targetValue = max_of_grades;
        String foundKey = null;

        for (Map.Entry<String, Integer> entry : grades.entrySet()) {
            if (targetValue.equals(entry.getValue())) {
                foundKey = entry.getKey();
                break; // если нужно только первое совпадение
            }
        }

        System.out.println("Average grade: " + sum_of_grades/grades.size());
        System.out.println("Best student: " + foundKey);
    }
}
