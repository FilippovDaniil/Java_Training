package m26_reflection_annotations.practice.task06;

/**
 * Задача 06 — Модуль 26: Чтение аннотаций на методах
 *
 * ЗАДАНИЕ:
 *   Дана аннотация @Important (RUNTIME, METHOD). Пометьте ею некоторые
 *   методы класса Service. С помощью рефлексии переберите все методы
 *   и выведите имена тех, что помечены @Important.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   Важные методы:
 *   processPayment
 *   deleteAccount
 *
 * ПОДСКАЗКА:
 *   m.isAnnotationPresent(Important.class) — проверка наличия аннотации.
 */

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;

public class Task06 {
    public static void main(String[] args) {
        System.out.println("Важные методы:");

        // Получаем все методы класса Service (включая package-private и private)
        Method[] methods = Service.class.getDeclaredMethods();

        for (Method m : methods) {
            // Проверяем наличие аннотации @Important
            if (m.isAnnotationPresent(Important.class)) {
                System.out.println(m.getName());
            }
        }
    }
}
