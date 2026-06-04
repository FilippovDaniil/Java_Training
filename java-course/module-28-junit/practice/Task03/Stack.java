import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

// Класс под тестом (готов)
class Stack {
    private int[] data = new int[100];
    private int size = 0;

    void push(int value) { data[size++] = value; }
    int pop() { return data[--size]; }
    int size() { return size; }
    boolean isEmpty() { return size == 0; }
}
