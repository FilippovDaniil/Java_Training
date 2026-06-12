package m08_methods.practice;

/**
 * Задача 04 — Модуль 08: Метод с массивом-параметром
 *
 * ЗАДАНИЕ:
 *   Напишите два метода:
 *     - int sumArray(int[] arr)  — возвращает сумму элементов;
 *     - int maxArray(int[] arr)  — возвращает максимальный элемент.
 *   Вызовите их для готового массива в main.
 *
 * ПРИМЕР (для {3, 7, 2, 9, 5}):
 *   Сумма: 26
 *   Максимум: 9
 *
 * ПОДСКАЗКА:
 *   Массив передаётся по ссылке, но читать его внутри метода безопасно.
 */
public class Task04 {
    public static void main(String[] args) {
        int[] arr = {3, 7, 2, 9, 5};
        // Вызовите методы и выведите результаты
        System.out.println(sumArray(arr));
        System.out.println(maxArray(arr));
    }

    // Объявите методы sumArray и maxArray ниже
    private static int sumArray(int[] arr){
        int sum = 0;
        for (int i : arr) {
            sum = sum + i;
        }
        return sum;
    }

    private static int maxArray(int[] arr){
        int max = arr[0];
        for(int i = 1; i < arr.length; i++){
            if(arr[i] > max){
                max = arr[i];
            }
        }
        return max;
    }
}
