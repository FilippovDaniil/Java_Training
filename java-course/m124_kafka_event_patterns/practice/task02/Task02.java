package m124_kafka_event_patterns.practice.task02;

/**
 * Задача 02 — Модуль 124: релэй outbox — отправка накопленных событий в Kafka
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: spring-boot-starter-data-jpa, h2, spring-kafka, jackson-databind.
 * Нужен брокер. Запуск в IDE (▶ main).
 *
 * ФАЙЛЫ ЗАДАЧИ: Task02.java (точка входа), OutboxMessage02.java, OutboxRepository02.java,
 *               OutboxRelay02.java (планировщик отправки).
 *
 * ЗАДАНИЕ:
 *   1) Включите планировщик: @EnableScheduling на классе Task02.
 *   2) OutboxRelay02.publishBatch() с @Scheduled(fixedDelay = 500):
 *        - выбрать до 100 неотправленных записей в порядке occurredAt;
 *        - для каждой: kafka.send(topic, aggregateId, payload);
 *        - при успехе — markSent(now()); при ошибке — incrementAttempts() и оставить sentAt = null;
 *        - логировать, сколько отправлено/сбойных за проход.
 *   3) Добавьте защиту от «застрявших» записей: если attempts > 10 — писать WARN
 *      (в проде такие записи уводят в отдельную таблицу/алерт).
 *   4) Эксперимент A: остановите брокер, создайте задачу (REST из задачи 01) — записи копятся
 *      в outbox, приложение продолжает отвечать 201. Поднимите брокер — релэй догоняет сам.
 *   5) Эксперимент B: запустите ДВЕ копии приложения на разных портах и проверьте, что события
 *      не отправляются дважды (для PostgreSQL — через FOR UPDATE SKIP LOCKED; опишите,
 *      что произойдёт без блокировки).
 *
 * ОЖИДАЕМЫЙ ВЫВОД:
 *   релэй: отправлено 3, сбоев 0, осталось 0
 *   (при выключенном брокере) релэй: отправлено 0, сбоев 3, осталось 3
 *
 * ЦЕЛЬ: получить работающий транспорт «БД -> Kafka», устойчивый к недоступности брокера.
 *
 * ВАЖНО: релэй даёт at-least-once — он может отправить событие повторно (упал между send и
 *   markSent). Это нормально и не требует «доработки»: дедупликация выполняется на стороне
 *   потребителя по eventId (модуль 123). Пытаться сделать релэй «exactly-once» — тупиковый путь.
 *
 * ПОДСКАЗКА: fixedDelay 500 мс — компромисс между задержкой публикации и нагрузкой на БД.
 *   Альтернатива опросу — CDC (Debezium читает WAL), тогда релэй не нужен вовсе (задача 05
 *   модуля 126 — эксплуатация; конфигурация Debezium — в задаче 03 этого модуля).
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Task02 {

    public static void main(String[] args) {
        SpringApplication.run(Task02.class, args);
    }
}
