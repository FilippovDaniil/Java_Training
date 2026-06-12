package m08_methods.practice;

/**
 * Задача 03 — Модуль 08: Метод, возвращающий boolean
 *
 * ЗАДАНИЕ:
 *   Напишите метод boolean isEven(int n), возвращающий true,
 *   если число чётное. В main проверьте несколько чисел.
 *
 * ПРИМЕР:
 *   isEven(4) → true
 *   isEven(7) → false
 *
 * ПОДСКАЗКА:
 *   return n % 2 == 0;  — результат сравнения уже boolean.
 */
public class Task03 {
    public static void main(String[] args) {
        // Проверьте метод на нескольких числах
        System.out.println(isEven(7));
        System.out.println(isEven(8));
    }

    // Объявите метод isEven ниже
    private static boolean isEven(int number){
        if(number % 2 ==0){
            return true;
        } else {
            return false;
        }
    }
}
