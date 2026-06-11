package m04_stack_queue.practice;

/**
 * Задача 05 — Тема 04: Кольцевая очередь (circular buffer) на массиве
 *
 * ЗАДАНИЕ:
 *   Реализуйте очередь фиксированной ёмкости на массиве с двумя индексами
 *   (head и tail), ходящими по кругу через % capacity. Все операции — O(1),
 *   без сдвигов элементов.
 *
 * ПРИМЕР / ВЫВОД:
 *   cap=3
 *   enqueue 1,2,3  (полна)
 *   enqueue 4 -> false (нет места)
 *   dequeue -> 1
 *   enqueue 4 -> true (теперь место есть; tail обернулся)
 *   dequeue -> 2, 3, 4
 *
 * ТРЕБОВАНИЯ:
 *   - хранить count (число элементов), чтобы отличать «пусто» от «полно»
 *     (иначе head==tail неоднозначно);
 *   - enqueue в полную очередь -> false; dequeue из пустой -> исключение;
 *   - индексы продвигаются по модулю capacity.
 *
 * ПОДСКАЗКА:
 *   tail = (tail + 1) % capacity; head = (head + 1) % capacity; count++/count--.
 */

public class Task05 {

    static class CircularQueue {
        private final int[] data;
        private int head = 0, tail = 0, count = 0;

        public CircularQueue(int capacity) { this.data = new int[capacity]; }

        public boolean enqueue(int x) {
            // TODO: если count == data.length -> false; иначе записать в tail, продвинуть tail, count++
            return false;
        }

        public int dequeue() {
            // TODO: если count==0 -> исключение; взять data[head], продвинуть head, count--
            return 0;
        }

        public boolean isEmpty() { return count == 0; }
        public boolean isFull() { return count == data.length; }
    }

    public static void main(String[] args) {
        CircularQueue q = new CircularQueue(3);
        // TODO: воспроизведите сценарий из примера (заполнение, оборот, опустошение)
    }
}
