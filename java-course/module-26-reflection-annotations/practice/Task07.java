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
import java.lang.reflect.Method;

public class Task07 {
    public static void main(String[] args) {
        // Запустите TestRunner для класса MathTests
    }
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface MyTest {
}

// TODO: класс MathTests с методами @MyTest и класс/логика TestRunner
