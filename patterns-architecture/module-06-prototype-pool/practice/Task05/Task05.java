/**
 * Задача 05 — Тема 06: Object Pool (базовый)
 *
 * ЗАДАНИЕ:
 *   Реализуйте пул переиспользуемых «соединений»:
 *     - класс PooledConnection (файл PooledConnection.java): поле id (int),
 *       конструктор печатает "создано соединение #id" (чтобы видеть создание);
 *     - ConnectionPool (файл ConnectionPool.java):
 *         PooledConnection acquire() — отдаёт свободное из пула, а если их нет,
 *           создаёт новое;
 *         void release(PooledConnection c) — возвращает соединение в пул.
 *   В main: acquire два соединения, верните одно через release, затем снова
 *   acquire — должно ПЕРЕИСПОЛЬЗОВАТЬСЯ возвращённое (новое не создаётся).
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   создано соединение #1
 *   создано соединение #2
 *   (release #1)
 *   повторный acquire вернул #1 (переиспользование)
 *
 * ТРЕБОВАНИЯ:
 *   - если в пуле есть свободное соединение, acquire НЕ создаёт новое;
 *   - released-соединение снова доступно для acquire;
 *   - храните свободные соединения в очереди/стеке (например, ArrayDeque).
 *
 * ПОДСКАЗКА:
 *   acquire: free.isEmpty() ? new PooledConnection(nextId++) : free.poll();
 *   release: free.offer(c);
 */

public class Task05 {
    public static void main(String[] args) {
        // TODO: продемонстрируйте acquire/release и переиспользование соединения
    }
}
