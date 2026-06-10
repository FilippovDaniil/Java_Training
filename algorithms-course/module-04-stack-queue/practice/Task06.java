/**
 * Задача 06 — Тема 04: MinStack — стек с минимумом за O(1)
 *
 * ЗАДАНИЕ:
 *   Реализуйте стек, который помимо push/pop/peek умеет getMin() — вернуть
 *   текущий минимум всех элементов в стеке за O(1).
 *
 * ПРИМЕР / ВЫВОД:
 *   push 5, 3, 7, 2
 *   getMin -> 2
 *   pop (убрали 2)
 *   getMin -> 3
 *
 * ТРЕБОВАНИЯ:
 *   - все операции (push, pop, peek, getMin) — O(1);
 *   - getMin/pop на пустом стеке — исключение;
 *   - НЕ искать минимум линейно при каждом запросе.
 *
 * ПОДСКАЗКА:
 *   Держите ВТОРОЙ стек минимумов: при push кладите туда min(x, текущий минимум),
 *   при pop снимайте с обоих стеков. Вершина стека минимумов — всегда текущий min.
 */

import java.util.ArrayDeque;
import java.util.Deque;

public class Task06 {

    static class MinStack {
        private final Deque<Integer> data = new ArrayDeque<>();
        private final Deque<Integer> mins = new ArrayDeque<>();

        public void push(int x) {
            // TODO: data.push(x); в mins положить min(x, mins.peek() если не пуст)
        }

        public int pop() {
            // TODO: снять с обоих стеков, вернуть значение data
            return 0;
        }

        public int peek() {
            // TODO: вершина data
            return 0;
        }

        public int getMin() {
            // TODO: вершина mins
            return 0;
        }
    }

    public static void main(String[] args) {
        MinStack s = new MinStack();
        // TODO: push 5,3,7,2; getMin; pop; getMin
    }
}
