package m28_junit.practice.task03;

/**
 * Задача 03 — Модуль 28: @BeforeEach (подготовка)
 *
 * ВНИМАНИЕ: JUnit-тест. Запускайте через IDE или Maven/Gradle.
 *
 * ЗАДАНИЕ:
 *   Класс Stack реализован. Используя @BeforeEach, создавайте новый
 *   пустой Stack перед каждым тестом (чтобы тесты были независимы).
 *   Напишите тесты:
 *     1) только что созданный стек пуст (isEmpty == true);
 *     2) после push размер увеличивается;
 *     3) pop возвращает последний добавленный элемент (LIFO);
 *     4) после push+pop стек снова пуст.
 *
 * ПОДСКАЗКА:
 *   @BeforeEach void setUp() { stack = new Stack(); }
 *   Поле stack объявите на уровне класса теста.
 */

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Task03 {

    Stack stack;

    @BeforeEach
    void setUp() {
        // Создаем новый пустой Stack перед каждым тестом
        stack = new Stack();
    }

    // Тест 1: только что созданный стек пуст
    @Test
    void testNewStackIsEmpty() {
        assertTrue(stack.isEmpty(), "New stack should be empty");
        assertEquals(0, stack.size(), "New stack size should be 0");
    }

    // Тест 2: после push размер увеличивается
    @Test
    void testPushIncreasesSize() {
        assertEquals(0, stack.size(), "Stack should be empty before push");

        stack.push(10);
        assertEquals(1, stack.size(), "Size should be 1 after one push");
        assertFalse(stack.isEmpty(), "Stack should not be empty after push");

        stack.push(20);
        assertEquals(2, stack.size(), "Size should be 2 after two pushes");
    }

    // Тест 3: pop возвращает последний добавленный элемент (LIFO)
    @Test
    void testPopReturnsLastAddedElement() {
        stack.push(10);
        stack.push(20);
        stack.push(30);

        assertEquals(3, stack.size(), "Size should be 3 before pop");
        assertEquals(30, stack.pop(), "First pop should return last pushed element (30)");
        assertEquals(2, stack.size(), "Size should be 2 after first pop");

        assertEquals(20, stack.pop(), "Second pop should return 20");
        assertEquals(1, stack.size(), "Size should be 1 after second pop");

        assertEquals(10, stack.pop(), "Third pop should return 10");
        assertEquals(0, stack.size(), "Size should be 0 after third pop");
    }

    // Тест 4: после push+pop стек снова пуст
    @Test
    void testStackIsEmptyAfterPushAndPop() {
        assertTrue(stack.isEmpty(), "Stack should be empty initially");

        stack.push(42);
        assertFalse(stack.isEmpty(), "Stack should not be empty after push");

        int value = stack.pop();
        assertEquals(42, value, "Popped value should match pushed value");

        assertTrue(stack.isEmpty(), "Stack should be empty after push+pop");
        assertEquals(0, stack.size(), "Size should be 0 after push+pop");
    }
}
