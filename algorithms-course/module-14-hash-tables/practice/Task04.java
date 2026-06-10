/**
 * Задача 04 — Тема 14: Полиномиальная хеш-функция для строк
 *
 * ЗАДАНИЕ:
 *   Реализуйте полиномиальный хеш строки: h = s[0]·p^(n-1) + ... + s[n-1],
 *   вычисляемый по схеме Горнера: h = h*p + s[i]. Используйте p=31 (как в
 *   String.hashCode). Проверьте равномерность: распределите несколько слов
 *   по N корзинам и выведите, сколько слов попало в каждую.
 *
 * ПРИМЕР / ВЫВОД:
 *   hash("cat") = ...
 *   hash("act") != hash("cat")   (разный порядок -> разный хеш)
 *   распределение слов по 8 корзинам: [.., .., ..]  (более-менее равномерно)
 *
 * ТРЕБОВАНИЯ:
 *   - схема Горнера: for c in s: h = h * 31 + c;
 *   - индекс корзины: (h & 0x7fffffff) % N;
 *   - покажите, что анаграммы ("cat"/"act") дают разные хеши.
 *
 * ПОДСКАЗКА:
 *   Переполнение int при умножении — это нормально для хеша (берём по модулю 2^32).
 */

public class Task04 {

    static int polyHash(String s) {
        // TODO: схема Горнера с p=31
        return 0;
    }

    public static void main(String[] args) {
        System.out.println("hash(cat) = " + polyHash("cat"));
        System.out.println("hash(act) = " + polyHash("act"));
        String[] words = {"apple", "banana", "cherry", "date", "fig", "grape", "kiwi", "lemon"};
        int[] dist = new int[8];
        // TODO: разложить words по 8 корзинам через polyHash и вывести dist
    }
}
