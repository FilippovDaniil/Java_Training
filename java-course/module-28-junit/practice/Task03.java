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
        // TODO: создайте новый Stack перед каждым тестом
    }

    // TODO: напишите 4 теста, описанных в задании

}

// Класс под тестом (готов)
class Stack {
    private int[] data = new int[100];
    private int size = 0;

    void push(int value) { data[size++] = value; }
    int pop() { return data[--size]; }
    int size() { return size; }
    boolean isEmpty() { return size == 0; }
}
