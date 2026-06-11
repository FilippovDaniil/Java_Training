package m04_stack_queue.practice.task01;

/**
 * Стек целых чисел на массиве. Поддерживает динамический рост (удвоение).
 */
public class ArrayStack {

    private int[] data;
    private int top;   // индекс следующей свободной позиции (= число элементов)

    public ArrayStack() { this(8); }
    public ArrayStack(int capacity) { this.data = new int[Math.max(1, capacity)]; this.top = 0; }

    /** Положить на вершину. Амортизированно O(1). */
    public void push(int x) {
        // TODO: если top == data.length — расширить (удвоить); data[top++] = x
    }

    /** Снять с вершины. O(1). Пусто -> исключение. */
    public int pop() {
        // TODO: проверить пустоту; вернуть data[--top]
        return 0;
    }

    /** Посмотреть вершину без снятия. O(1). */
    public int peek() {
        // TODO
        return 0;
    }

    public boolean isEmpty() { return top == 0; }
    public int size() { return top; }
}
