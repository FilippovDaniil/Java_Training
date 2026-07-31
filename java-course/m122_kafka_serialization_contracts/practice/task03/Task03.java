package m122_kafka_serialization_contracts.practice.task03;

/**
 * Задача 03 — Модуль 122: эволюция схемы на JSON — обратная и прямая совместимость
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: spring-boot-starter, spring-kafka, jackson-databind. Нужен брокер.
 * Запуск в IDE (▶ main).
 *
 * ФАЙЛЫ ЗАДАЧИ: Task03.java (точка входа), TaskCreatedV103.java (схема v1),
 *               TaskCreatedV203.java (схема v2), CompatibilityListener03.java (два потребителя).
 *
 * ЗАДАНИЕ — воспроизведите обе ситуации совместимости:
 *   1) TaskCreatedV103: taskId, title, authorId.
 *      TaskCreatedV203: те же поля + новое НЕОБЯЗАТЕЛЬНОЕ поле priority (String, дефолт "NORMAL")
 *      + deadline (Instant, nullable).
 *   2) FORWARD (старый код читает новые данные): отправьте событие V2 и прочитайте его
 *      потребителем, ожидающим V1. Чтобы не падать на неизвестных полях, настройте:
 *        spring.jackson.deserialization.fail-on-unknown-properties: false
 *      (или @JsonIgnoreProperties(ignoreUnknown = true) на записи).
 *   3) BACKWARD (новый код читает старые данные): отправьте событие V1 и прочитайте его
 *      потребителем V2 — priority/deadline должны получить дефолт/null, а не сломать разбор.
 *   4) Сломайте совместимость намеренно: переименуйте title -> name в V2, отправьте и прочитайте
 *      потребителем V1. Зафиксируйте, что именно потерялось.
 *
 * ОЖИДАЕМЫЙ ИТОГ:
 *   V2 -> потребитель V1: событие обработано, новые поля проигнорированы (forward-совместимо);
 *   V1 -> потребитель V2: событие обработано, priority = "NORMAL"/null (backward-совместимо);
 *   переименование поля: у потребителя V1 title = null — данные молча потеряны (ЛОМАЮЩЕЕ изменение).
 *
 * ЦЕЛЬ: на практике понять, почему «добавляем поля с дефолтом» можно, а «переименуем поле» — нельзя.
 *
 * ВАЖНО: самое опасное здесь — что переименование НЕ вызывает исключения: потребитель просто
 *   получает null и продолжает работать с испорченными данными. Такие дефекты находят в проде.
 *
 * ПОДСКАЗКА: для новых обязательных по смыслу полей задавайте безопасный дефолт в потребителе,
 *   а не «обязательность» в схеме — иначе выпуск продюсера сломает всех подписчиков сразу.
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Task03 {

    public static void main(String[] args) {
        SpringApplication.run(Task03.class, args);
    }
}
