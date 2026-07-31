package m121_kafka_consumer_spring.practice;

/**
 * Задача 01 — Модуль 121: первый @KafkaListener (группа, auto-offset-reset)
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: spring-boot-starter, spring-kafka. Нужен брокер и топик task.created.
 * Запуск в IDE (▶ main) — приложение работает, пока его не остановить.
 *
 * ЗАДАНИЕ:
 *   1) Настройки консьюмера:
 *        spring.kafka.bootstrap-servers: localhost:29092
 *        spring.kafka.consumer.group-id: notifier
 *        spring.kafka.consumer.auto-offset-reset: earliest
 *        spring.kafka.consumer.key-deserializer:   org.apache.kafka.common.serialization.StringDeserializer
 *        spring.kafka.consumer.value-deserializer: org.apache.kafka.common.serialization.StringDeserializer
 *   2) Реализуйте метод onTaskCreated: печатать полученное значение.
 *   3) Запустите приложение — прочитаются все накопленные сообщения (earliest).
 *   4) Не останавливая приложение, отправьте новое сообщение CLI-продюсером — оно появится в консоли.
 *   5) Остановите приложение, отправьте ещё одно сообщение, запустите снова:
 *        сообщение будет прочитано (офсет группы сохранён на брокере).
 *
 * ОЖИДАЕМЫЙ ВЫВОД:
 *   получено: {"id":1,"title":"Написать отчёт"}
 *   получено: {"id":2,"title":"Проверить логи"}
 *
 * ЦЕЛЬ: увидеть, что слушатель — это отдельный поток с poll-циклом, а офсет группы живёт на брокере.
 *
 * ВАЖНО: auto-offset-reset действует ТОЛЬКО когда у группы ещё нет сохранённого офсета.
 *   Если группа notifier уже читала топик, смена earliest/latest ничего не изменит —
 *   нужен kafka-consumer-groups.sh --reset-offsets (модуль 119).
 *
 * ПОДСКАЗКА: проверить состояние группы во время работы:
 *   docker compose exec kafka /opt/kafka/bin/kafka-consumer-groups.sh \
 *       --bootstrap-server localhost:9092 --describe --group notifier
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;

@SpringBootApplication
public class Task01 {

    public static void main(String[] args) {
        SpringApplication.run(Task01.class, args);
    }

    @KafkaListener(topics = "task.created", groupId = "notifier")
    public void onTaskCreated(String value) {
        // TODO: напечатать полученное сообщение
    }
}
