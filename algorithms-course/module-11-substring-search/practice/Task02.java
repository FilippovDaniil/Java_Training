/**
 * Задача 02 — Тема 11: Префикс-функция (failure function)
 *
 * ЗАДАНИЕ:
 *   Вычислите префикс-функцию строки: pi[i] = длина наибольшего собственного
 *   префикса подстроки s[0..i], который одновременно является её суффиксом.
 *   Это основа КМП (задача 03).
 *
 * ПРИМЕР / ВЫВОД:
 *   s = "ABABC"  -> pi = [0, 0, 1, 2, 0]
 *   s = "AAAA"   -> pi = [0, 1, 2, 3]
 *   s = "ABCABD" -> pi = [0, 0, 0, 1, 2, 0]
 *
 * ТРЕБОВАНИЯ:
 *   - O(m); вернуть массив pi длины s.length();
 *   - при несовпадении откатываться по k = pi[k-1] (НЕ k=0);
 *   - pi[0] = 0 всегда.
 *
 * ПОДСКАЗКА:
 *   for (i=1, k=0; i<m; i++){ while(k>0 && s[i]!=s[k]) k=pi[k-1]; if(s[i]==s[k]) k++; pi[i]=k; }
 */

import java.util.Arrays;

public class Task02 {

    static int[] prefixFunction(String s) {
        int[] pi = new int[s.length()];
        // TODO: вычислить префикс-функцию
        return pi;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(prefixFunction("ABABC")));    // [0,0,1,2,0]
        System.out.println(Arrays.toString(prefixFunction("AAAA")));     // [0,1,2,3]
        System.out.println(Arrays.toString(prefixFunction("ABCABD")));   // [0,0,0,1,2,0]
    }
}
