package m33_guava_apache_commons.practice;

/**
 * Задача 03 — Модуль 33: Guava Multiset (подсчёт)
 *
 * ТРЕБУЕТСЯ ЗАВИСИМОСТЬ: com.google.guava:guava:33.2.1-jre
 *
 * ЗАДАНИЕ:
 *   Посчитайте частоту слов в тексте с помощью HashMultiset.
 *   1. Разбейте строку на слова.
 *   2. Добавьте каждое слово в Multiset.
 *   3. Выведите count() для нескольких слов и все уникальные элементы
 *      с их количеством (elementSet / entrySet).
 *
 * ПРИМЕР:
 *   "кот пёс кот рыба кот пёс" →
 *   кот: 3
 *   пёс: 2
 *   рыба: 1
 *
 * ПОДСКАЗКА:
 *   Multiset<String> ms = HashMultiset.create();
 *   ms.add(word); ms.count("кот"); ms.elementSet();
 *   Сравните с ручным подсчётом через HashMap (модуль 15) — здесь короче.
 */
import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Task03 {
    public static void main(String[] args) {
        String text = "кот пёс кот рыба кот пёс";
        List<String> list1 = new ArrayList<>(Arrays.asList(text.split(" ")));
        // Посчитайте частоту слов через Multiset
        Multiset<String> ms = HashMultiset.create();
        for (String string : list1) {
            ms.add(string);
        }
        System.out.println(ms);

    }
}
