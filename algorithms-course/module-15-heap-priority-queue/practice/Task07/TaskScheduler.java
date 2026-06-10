/**
 * Модуль Data-Cruncher: приоритетная очередь обработки задач.
 * Задачи обрабатываются не в порядке поступления (FIFO), а по ПРИОРИТЕТУ:
 * чем меньше число priority, тем срочнее. Внутри — min-heap по приоритету.
 */
import java.util.PriorityQueue;

public class TaskScheduler {

    /** Задача: имя + приоритет (меньше = срочнее). */
    public record Job(String name, int priority) {}

    private final PriorityQueue<Job> queue =
            new PriorityQueue<>(java.util.Comparator.comparingInt(Job::priority));

    /** Поставить задачу в очередь. O(log n). */
    public void submit(Job job) {
        // TODO: добавить в очередь
    }

    /** Взять самую приоритетную задачу (или null, если пусто). O(log n). */
    public Job next() {
        // TODO: извлечь минимум по приоритету
        return null;
    }

    public boolean hasNext() { return !queue.isEmpty(); }
}
