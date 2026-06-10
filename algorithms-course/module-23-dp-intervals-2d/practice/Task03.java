/**
 * Задача 03 — Тема 23: Самая длинная палиндромная подстрока (DP O(n²))
 *
 * ЗАДАНИЕ:
 *   Найдите самую длинную подстроку, являющуюся палиндромом, методом DP:
 *   dp[l][r] = является ли s[l..r] палиндромом.
 *
 * ПРИМЕР / ВЫВОД:
 *   "babad" -> "bab" (или "aba"), длина 3
 *   "cbbd"  -> "bb", длина 2
 *   "a"     -> "a"
 *
 * ТРЕБОВАНИЯ:
 *   - dp[l][r] = (s[l]==s[r]) && (r-l<2 || dp[l+1][r-1]);
 *   - заполнять по возрастанию длины подстроки; запоминать самый длинный палиндром;
 *   - вернуть саму подстроку (или её границы); O(n²).
 *
 * ПОДСКАЗКА:
 *   Базы: все одиночные символы — палиндромы; пары — если символы равны.
 */

public class Task03 {

    static String longestPalindrome(String s) {
        // TODO: 2D DP по длине; вернуть самый длинный палиндром
        return "";
    }

    public static void main(String[] args) {
        System.out.println(longestPalindrome("babad"));   // bab или aba
        System.out.println(longestPalindrome("cbbd"));     // bb
        System.out.println(longestPalindrome("a"));        // a
    }
}
