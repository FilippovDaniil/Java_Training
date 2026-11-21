package m120_kafka_producer_spring.practice.task06;

/**
 * Задача 06 — Модуль 120: ручная конфигурация ProducerFactory (acks, идемпотентность, батчинг)
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: spring-boot-starter, spring-kafka. Нужен брокер.
 * Запуск в IDE (▶ main).
 *
 * ФАЙЛЫ ЗАДАЧИ: Task06.java (точка входа), KafkaProducerConfig06.java (два ProducerFactory),
 *               TaskCreated06.java (событие).
 *
 * ЗАДАНИЕ:
 *   Соберите продюсеров вручную (без spring.kafka.producer.*), чтобы увидеть настройки явно:
 *     1) reliableTemplate — «не потеряем»:
 *          acks=all, enable.idempotence=true, max.in.flight.requests.per.connection=5,
 *          delivery.timeout.ms=120000, compression.type=lz4, linger.ms=10;
 *     2) fastTemplate — «максимум скорости, потеря допустима»:
 *          acks=0, enable.idempotence=false, linger.ms=0;
 *     3) в runner отправьте одно и то же событие двумя шаблонами и сравните:
 *          - что возвращает RecordMetadata при acks=0 (какой offset?);
 *          - какой шаблон вы выберете для события «задача создана» и почему.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   reliable: partition 3 offset 15   (offset реальный — брокер подтвердил запись)
 *   fast:     partition 3 offset -1   (подтверждения не ждали — offset неизвестен)
 *
 * ЦЕЛЬ: понять связку acks/идемпотентность/батчинг и научиться держать несколько
 *       KafkaTemplate с разными гарантиями в одном приложении.
 *
 * ВАЖНО: acks=0 несовместим с идемпотентностью; при acks=0 сбой отправки может остаться
 *   незамеченным — такой продюсер подходит для метрик/телеметрии, но не для бизнес-событий.
 *
 * ПОДСКАЗКА: свойства — константы ProducerConfig (ACKS_CONFIG, ENABLE_IDEMPOTENCE_CONFIG,
 *   LINGER_MS_CONFIG, COMPRESSION_TYPE_CONFIG); фабрика — new DefaultKafkaProducerFactory<>(props).
 */

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;

import java.time.Instant;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class Task06 {

    public static void main(String[] args) {
        SpringApplication.run(Task06.class, args);
    }

    @Bean
    ApplicationRunner runner(KafkaTemplate<String, Object> reliableTemplate,
                             KafkaTemplate<String, Object> fastTemplate) {
        return args -> {
            TaskCreated06 event = new TaskCreated06(1L, "Написать отчёт", Instant.now());

            // 1) надёжная отправка: ждём ack, offset реальный
            var reliableResult = reliableTemplate
                    .send("task.created", "task-1", event)
                    .get(5, TimeUnit.SECONDS);
            var rmd = reliableResult.getRecordMetadata();
            System.out.printf("reliable: partition %d offset %d%n",
                    rmd.partition(), rmd.offset());

            // 2) быстрая отправка: acks=0, offset неизвестен (-1)
            var fastResult = fastTemplate
                    .send("task.created", "task-1", event)
                    .get(5, TimeUnit.SECONDS);
            var fmd = fastResult.getRecordMetadata();
            System.out.printf("fast:     partition %d offset %d%n",
                    fmd.partition(), fmd.offset());

            // ВЫВОД:
            // - reliable: offset >= 0 — брокер подтвердил запись (acks=all + идемпотентность).
            //   Такой шаблон нужен для бизнес-событий: «задача создана», «платёж прошёл» и т.п.
            // - fast: offset = -1 — подтверждения не ждали (acks=0). Сообщение может быть
            //   потеряно при сбое брокера/сети, дубли возможны при ретраях.
            //   Подходит для метрик, телеметрии, логов — там потеря допустима.
        };
    }
}