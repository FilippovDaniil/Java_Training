package m120_kafka_producer_spring.practice;

/**
 * Задача 01 — Модуль 120: первый producer на Spring (KafkaTemplate + application.yml)
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: spring-boot-starter, org.springframework.kafka:spring-kafka.
 * НУЖЕН запущенный брокер (модуль 119, задача 07). Запуск в IDE (▶ main).
 *
 * ЗАДАНИЕ:
 *   1) Опишите настройки продюсера (файл src/main/resources/application.yml или свойства IDE):
 *        spring.kafka.bootstrap-servers: localhost:29092
 *        spring.kafka.producer.key-serializer:   org.apache.kafka.common.serialization.StringSerializer
 *        spring.kafka.producer.value-serializer: org.apache.kafka.common.serialization.StringSerializer
 *   2) В runner(...) отправьте в топик "task.created" три строковых сообщения с ключами:
 *        kafka.send("task.created", "task-1", "{\"id\":1,\"title\":\"Написать отчёт\"}");
 *        ... ещё два с ключами task-2, task-3
 *   3) Выведите в консоль подтверждение по каждой отправке (см. подсказку).
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   отправлено task-1 -> task.created-4 offset 0
 *   отправлено task-2 -> task.created-1 offset 0
 *   отправлено task-3 -> task.created-5 offset 0
 *
 * ПРОВЕРКА: прочитайте топик CLI-консьюмером с --from-beginning --property print.key=true —
 *   должны увидеть все три сообщения.
 *
 * ЦЕЛЬ: убедиться, что Boot сам создаёт KafkaTemplate из свойств, и научиться отправлять запись.
 *
 * ВАЖНО: send() асинхронный. Если main-поток завершится раньше отправки, сообщения могут не уйти —
 *   в учебной задаче дождитесь результата (get с таймаутом) или вызовите kafka.flush().
 *
 * ПОДСКАЗКА: результат — CompletableFuture<SendResult<String,String>>; метаданные лежат в
 *   result.getRecordMetadata(): topic(), partition(), offset().
 */

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;

@SpringBootApplication
public class Task01 {

    public static void main(String[] args) {
        SpringApplication.run(Task01.class, args);
    }

    @Bean
    ApplicationRunner runner(KafkaTemplate<String, String> kafka) {
        return args -> {
            // TODO 1: отправить три сообщения в "task.created" с ключами task-1, task-2, task-3
            // TODO 2: для каждой отправки напечатать topic-partition и offset из RecordMetadata
            // TODO 3: дождаться завершения отправок (get с таймаутом) или kafka.flush()
        };
    }
}
