package m22_dynamic_programming_intro.practice;

/**
 * Задача 06 — Тема 22: Размен монет (минимальное число монет)
 *
 * ЗАДАНИЕ:
 *   Дан набор номиналов монет (неограниченный запас каждой) и сумма amount.
 *   Найдите минимальное число монет, дающее ровно эту сумму. Если набрать
 *   сумму нельзя — верните -1.
 *
 * ПРИМЕР / ВЫВОД:
 *   монеты [1,3,4], amount=6 -> 2   (3+3)
 *   монеты [2], amount=3 -> -1      (нечётную сумму не набрать)
 *   amount=0 -> 0
 *
 * ТРЕБОВАНИЯ:
 *   - dp[s] = мин число монет на сумму s; dp[0]=0; остальное «бесконечность»;
 *   - переход: dp[s] = min по монетам (dp[s-coin] + 1), если s>=coin и dp[s-coin] достижимо;
 *   - вернуть dp[amount] или -1.
 *
 * ПОДСКАЗКА:
 *   Инициализируйте dp значением amount+1 (заведомо недостижимо) как «бесконечность».
 */

public class Task06 {

    static int coinChange(int[] coins, int amount) {
        // TODO: dp[s] = min монет; вернуть dp[amount] или -1
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(coinChange(new int[]{1, 3, 4}, 6));   // 2
        System.out.println(coinChange(new int[]{2}, 3));          // -1
        System.out.println(coinChange(new int[]{1, 2, 5}, 0));    // 0
    }
}
