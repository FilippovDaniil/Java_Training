package m122_kafka_serialization_contracts.practice.task02;

/**
 * Задача 02 — Модуль 122: заголовки сообщения (ProducerRecord.headers, @Header)
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: spring-boot-starter, spring-kafka. Нужен брокер. Запуск в IDE (▶ main).
 *
 * ФАЙЛЫ ЗАДАЧИ: Task02.java (точка входа), HeaderPublisher02.java (отправка с заголовками),
 *               HeaderListener02.java (чтение заголовков).
 *
 * ЗАДАНИЕ:
 *   1) HeaderPublisher02: собрать ProducerRecord и добавить заголовки:
 *        eventId   = UUID.randomUUID().toString()
 *        eventType = "task.created"
 *        version   = "1"
 *        traceId   = произвольный
 *      Отправить через kafka.send(record).
 *   2) HeaderListener02: принять значение + заголовки через @Header и напечатать их.
 *   3) Посмотреть заголовки со стороны CLI:
 *        kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic task.created \
 *            --from-beginning --property print.headers=true --property print.key=true
 *   4) Ответьте в комментарии: какие данные правильно держать в заголовках, а какие — в теле.
 *
 * ОЖИДАЕМЫЙ ВЫВОД:
 *   получено eventId=1f0c... eventType=task.created version=1 traceId=trace-7 value={...}
 *
 * ЦЕЛЬ: научиться передавать метаинформацию рядом с данными — так посредники (роутеры, DLQ,
 *       трассировка) работают, не разбирая тело сообщения.
 *
 * ВАЖНО: заголовок Kafka — это байты (byte[]), кодировку задаёте вы. Договоритесь на UTF-8
 *   и одинаковые имена заголовков во всех сервисах, иначе потребитель прочитает мусор.
 *
 * ПОДСКАЗКА: если заголовок может отсутствовать, объявляйте параметр как
 *   @Header(name = "traceId", required = false) String traceId — иначе слушатель упадёт
 *   на сообщении без этого заголовка.
 */

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Task02 {

    public static void main(String[] args) {
        SpringApplication.run(Task02.class, args);
    }

    @Bean
    ApplicationRunner runner(HeaderPublisher02 publisher) {
        return args -> {
            // TODO: отправить два события с заголовками (разные eventId, один traceId)
        };
    }
}
