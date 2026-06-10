/**
 * Задача 05 — Тема 22: Редакционное расстояние (Левенштейн)
 *
 * ЗАДАНИЕ:
 *   Найдите минимальное число операций (вставка, удаление, замена символа),
 *   чтобы превратить строку a в строку b.
 *
 * ПРИМЕР / ВЫВОД:
 *   "kitten" -> "sitting" = 3   (k->s, e->i, +g)
 *   "flaw" -> "lawn" = 2
 *   "" -> "abc" = 3
 *
 * ТРЕБОВАНИЯ:
 *   - dp[i][j] = расстояние между a[0..i) и b[0..j);
 *   - база: dp[i][0]=i (удалить всё), dp[0][j]=j (вставить всё);
 *   - переход: если a[i-1]==b[j-1] -> dp[i-1][j-1];
 *     иначе 1 + min(dp[i-1][j] удаление, dp[i][j-1] вставка, dp[i-1][j-1] замена);
 *   - O(n·m).
 *
 * ПОДСКАЗКА:
 *   Три направления перехода соответствуют трём операциям редактирования.
 */

public class Task05 {

    static int editDistance(String a, String b) {
        // TODO: 2D DP с базой и тремя переходами
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(editDistance("kitten", "sitting"));   // 3
        System.out.println(editDistance("flaw", "lawn"));        // 2
        System.out.println(editDistance("", "abc"));             // 3
    }
}
