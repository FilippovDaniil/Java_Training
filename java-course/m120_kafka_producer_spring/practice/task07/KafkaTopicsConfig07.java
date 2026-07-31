package m120_kafka_producer_spring.practice.task07;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Топики сквозного проекта.
 *
 * TODO 1: NewTopic taskCreated       — "task.created",        6 партиций, 1 реплика.
 * TODO 2: NewTopic taskStatusChanged — "task.status.changed",  6 партиций, 1 реплика.
 *
 * ПОДСКАЗКА: TopicBuilder.name(...).partitions(6).replicas(1).build();
 *   имена топиков держите константами (одно место правки) — они часть публичного контракта.
 */
@Configuration
public class KafkaTopicsConfig07 {

    public static final String TASK_CREATED = "task.created";
    public static final String TASK_STATUS_CHANGED = "task.status.changed";

    @Bean
    NewTopic taskCreated() {
        // TODO 1
        return null;
    }

    @Bean
    NewTopic taskStatusChanged() {
        // TODO 2
        return null;
    }
}
