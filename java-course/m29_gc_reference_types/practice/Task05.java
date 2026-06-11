package m29_gc_reference_types.practice;

/**
 * Задача 05 — Модуль 29: PhantomReference и ReferenceQueue
 *
 * ЗАДАНИЕ:
 *   Продемонстрируйте уведомление о сборке объекта.
 *   1. Создайте ReferenceQueue и PhantomReference на объект,
 *      связав их.
 *   2. Уберите strong-ссылку, вызовите System.gc().
 *   3. В цикле проверяйте queue.poll() — когда вернётся не null,
 *      объект собран; выведите "Объект собран сборщиком мусора".
 *
 * ЦЕЛЬ:
 *   Понять механизм пост-обработки: phantom.get() ВСЕГДА null,
 *   но через очередь можно узнать, что объект уничтожен.
 *
 * ПОДСКАЗКА:
 *   ReferenceQueue<Object> q = new ReferenceQueue<>();
 *   PhantomReference<Object> p = new PhantomReference<>(obj, q);
 *   obj = null; System.gc();
 *   Reference<?> ref = q.remove(1000); // ждёт до 1с
 */
import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;

public class Task05 {
    public static void main(String[] args) throws InterruptedException {
        // Создайте PhantomReference + ReferenceQueue, поймайте уведомление о сборке
    }
}
