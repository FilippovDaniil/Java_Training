package m05_recursion_iteration.practice;

/**
 * Задача 02 — Тема 05: Все подмножества массива (бэктрекинг)
 *
 * ЗАДАНИЕ:
 *   Сгенерируйте ВСЕ подмножества массива (включая пустое и полное) рекурсивным
 *   бэктрекингом. Для каждого элемента — два выбора: взять или не взять.
 *
 * ПРИМЕР / ВЫВОД (для [1,2,3] — 2^3 = 8 подмножеств):
 *   []
 *   [1]
 *   [1, 2]
 *   [1, 2, 3]
 *   [1, 3]
 *   [2]
 *   [2, 3]
 *   [3]
 *   (порядок может отличаться — важно, что ровно 8 уникальных)
 *
 * ТРЕБОВАНИЯ:
 *   - всего должно получиться 2^n подмножеств;
 *   - используйте рекурсию с «текущим» списком, который дополняете и откатываете;
 *   - результаты собирайте в List<List<Integer>>.
 *
 * ПОДСКАЗКА:
 *   backtrack(index, current): если index==n -> добавить копию current в результат;
 *   иначе: ветка «взять a[index]» (add -> рекурсия -> remove) и ветка «не взять» (рекурсия).
 */

import java.util.ArrayList;
import java.util.List;

public class Task02 {

    static List<List<Integer>> subsets(int[] a) {
        List<List<Integer>> result = new ArrayList<>();
        // TODO: запустить backtrack(0, new ArrayList<>(), a, result)
        return result;
    }

    // TODO: реализуйте вспомогательный рекурсивный метод backtrack(...)

    public static void main(String[] args) {
        List<List<Integer>> all = subsets(new int[]{1, 2, 3});
        System.out.println("кол-во подмножеств: " + all.size());
        all.forEach(System.out::println);
    }
}
