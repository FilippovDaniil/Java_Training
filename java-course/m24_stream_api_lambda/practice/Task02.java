package m24_stream_api_lambda.practice;

/**
 * Задача 02 — Модуль 24: Predicate и Function
 *
 * ЗАДАНИЕ:
 *   Используя готовые функциональные интерфейсы:
 *     1) Predicate<Integer> isEven — проверка чётности; примените к
 *        нескольким числам.
 *     2) Function<String, Integer> length — длина строки; примените к
 *        нескольким словам.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   4 чётное? true
 *   7 чётное? false
 *   Длина "Java": 4
 *   Длина "Stream": 6
 *
 * ПОДСКАЗКА:
 *   Predicate<Integer> isEven = n -> n % 2 == 0;  isEven.test(4);
 *   Function<String,Integer> len = s -> s.length();  len.apply("Java");
 */
import java.util.function.Function;
import java.util.function.Predicate;

public class Task02 {
    public static void main(String[] args) {
        // Создайте Predicate и Function, примените их
        Predicate<Integer> isEven = n -> n % 2 == 0;
        Function<String, Integer> len = s -> s.length();
        System.out.println("4 чётное ? " + isEven.test(4));
        System.out.println("7 чётное ? " + isEven.test(7));
        System.out.println("Длина Java: " + len.apply("Java"));
        System.out.println("Длина Stream: " + len.apply("Stream"));
    }
}
