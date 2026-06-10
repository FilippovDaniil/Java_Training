/**
 * Задача 07 (мини-проект Data-Cruncher) — Тема 09: медиана и квантили
 *
 * ЗАДАНИЕ:
 *   Добавьте в Data-Cruncher QuantileFinder (файл QuantileFinder.java),
 *   который через Quick Select находит медиану и квантили набора данных
 *   за O(n) в среднем — без полной сортировки.
 *
 * ПРИМЕР / ВЫВОД:
 *   данные: [7, 2, 1, 6, 8, 5, 3, 4, 9, 0]
 *   медиана (k=5)        = 5
 *   нижний квартиль 0.25 = 2
 *   верхний квартиль 0.75= 7
 *
 * ТРЕБОВАНИЯ:
 *   - select не должен портить исходный массив (работайте с копией);
 *   - median и quantile выражаются через select;
 *   - сложность поиска одного квантиля — O(n) в среднем.
 *
 * ПОДСКАЗКА:
 *   Для проверки сравните результат select(k) с тем, что даёт полная сортировка.
 */

public class Task07 {
    public static void main(String[] args) {
        int[] data = {7, 2, 1, 6, 8, 5, 3, 4, 9, 0};
        QuantileFinder qf = new QuantileFinder();
        System.out.println("медиана        = " + qf.median(data));
        System.out.println("квантиль 0.25  = " + qf.quantile(data, 0.25));
        System.out.println("квантиль 0.75  = " + qf.quantile(data, 0.75));
    }
}
