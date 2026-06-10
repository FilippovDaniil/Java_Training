/**
 * Задача 07 (мини-проект Data-Cruncher) — Тема 05: генератор комбинаций
 *
 * ЗАДАНИЕ:
 *   Добавьте в Data-Cruncher CombinationGenerator (файл CombinationGenerator.java),
 *   который перебирает все сочетания параметров анализа. Например, из набора
 *   режимов {"sort", "search", "dedup", "stats"} выбрать все пары (k=2) —
 *   это варианты конвейера обработки данных.
 *
 * ПРИМЕР / ВЫВОД (n=4 параметра, k=2 -> C(4,2)=6 комбинаций):
 *   [sort, search]
 *   [sort, dedup]
 *   [sort, stats]
 *   [search, dedup]
 *   [search, stats]
 *   [dedup, stats]
 *   Всего: 6
 *
 * ТРЕБОВАНИЯ:
 *   - используйте рекурсивный бэктрекинг;
 *   - число комбинаций = C(n, k);
 *   - метод обобщённый (работает со списком любых элементов).
 *
 * ПОДСКАЗКА:
 *   Чтобы не было перестановок-дубликатов, на каждом уровне начинайте перебор
 *   с индекса start = предыдущий + 1.
 */

import java.util.List;

public class Task07 {
    public static void main(String[] args) {
        List<String> params = List.of("sort", "search", "dedup", "stats");
        CombinationGenerator gen = new CombinationGenerator();
        List<List<String>> combos = gen.combinations(params, 2);
        combos.forEach(System.out::println);
        System.out.println("Всего: " + combos.size());
    }
}
