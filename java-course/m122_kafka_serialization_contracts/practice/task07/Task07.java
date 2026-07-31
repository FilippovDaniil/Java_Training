package m122_kafka_serialization_contracts.practice.task07;

/**
 * Задача 07 — Модуль 122: МИНИ-ПРОЕКТ «Контракт событий Task Tracker: v1 → v2 без простоя»
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: spring-boot-starter, spring-kafka, jackson-databind. Нужен брокер.
 * Запуск в IDE (▶ main).
 *
 * ФАЙЛЫ ЗАДАЧИ:
 *   Task07.java              — точка входа
 *   EventEnvelope07.java     — конверт события (eventId, type, version, occurredAt, traceId, source)
 *   TaskCreatedV107.java     — payload v1
 *   TaskCreatedV207.java     — payload v2 (совместимое расширение)
 *   ContractPublisher07.java — публикация: v1, v2 и режим двойной публикации
 *   ContractListener07.java  — потребители v1 и v2
 *   ContractDoc07.java       — носитель документации контракта (AsyncAPI-подобная спека)
 *
 * ЦЕЛЬ: пройти полный цикл жизни контракта: описать v1, выпустить совместимую v2, организовать
 *       миграцию подписчиков без остановки системы и задокументировать результат. Капстоун модуля.
 *
 * ЗАДАНИЕ:
 *   1) EventEnvelope07 — конверт (как в задаче 01), плюс правило: version соответствует
 *      версии payload.
 *   2) TaskCreatedV107: taskId, title, authorId.
 *      TaskCreatedV207: то же + priority (default "NORMAL") + deadline (nullable) — СОВМЕСТИМО.
 *   3) ContractPublisher07:
 *        - publishV1(...) — топик "task.created", version = 1;
 *        - publishV2(...) — топик "task.created", version = 2 (тот же топик: изменение совместимое);
 *        - publishBreakingV2(...) — топик "task.created.v2" для ЛОМАЮЩЕГО изменения
 *          (например, taskId стал строкой) + одновременная публикация в "task.created"
 *          для не переехавших подписчиков (двойная публикация).
 *   4) ContractListener07:
 *        - слушатель группы "legacy" читает "task.created" как v1 (должен работать и на v2);
 *        - слушатель группы "modern" читает "task.created" как v2;
 *        - слушатель группы "modern-v2-topic" читает "task.created.v2".
 *   5) ContractDoc07 — заполните спецификацию контракта и чек-лист миграции.
 *
 * ПРОВЕРКА:
 *   - publishV2 -> слушатель "legacy" обработал событие без ошибок (новые поля проигнорированы);
 *   - publishV1 -> слушатель "modern" получил priority = "NORMAL" (дефолт), не упал;
 *   - publishBreakingV2 -> событие пришло в оба топика; после «переезда» всех подписчиков
 *     публикация в "task.created" отключается одной правкой в ContractPublisher07;
 *   - kafka-ui: в task.created видны сообщения обеих версий (различаются полем version).
 *
 * ОЖИДАЕМЫЙ ИТОГ: рабочая схема эволюции контракта, которую можно повторить на любом проекте:
 *   совместимые изменения — в тот же топик с ростом version; ломающие — новый топик,
 *   двойная публикация, плановый вывод старой версии.
 *
 * ВАЖНО: двойная публикация — не «навсегда». У неё должен быть срок и владелец: пока она
 *   работает, продюсер обязан поддерживать ДВЕ схемы, а это двойная стоимость изменений.
 *
 * ПОДСКАЗКА: соедините задачи 01 (конверт), 02 (заголовки: eventId/version дублируем в headers
 *   для маршрутизации без разбора тела), 03 (совместимость), 06 (правила выпуска).
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Task07 {

    public static void main(String[] args) {
        SpringApplication.run(Task07.class, args);
    }
}
