package m121_kafka_consumer_spring.practice.task05;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;

/**
 * Генератор нагрузки: наполняет топик событиями с разными ключами.
 *
 * TODO 1: отправить 10 сообщений с ключом "task-42" (значения "42: шаг 1".."42: шаг 10").
 * TODO 2: отправить 10 сообщений с ключами "task-1".."task-10".
 * TODO 3: kafka.flush() в конце.
 *
 * ЗАЧЕМ: один «горячий» ключ + много обычных — типичная продуктовая картина. Так сразу видно,
 *   что горячий ключ обрабатывается строго последовательно (одна партиция = один поток),
 *   и это ограничение пропускной способности по конкретной сущности.
 */
@Configuration
public class LoadGenerator05 {

    @Bean
    ApplicationRunner generate(KafkaTemplate<String, String> kafka) {
        return args -> {
            // 10 сообщений с одним ключом "task-42" — все в одну партицию
            for (int i = 1; i <= 10; i++) {
                kafka.send("task.created", "task-42", "42: шаг " + i);
            }

            // 10 сообщений с разными ключами — распределятся по партициям
            for (int i = 1; i <= 10; i++) {
                kafka.send("task.created", "task-" + i, i + ": обычная задача");
            }

            kafka.flush();   // дождаться отправки всех сообщений
        };
    }
}