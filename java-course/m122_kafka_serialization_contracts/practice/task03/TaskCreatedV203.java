package m122_kafka_serialization_contracts.practice.task03;

import java.time.Instant;

/**
 * Схема версии 2 — совместимое расширение v1.
 *
 * TODO 1: повторить поля v1 (taskId, title, authorId) БЕЗ переименований и смены типов.
 * TODO 2: добавить необязательные поля: String priority (дефолт "NORMAL"), Instant deadline (null).
 * TODO 3 (эксперимент по заданию 4): временно переименовать title -> name и увидеть,
 *         что потребитель v1 получит null.
 *
 * ПОДСКАЗКА: дефолт для record удобно задать компактным конструктором:
 *   public TaskCreatedV203 { if (priority == null) priority = "NORMAL"; }
 */
public record TaskCreatedV203(long taskId) {
}
