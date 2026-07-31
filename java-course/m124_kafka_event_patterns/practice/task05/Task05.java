package m124_kafka_event_patterns.practice.task05;

/**
 * Задача 05 — Модуль 124: compacted-топик как «таблица состояний» (снимок + tombstone)
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: spring-boot-starter, spring-kafka, jackson-databind. Нужен брокер.
 * Запуск в IDE (▶ main).
 *
 * ФАЙЛЫ ЗАДАЧИ: Task05.java (точка входа), StatePublisher05.java (публикация состояния),
 *               StateBootstrapListener05.java (загрузка снимка при старте).
 *
 * ЗАДАНИЕ:
 *   1) Создайте compacted-топик (NewTopic бином или CLI):
 *        task.state, 3 партиции, cleanup.policy=compact, min.cleanable.dirty.ratio=0.1
 *   2) StatePublisher05:
 *        - publishState(taskId, stateJson) — send("task.state", ключ = taskId, значение = состояние);
 *        - deleteState(taskId) — send("task.state", ключ = taskId, значение = null) — TOMBSTONE.
 *   3) Опубликуйте историю по одной задаче: NEW -> IN_PROGRESS -> DONE (один ключ, три записи).
 *   4) StateBootstrapListener05 (группа "state-bootstrap", auto-offset-reset = earliest):
 *        - читать топик с начала и складывать состояние в Map<String, String>;
 *        - на tombstone (значение null) — удалять ключ из Map;
 *        - печатать итоговое состояние после прочтения.
 *   5) Наблюдение: сравните, что видит консьюмер СРАЗУ после публикации трёх записей и что —
 *      после срабатывания compaction (можно ускорить: segment.ms=1000, потом подождать
 *      и перечитать топик с начала). Зафиксируйте вывод.
 *
 * ОЖИДАЕМЫЙ ИТОГ:
 *   до compaction:  три записи по ключу task-42 (вся история);
 *   после:          одна — последняя (DONE); после tombstone — ключ исчезает из снимка.
 *
 * ЦЕЛЬ: научиться использовать топик как переносимый «снимок состояния», который новый сервис
 *       читает целиком при старте (bootstrap) — без запросов к чужой БД.
 *
 * ВАЖНО: compaction — НЕ способ «удалить старое» (для этого retention.ms) и НЕ мгновенная
 *   операция: брокер уплотняет сегменты по мере их заполнения. До уплотнения потребитель
 *   увидит всю историю по ключу — и это нормально, если он применяет записи идемпотентно.
 *
 * ПОДСКАЗКА: tombstone обязателен для удаления: без него ключ останется в снимке навсегда.
 *   Держать «удалённые» сущности флагом isDeleted в значении — тоже вариант, но тогда
 *   compacted-топик никогда не уменьшается.
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Task05 {

    public static void main(String[] args) {
        SpringApplication.run(Task05.class, args);
    }
}
