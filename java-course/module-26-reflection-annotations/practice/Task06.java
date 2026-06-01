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
        // Найдите методы Service, помеченные @Important
    }
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface Important {
}

class Service {
    // TODO: пометьте часть методов аннотацией @Important
    void processPayment() { }
    void log() { }
    void deleteAccount() { }
    void ping() { }
}
