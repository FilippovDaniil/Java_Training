package m33_guava_apache_commons.practice;

/**
 * Задача 01 — Модуль 33: Guava Multimap
 *
 * ТРЕБУЕТСЯ ЗАВИСИМОСТЬ: com.google.guava:guava:33.2.1-jre
 * (добавьте в pom.xml/Gradle; без неё файл не компилируется).
 *
 * ЗАДАНИЕ:
 *   Используя ArrayListMultimap, постройте справочник
 *   "категория -> товары". Добавьте несколько товаров в разные
 *   категории (в т.ч. несколько в одну). Затем:
 *     - выведите все товары категории "фрукты";
 *     - выведите общее число пар (size);
 *     - переберите все ключи (keySet) и их значения.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   Фрукты: [яблоко, банан, груша]
 *   Всего пар: 5
 *
 * ПОДСКАЗКА:
 *   Multimap<String, String> m = ArrayListMultimap.create();
 *   m.put("фрукты", "яблоко"); m.get("фрукты");
 */
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

public class Task01 {
    public static void main(String[] args) {
        // Создайте Multimap и заполните его категориями
        Multimap<String, String> m = ArrayListMultimap.create();
        m.put("Fruits","apple");
        m.put("Fruits","mango");
        m.put("Fruits","watemelon");
        m.put("Wegetables","carrot");
        m.put("Wegetables","potato");
        System.out.println(m.get("Fruits"));
        System.out.println(m.get("Wegetables"));
    }
}
