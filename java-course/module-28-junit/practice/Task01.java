/**
 * Задача 01 — Модуль 28: Первый тест (assertEquals)
 *
 * ВНИМАНИЕ: это JUnit-тест, а НЕ программа с main.
 * Запускайте его в IntelliJ IDEA (▶) или через Maven/Gradle (mvn test).
 * Нужна библиотека JUnit 5 на classpath.
 *
 * ЗАДАНИЕ:
 *   Класс Calculator (ниже) уже реализован. Напишите тесты:
 *     1) testAdd — проверьте, что add(2, 3) возвращает 5;
 *     2) testSubtract — проверьте, что subtract(10, 4) возвращает 6;
 *     3) testMultiply — проверьте, что multiply(3, 4) возвращает 12.
 *   Используйте assertEquals(ожидаемое, фактическое).
 *
 * ПОДСКАЗКА:
 *   @Test void testAdd() { assertEquals(5, new Calculator().add(2, 3)); }
 *   Помните паттерн AAA: Arrange → Act → Assert.
 */
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Task01 {

    @Test
    void testAdd() {
        // Ваш код здесь
    }

    // TODO: добавьте тесты testSubtract и testMultiply
}

// Класс под тестом (готов, менять не нужно)
class Calculator {
    int add(int a, int b)      { return a + b; }
    int subtract(int a, int b) { return a - b; }
    int multiply(int a, int b) { return a * b; }
}
