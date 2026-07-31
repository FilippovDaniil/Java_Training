package m125_kafka_testing.practice.task03;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Тестовое приложение для @SpringBootTest(classes = Task03.class).
 *
 * Минимальный контекст: только то, что нужно тесту (слушатель + автоконфигурация Kafka).
 * Так интеграционный тест не тащит за собой всё приложение и стартует быстро.
 */
@SpringBootApplication
public class Task03 {

    public static void main(String[] args) {
        SpringApplication.run(Task03.class, args);
    }
}
