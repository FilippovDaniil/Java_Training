package m08_methods.practice;

/**
 * Задача 02 — Модуль 08: Метод с возвратом значения
 *
 * ЗАДАНИЕ:
 *   Напишите метод int sum(int a, int b), возвращающий сумму.
 *   В main вызовите его, сохраните результат в переменную и выведите.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (для sum(7, 8)):
 *   15
 *
 * ПОДСКАЗКА:
 *   return a + b;  — метод возвращает значение, main его печатает.
 */
public class Task02 {
    public static void main(String[] args) {
        // Вызовите метод и выведите результат
        System.out.println(sum(5,4));
        System.out.println(sum(55,44));

    }

    // Объявите метод sum ниже
    private static int sum(int a, int b){
        return a+b;
    }
}
