package m23_dp_intervals_2d.practice;

/**
 * Задача 06 — Тема 23: Разбиение строки на слова (word break)
 *
 * ЗАДАНИЕ:
 *   Дана строка s и словарь слов. Определите, можно ли разбить s на
 *   последовательность слов из словаря (слова можно использовать много раз).
 *
 * ПРИМЕР / ВЫВОД:
 *   s="applepenapple", dict={apple, pen} -> true   (apple+pen+apple)
 *   s="catsandog", dict={cats,dog,sand,and,cat} -> false
 *
 * ТРЕБОВАНИЯ:
 *   - dp[i] = можно ли разбить префикс s[0..i);
 *   - dp[0]=true; dp[i]=true, если есть j<i: dp[j] && s[j..i) в словаре;
 *   - O(n² · проверка слова) или с Set для быстрой проверки;
 *   - вернуть dp[n].
 *
 * ПОДСКАЗКА:
 *   Словарь — в HashSet для проверки наличия подстроки за ~O(длины слова).
 */

import java.util.Set;

public class Task06 {

    static boolean wordBreak(String s, Set<String> dict) {
        // TODO: dp[i] = разбиваем ли префикс длины i
        return false;
    }

    public static void main(String[] args) {
        System.out.println(wordBreak("applepenapple", Set.of("apple", "pen")));         // true
        System.out.println(wordBreak("catsandog", Set.of("cats","dog","sand","and","cat"))); // false
    }
}
