package m14_collections_set_iterator.practice;
import java.util.Random;

/**
 * Задача 05 — Модуль 14: HashSet vs TreeSet
 *
 * ЗАДАНИЕ:
 *   Добавьте одни и те же числа в HashSet и в TreeSet
 *   (например: 50, 10, 30, 20, 40). Выведите оба множества и
 *   сравните порядок элементов. Объясните в комментарии разницу.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   HashSet: [50, 20, 40, 10, 30]   (порядок не гарантирован)
 *   TreeSet: [10, 20, 30, 40, 50]   (всегда отсортирован)
 *
 * ПОДСКАЗКА:
 *   Set<Integer> tree = new TreeSet<>();  TreeSet хранит элементы
 *   в отсортированном порядке.
 */
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class Task05 {
    public static void main(String[] args) {
        // Ваш код здесь
        Set<Integer> treeSet = new TreeSet<>();
        Set<Integer> hashSet = new HashSet<>();
        Random random = new Random();

        for (int i = 0; i < 100; i++){
            int ran = random.nextInt(100,200);
            treeSet.add(ran);
            hashSet.add(ran);
        }

        System.out.println(treeSet);
        System.out.println(hashSet);


    }
}
