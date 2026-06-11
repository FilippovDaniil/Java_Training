package m10_counting_radix_sort.practice;

/**
 * Задача 06 — Тема 10: Стабильность и память — наглядная демонстрация
 *
 * ЗАДАНИЕ:
 *   Покажите, ЧТО ТАКОЕ стабильность сортировки на практике. Дан массив пар
 *   (ключ, метка-порядка-поступления). Отсортируйте по ключу:
 *     - СТАБИЛЬНЫМ counting sort — равные ключи сохраняют исходный порядок меток;
 *     - НЕстабильным способом (например простой selection с обменами) —
 *       порядок равных ключей может нарушиться.
 *   Выведите обе раскладки и поясните разницу.
 *
 * ПРИМЕР / ВЫВОД:
 *   Вход (ключ:метка): 2:a 1:b 2:c 1:d
 *   Стабильно по ключу: 1:b 1:d 2:a 2:c   (b раньше d, a раньше c — как во входе)
 *   Нестабильно:        1:d 1:b 2:c 2:a   (порядок равных ключей мог измениться)
 *
 * ТРЕБОВАНИЯ:
 *   - представьте пару как int[]{key, label} или маленький класс;
 *   - стабильная версия — counting/insertion; нестабильная — selection sort;
 *   - в комментарии оцените доп. ПАМЯТЬ counting sort: O(n + k).
 *
 * ПОДСКАЗКА:
 *   Метку удобно сделать символом ('a','b',...) — наглядно видно сохранение порядка.
 */

public class Task06 {

    record Pair(int key, char label) {}

    static Pair[] stableSort(Pair[] a) {
        // TODO: стабильная сортировка по key (counting или insertion)
        return a.clone();
    }

    static Pair[] unstableSort(Pair[] a) {
        // TODO: нестабильная сортировка по key (selection с обменами)
        return a.clone();
    }

    static String show(Pair[] a) {
        StringBuilder sb = new StringBuilder();
        for (Pair p : a) sb.append(p.key()).append(':').append(p.label()).append(' ');
        return sb.toString().trim();
    }

    public static void main(String[] args) {
        Pair[] in = { new Pair(2,'a'), new Pair(1,'b'), new Pair(2,'c'), new Pair(1,'d') };
        System.out.println("стабильно:   " + show(stableSort(in.clone())));
        System.out.println("нестабильно: " + show(unstableSort(in.clone())));
    }
}
