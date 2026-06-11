package m11_substring_search.practice;

/**
 * Задача 03 — Тема 11: Алгоритм КМП (поиск всех вхождений)
 *
 * ЗАДАНИЕ:
 *   Реализуйте поиск всех вхождений паттерна в текст алгоритмом Кнута-Морриса-
 *   Пратта за O(n + m), используя префикс-функцию (задача 02).
 *
 * ПРИМЕР / ВЫВОД:
 *   T = "ABABDABACDABABCABAB", P = "ABABCABAB"
 *   вхождения: [10]
 *   T = "AAAAA", P = "AA" -> [0, 1, 2, 3]
 *
 * ТРЕБОВАНИЯ:
 *   - сначала построить pi для паттерна;
 *   - идти по тексту ОДИН раз без возвратов;
 *   - при несовпадении откатывать j = pi[j-1];
 *   - после полного совпадения тоже откатывать j = pi[j-1] (искать дальше).
 *
 * ПОДСКАЗКА:
 *   for (i=0, j=0; i<n; i++){ while(j>0 && T[i]!=P[j]) j=pi[j-1];
 *     if(T[i]==P[j]) j++; if(j==m){ add(i-m+1); j=pi[j-1]; } }
 */

import java.util.ArrayList;
import java.util.List;

public class Task03 {

    static List<Integer> kmpSearch(String text, String pattern) {
        List<Integer> result = new ArrayList<>();
        // TODO: построить префикс-функцию pattern; пройти текст по алгоритму КМП
        return result;
    }

    public static void main(String[] args) {
        System.out.println(kmpSearch("ABABDABACDABABCABAB", "ABABCABAB"));   // [10]
        System.out.println(kmpSearch("AAAAA", "AA"));                         // [0,1,2,3]
    }
}
