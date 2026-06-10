/**
 * Задача 02 — Тема 04: Очередь на основе двух стеков
 *
 * ЗАДАНИЕ:
 *   Реализуйте очередь (FIFO), используя ВНУТРИ только два стека (in и out).
 *   Классический приём: enqueue кладёт в стек in; dequeue, если out пуст,
 *   переливает всё из in в out (порядок переворачивается -> получается FIFO),
 *   затем снимает с вершины out.
 *
 * ПРИМЕР / ВЫВОД:
 *   enqueue 1,2,3
 *   dequeue -> 1
 *   enqueue 4
 *   dequeue -> 2, 3, 4
 *
 * ТРЕБОВАНИЯ:
 *   - внутри только два стека (Deque/ArrayDeque в роли стеков);
 *   - dequeue на пустой очереди — исключение;
 *   - амортизированная сложность enqueue/dequeue — O(1) (каждый элемент
 *     переливается максимум один раз).
 *
 * ПОДСКАЗКА:
 *   Переливайте in -> out ТОЛЬКО когда out пуст, иначе нарушите порядок.
 */

import java.util.ArrayDeque;
import java.util.Deque;

public class Task02 {

    static class QueueViaTwoStacks {
        private final Deque<Integer> in = new ArrayDeque<>();
        private final Deque<Integer> out = new ArrayDeque<>();

        public void enqueue(int x) {
            // TODO: положить в in
        }

        public int dequeue() {
            // TODO: если out пуст — перелить всё из in в out; снять с вершины out
            return 0;
        }
    }

    public static void main(String[] args) {
        QueueViaTwoStacks q = new QueueViaTwoStacks();
        // TODO: воспроизведите сценарий из примера
    }
}
