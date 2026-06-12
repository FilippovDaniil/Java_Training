package m15_heap_priority_queue.practice.task07;

/**
 * Задача 07 (мини-проект Data-Cruncher) — Тема 15: приоритетная очередь задач
 *
 * ЗАДАНИЕ:
 *   Добавьте в Data-Cruncher TaskScheduler (файл TaskScheduler.java) —
 *   обработку задач по приоритету через кучу. Задачи поступают в произвольном
 *   порядке, но извлекаются от самой срочной (минимальный priority) к менее срочной.
 *
 * ПРИМЕР / ВЫВОД:
 *   submit("backup", 5); submit("alert", 1); submit("report", 3)
 *   обработка по приоритету:
 *     alert (1)
 *     report (3)
 *     backup (5)
 *
 * ТРЕБОВАНИЯ:
 *   - submit/next — O(log n);
 *   - порядок извлечения определяется приоритетом, а не временем поступления;
 *   - меньший priority = более срочная задача.
 *
 * ПОДСКАЗКА:
 *   Это и есть отличие приоритетной очереди от обычной FIFO (тема 04).
 */

public class Task07 {
    public static void main(String[] args) {
        TaskScheduler scheduler = new TaskScheduler();
        scheduler.submit(new TaskScheduler.Job("backup", 5));
        scheduler.submit(new TaskScheduler.Job("alert", 1));
        scheduler.submit(new TaskScheduler.Job("report", 3));
        while (scheduler.hasNext()) {
            TaskScheduler.Job job = scheduler.next();
            System.out.println(job.name() + " (" + job.priority() + ")");
        }
    }
}
