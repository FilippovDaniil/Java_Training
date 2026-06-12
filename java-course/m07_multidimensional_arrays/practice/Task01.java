package m07_multidimensional_arrays.practice;

/**
 * Задача 01 — Модуль 07: Вывод матрицы
 *
 * ЗАДАНИЕ:
 *   Дана матрица 3x3. Выведите её в виде сетки (строки на отдельных
 *   строках, элементы через табуляцию или пробел).
 *
 * ОЖИДАЕМЫЙ ВЫВОД:
 *   1 2 3
 *   4 5 6
 *   7 8 9
 *
 * ПОДСКАЗКА:
 *   Вложенные циклы: внешний по строкам (m.length),
 *   внутренний по столбцам (m[row].length).
 */
public class Task01 {
    public static void main(String[] args) {
        int[][] m = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        // Ваш код здесь

        for(int row = 0; row < m.length; row++){
            for (int col = 0; col < m[row].length; col++){
                System.out.print(m[row][col] + "\t");
            }
            System.out.println();
        }

        System.out.println();

        for (int[] row: m){
            for(int value: row){
                System.out.print(value + "\t");
            }
            System.out.println();
        }
    }
}
