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

@SpringBootApplication
public class Task06 {

    public static void main(String[] args) {
        SpringApplication.run(Task06.class, args);
    }

    @Bean
    ApplicationRunner runner(KafkaTemplate<String, Object> reliableTemplate,
                             KafkaTemplate<String, Object> fastTemplate) {
        return args -> {
            // TODO 1: отправить TaskCreated06 через reliableTemplate, напечатать partition/offset
            // TODO 2: отправить то же событие через fastTemplate, напечатать partition/offset
            // TODO 3: вывод — чем отличается результат и какой шаблон нужен бизнес-событию
        };
    }
}
