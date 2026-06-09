/**
 * Задача 06 — Тема 11: Observer + Chain of Responsibility вместе
 *
 * ЗАДАНИЕ:
 *   Источник событий рассылает их подписчикам (Observer), а один из подписчиков
 *   пропускает событие через цепочку обработчиков (CoR) по уровню важности.
 *     - интерфейс Observer (файл Observer.java): void onEvent(int level, String msg);
 *     - EventSource (файл EventSource.java) — субъект: subscribe(Observer),
 *       fire(int level, String msg) уведомляет всех;
 *     - абстрактный Handler (файл Handler.java): setNext, handle(int level, String msg),
 *       passToNext(...);
 *     - InfoHandler (level == 1 → "INFO: msg") и ErrorHandler (level >= 2 →
 *       "ERROR: msg");
 *     - ChainObserver (файл ChainObserver.java) implements Observer: хранит
 *       голову цепочки и в onEvent делегирует head.handle(level, msg).
 *   В main: EventSource, подпишите ChainObserver с цепочкой Info → Error,
 *   выпустите события уровней 1 и 2.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   INFO: запуск
 *   ERROR: сбой
 *
 * ТРЕБОВАНИЯ:
 *   - Observer: события рассылаются подписчикам субъектом;
 *   - CoR: подписчик-обработчик находит подходящий обработчик по уровню;
 *   - связка: onEvent передаёт событие в голову цепочки.
 *
 * ПОДСКАЗКА:
 *   Observer отвечает «кому доставить событие», CoR — «кто его обработает».
 *   В распределённом виде это станет Kafka + обработчики (тема 24).
 */

public class Task06 {
    public static void main(String[] args) {
        // TODO: EventSource; ChainObserver с цепочкой Info→Error; subscribe; fire(1,...), fire(2,...)
    }
}
