/**
 * Задача 04 — Тема 22: Наибольшая общая подпоследовательность (LCS)
 *
 * ЗАДАНИЕ:
 *   Найдите длину наибольшей общей ПОДПОСЛЕДОВАТЕЛЬНОСТИ двух строк
 *   (символы идут в том же порядке, но не обязательно подряд).
 *
 * ПРИМЕР / ВЫВОД:
 *   "ABCBDAB", "BDCAB" -> 4   (например "BCAB" или "BDAB")
 *   "AGGTAB", "GXTXAYB" -> 4  ("GTAB")
 *
 * ТРЕБОВАНИЯ:
 *   - dp[i][j] = длина LCS префиксов a[0..i) и b[0..j);
 *   - переход: если a[i-1]==b[j-1] -> dp[i-1][j-1]+1; иначе max(dp[i-1][j], dp[i][j-1]);
 *   - O(n·m); вернуть dp[n][m].
 *
 * ПОДСКАЗКА:
 *   База — нулевая строка: dp[0][*]=dp[*][0]=0.
 */

public class Task04 {

    static int lcs(String a, String b) {
        // TODO: 2D DP по префиксам
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(lcs("ABCBDAB", "BDCAB"));   // 4
        System.out.println(lcs("AGGTAB", "GXTXAYB"));  // 4
    }
}
