package m122_kafka_serialization_contracts.practice.task07;

import java.time.Instant;

/**
 * Payload версии 2 — совместимое расширение v1.
 *
 * TODO 1: поля v1 без изменений (taskId, title, authorId).
 * TODO 2: + String priority с дефолтом "NORMAL" (компактный конструктор), + Instant deadline (nullable).
 * TODO 3: пометьте в комментарии, какие поля появились в v2 — это то, что читают ревьюеры
 *         контракта при выпуске.
 */
public record TaskCreatedV207(
        long taskId,
        String title,
        long authorId,
        String priority,      // новое в v2, дефолт "NORMAL"
        Instant deadline      // новое в v2, nullable
) {
    public TaskCreatedV207 {
        if (priority == null) priority = "NORMAL";
    }
}
