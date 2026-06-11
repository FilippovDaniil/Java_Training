package m02_arrays.practice.task02;

/**
 * Задача 02 — Тема 02: Грязный рост vs удвоение
 *
 * ЗАДАНИЕ:
 *   Сравните две стратегии роста динамического массива по числу КОПИРОВАНИЙ
 *   элементов при добавлении N штук:
 *     - GrowByOne     — ёмкость растёт на +1 каждый раз (грязный рост) -> O(n^2) копий;
 *     - GrowByDoubling — ёмкость удваивается -> O(n) копий (амортизация).
 *   Каждый класс считает суммарное число скопированных элементов в поле copies.
 *   В main добавьте по N=1000 элементов в оба и сравните счётчики.
 *
 * ПРИМЕР / ВЫВОД:
 *   N=1000
 *   GrowByOne:      копий ~= 499500   (≈ N^2/2)
 *   GrowByDoubling: копий ~= 1023     (≈ N)
 *   Вывод: удвоение даёт амортизированную O(1) на add.
 *
 * ТРЕБОВАНИЯ:
 *   - реализуйте классы GrowByOne и GrowByDoubling (отдельные файлы);
 *   - оба умеют add(int) и хранят long copies — сколько элементов скопировано
 *     при всех расширениях;
 *   - НЕ использовать ArrayList.
 *
 * ПОДСКАЗКА:
 *   При расширении вы создаёте новый массив и копируете size старых элементов —
 *   именно это (size) прибавляйте к copies.
 */

public class Task02 {
    public static void main(String[] args) {
        int n = 1000;
        GrowByOne one = new GrowByOne();
        GrowByDoubling two = new GrowByDoubling();
        for (int i = 0; i < n; i++) { one.add(i); two.add(i); }
        System.out.println("N=" + n);
        System.out.println("GrowByOne:      копий = " + one.copies);
        System.out.println("GrowByDoubling: копий = " + two.copies);
    }
}
