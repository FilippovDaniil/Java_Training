package m121_kafka_consumer_spring.practice.task02;

/**
 * Задача 02 — Модуль 121: метаданные записи (ConsumerRecord, @Header) и несколько топиков
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: spring-boot-starter, spring-kafka. Нужен брокер.
 * Запуск в IDE (▶ main).
 *
 * ФАЙЛЫ ЗАДАЧИ: Task02.java (точка входа), AuditListener02.java (слушатель).
 *
 * ЗАДАНИЕ:
 *   В AuditListener02 реализуйте два слушателя:
 *     1) onRecord(ConsumerRecord<String,String> rec) — топики task.created и task.status.changed,
 *        группа "audit": печатать topic, partition, offset, key, timestamp, value;
 *     2) onWithHeaders(String value, @Header(KafkaHeaders.RECEIVED_KEY) String key,
 *                      @Header(KafkaHeaders.RECEIVED_PARTITION) int partition) — топик task.created,
 *        группа "audit-headers": печатать key/partition, полученные отдельными параметрами.
 *   Отправьте события с ключами продюсером (модуль 120, задача 04) и сравните вывод.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   [audit] task.created-3 offset 15 key=42 ts=1753960000000 value={"id":42,...}
 *   [audit] task.status.changed-1 offset 4 key=42 ts=... value={...}
 *   [audit-headers] key=42 partition=3
 *
 * ЦЕЛЬ: научиться доставать всё окружение сообщения — это основа диагностики и идемпотентности.
 *
 * ВАЖНО: одна группа на два топика — офсеты хранятся отдельно по каждой паре (топик, партиция).
 *   Два слушателя с ОДИНАКОВЫМ group.id делили бы партиции между собой, поэтому группы разные.
 *
 * ПОДСКАЗКА: константы заголовков — в KafkaHeaders (RECEIVED_KEY, RECEIVED_PARTITION,
 *   RECEIVED_TOPIC, RECEIVED_TIMESTAMP, OFFSET).
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Task02 {

    public static void main(String[] args) {
        SpringApplication.run(Task02.class, args);
    }
}
