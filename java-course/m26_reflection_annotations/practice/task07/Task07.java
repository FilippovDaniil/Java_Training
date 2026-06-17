package m26_reflection_annotations.practice.task07;

/**
 * Задача 07 — Модуль 26 (МИНИ-ПРОЕКТ): Мини-фреймворк тестирования
 *
 * ЗАДАНИЕ:
 *   Напишите упрощённый «движок тестов» — как делает JUnit (модуль 28).
 *   1. Объявите аннотацию @MyTest (RUNTIME, METHOD).
 *   2. Класс MathTests с несколькими public-методами, помеченными
 *      @MyTest. Каждый метод проверяет какое-то условие и БРОСАЕТ
 *      исключение (например, через свой assertTrue), если проверка
 *      не прошла. Сделайте 3-4 теста: часть успешных, часть «падающих».
 *   3. Класс-движок TestRunner: через рефлексию находит все методы
 *      @MyTest в MathTests, создаёт объект, вызывает каждый метод
 *      (invoke) в try-catch и печатает результат: PASSED / FAILED.
 *   4. В конце выведите статистику: пройдено X из Y.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   testAddition: PASSED
 *   testDivision: FAILED (ожидалось 5, получено 4)
 *   testMax: PASSED
 *   Итог: 2 из 3 пройдено
 *
 * ПОДСКАЗКИ:
 *   - сканирование: clazz.getDeclaredMethods() + isAnnotationPresent;
 *   - вызов: method.invoke(instance);
 *   - «падение» теста: метод бросает исключение, движок ловит его в
 *     catch и считает тест FAILED (m.invoke оборачивает в
 *     InvocationTargetException — используйте e.getCause());
 *   - заведите простой assertEquals(expected, actual), бросающий
 *     RuntimeException при несовпадении.
 */

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Task07 {

    public static void main(String[] args) {
        // Запускаем тесты для класса MathTests
        runTests(MathTests.class);
    }

    private static void runTests(Class<?> testClass) {
        // Получаем все методы класса
        Method[] methods = testClass.getDeclaredMethods();

        int total = 0;
        int passed = 0;

        System.out.println("Запуск тестов:");

        // Создаём экземпляр тестового класса
        Object instance = null;
        try {
            instance = testClass.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            System.err.println("Не удалось создать экземпляр класса: " + e.getMessage());
            return;
        }

        for (Method m : methods) {
            // Проверяем, помечен ли метод аннотацией @MyTest
            if (m.isAnnotationPresent(MyTest.class)) {
                total++;
                System.out.print(m.getName() + ": ");
                try {
                    // Вызываем метод
                    m.invoke(instance);
                    System.out.println("PASSED");
                    passed++;
                } catch (InvocationTargetException e) {
                    // Тест упал – исключение внутри метода
                    Throwable cause = e.getCause();
                    String message = (cause != null) ? cause.getMessage() : "без сообщения";
                    System.out.println("FAILED (" + message + ")");
                } catch (IllegalAccessException e) {
                    System.out.println("FAILED (недоступный метод)");
                }
            }
        }

        System.out.println("Итог: " + passed + " из " + total + " пройдено");
    }
}