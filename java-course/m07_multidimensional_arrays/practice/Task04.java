package m07_multidimensional_arrays.practice;

/**
 * Задача 04 — Модуль 07: Сумма диагоналей
 *
 * ЗАДАНИЕ:
 *   Для квадратной матрицы посчитайте сумму главной диагонали
 *   (элементы [i][i]) и побочной диагонали (элементы [i][n-1-i]).
 *
 * ПРИМЕР (для матрицы из Task01):
 *   Главная диагональ: 15   (1 + 5 + 9)
 *   Побочная диагональ: 15  (3 + 5 + 7)
 *
 * ПОДСКАЗКА:
 *   Один цикл по i: главная — m[i][i], побочная — m[i][n-1-i],
 *   где n = m.length.
 */
public class Task04 {
    public static void main(String[] args) {
        int[][] m = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        // Ваш код здесь
        int diag = 0;
        int poboch_diag = 0;
        for (int i = 0; i < m.length; i++){
            diag = diag + m[i][i];
            poboch_diag = poboch_diag + m[i][m.length-1-i];
        }
        System.out.println(diag);
        System.out.println(poboch_diag);
    }
}
