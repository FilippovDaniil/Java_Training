package m123_kafka_reliability.practice.task04;

/**
 * Задача 04 — Модуль 123: идемпотентный потребитель (дедупликация по eventId)
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: spring-boot-starter, spring-kafka, jackson-databind. Нужен брокер.
 * Запуск в IDE (▶ main).
 *
 * ФАЙЛЫ ЗАДАЧИ: Task04.java (точка входа), EventEnvelope04.java (конверт с eventId),
 *               ProcessedEvents04.java (реестр обработанных), IdempotentListener04.java (слушатель).
 *
 * ЗАДАНИЕ:
 *   1) EventEnvelope04: eventId (UUID), type, occurredAt, payload (String).
 *   2) ProcessedEvents04.markProcessed(UUID eventId): вернуть true, если событие ещё не
 *      обрабатывалось (и запомнить), false — если это дубль.
 *   3) IdempotentListener04:
 *        - если markProcessed вернул false -> напечатать «дубль, пропускаем» и выйти;
 *        - иначе выполнить «побочный эффект» (печать «письмо отправлено») и посчитать эффекты.
 *   4) Эксперимент: отправьте ОДНО И ТО ЖЕ событие (тот же eventId) три раза
 *      (CLI-продюсером или из runner) — эффект должен произойти РОВНО один раз.
 *   5) Второй эксперимент: отправьте два события с одинаковым payload, но разными eventId —
 *      эффект должен произойти дважды (это разные факты).
 *
 * ОЖИДАЕМЫЙ ВЫВОД:
 *   письмо отправлено для eventId=1f0c...  (эффектов: 1)
 *   дубль eventId=1f0c... — пропускаем
 *   дубль eventId=1f0c... — пропускаем
 *
 * ЦЕЛЬ: сделать обработку безопасной при at-least-once — это обязательное условие продуктовой
 *       эксплуатации Kafka, а не «оптимизация на будущее».
 *
 * ВАЖНО: в реальном сервисе реестр обработанных событий — ТАБЛИЦА В БД, и отметка о обработке
 *   должна попадать в ту же транзакцию, что и бизнес-эффект:
 *     INSERT INTO processed_events(event_id, processed_at) VALUES (?, now()) ON CONFLICT DO NOTHING;
 *   Иначе останется окно «эффект сделан — отметка потеряна» (дубль) или наоборот (потеря).
 *   Таблицу чистят по времени (например, храним 30 дней) — иначе она растёт бесконечно.
 *
 * ПОДСКАЗКА: дедупликация по (topic, partition, offset) выглядит проще, но ломается при
 *   повторной публикации того же факта (миграция топика, двойная публикация из модуля 122).
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Task04 {

    public static void main(String[] args) {
        SpringApplication.run(Task04.class, args);
    }
}
