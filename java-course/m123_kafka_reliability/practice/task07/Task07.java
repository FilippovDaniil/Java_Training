package m123_kafka_reliability.practice.task07;

/**
 * Задача 07 — Модуль 123: МИНИ-ПРОЕКТ «Надёжный сервис уведомлений: retry + DLQ + идемпотентность»
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: spring-boot-starter-web, spring-kafka, jackson-databind. Нужен брокер.
 * Запуск в IDE (▶ main).
 *
 * ФАЙЛЫ ЗАДАЧИ:
 *   Task07.java                 — точка входа
 *   EventEnvelope07.java        — конверт (eventId, type, occurredAt, payload)
 *   ProcessedEvents07.java      — реестр обработанных событий (дедупликация)
 *   ReliabilityConfig07.java    — DefaultErrorHandler: backoff, не-retryable, DLQ
 *   NotificationListener07.java — основной слушатель (идемпотентный, ручной ack)
 *   DltAdminController07.java   — REST: статистика DLT и повторная обработка (reprocessing)
 *
 * ЦЕЛЬ: собрать потребителя, который выдерживает реальную эксплуатацию: временные сбои повторяет,
 *       постоянные — отправляет в DLQ, дубли игнорирует, а сбойные события можно переиграть
 *       после исправления причины. Капстоун модуля.
 *
 * ЗАДАНИЕ:
 *   1) ReliabilityConfig07:
 *        - ExponentialBackOffWithMaxRetries(3): 1с, 2с, 4с;
 *        - DeadLetterPublishingRecoverer -> "<топик>.DLT";
 *        - not-retryable: IllegalArgumentException (ошибки контракта/валидации).
 *   2) NotificationListener07 (topics = "task.created", groupId = "notifier", ручной ack):
 *        - валидация конверта: нет eventId/payload -> IllegalArgumentException (сразу DLQ);
 *        - дедупликация по eventId через ProcessedEvents07;
 *        - «отправка уведомления»: при payload с "smtp-down" — RuntimeException (повторяемо);
 *        - ack.acknowledge() ТОЛЬКО после успешной обработки;
 *        - счётчики: обработано / дублей / отказов.
 *   3) DltAdminController07:
 *        - GET /api/dlt/stats — сколько записей ушло в DLT (по счётчику DLT-слушателя);
 *        - POST /api/dlt/reprocess — прочитать записи из "task.created.DLT" и отправить обратно
 *          в "task.created" (переиграть), пометив заголовком reprocessed=true;
 *        - защита от цикла: если у записи уже есть reprocessed=true, второй раз не переигрывать.
 *
 * ПРОВЕРКА (сценарии эксплуатации):
 *   1) обычное событие -> обработано один раз, LAG = 0;
 *   2) то же событие отправлено трижды (один eventId) -> уведомление одно, дублей 2;
 *   3) событие с "smtp-down" -> 4 попытки с паузами -> запись в task.created.DLT;
 *   4) событие с пустым payload -> ОДНА попытка -> сразу в DLT (не-retryable);
 *   5) POST /api/dlt/reprocess после «починки» -> событие обработано;
 *   6) повторный reprocess той же записи -> отклонён (защита от цикла).
 *
 * ОЖИДАЕМЫЙ ИТОГ: сервис, который не теряет события, не дублирует эффекты, не встаёт из-за
 *   одной плохой записи и позволяет переиграть сбойное после исправления причины.
 *
 * ВАЖНО: в продуктовом варианте реестр обработанных событий и «переигранные» записи хранятся
 *   в БД в одной транзакции с эффектом; счётчики экспортируются в Micrometer/Prometheus,
 *   а на непустой DLT ставится алерт (модуль 126).
 *
 * ПОДСКАЗКА: соедините задачи 01-04 модуля: backoff (01), классификация исключений (02),
 *   DLQ и заголовки (03), дедупликация (04). Для чтения DLT по требованию удобен слушатель
 *   с autoStartup = "false", запускаемый из контроллера (registry.getListenerContainer(...).start()).
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Task07 {

    public static void main(String[] args) {
        SpringApplication.run(Task07.class, args);
    }
}
