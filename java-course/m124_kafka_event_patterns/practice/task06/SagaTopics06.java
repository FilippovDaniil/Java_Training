package m124_kafka_event_patterns.practice.task06;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

/**
 * Топики процесса — «схема» саги в одном месте.
 *
 * TODO: объявите NewTopic-бины для всех пяти топиков (3 партиции, 1 реплика):
 *   quota.reserve.requested · quota.reserved · quota.rejected
 *   quota.release.requested · task.created
 *
 * ПОДСКАЗКА ПО КЛЮЧУ: ключом всех событий саги делайте sagaId (или authorId, если важен
 *   порядок операций по квоте автора). Так все события одного процесса попадут в одну партицию,
 *   и шаги будут применяться в порядке возникновения.
 */
@Configuration
public class SagaTopics06 {

    public static final String RESERVE_REQUESTED = "quota.reserve.requested";
    public static final String RESERVED = "quota.reserved";
    public static final String REJECTED = "quota.rejected";
    public static final String RELEASE_REQUESTED = "quota.release.requested";
    public static final String TASK_CREATED = "task.created";

    @Bean NewTopic reserveRequested() {
        return TopicBuilder.name(RESERVE_REQUESTED).partitions(3).replicas(1).build();
    }
    @Bean NewTopic reserved() {
        return TopicBuilder.name(RESERVED).partitions(3).replicas(1).build();
    }
    @Bean NewTopic rejected() {
        return TopicBuilder.name(REJECTED).partitions(3).replicas(1).build();
    }
    @Bean NewTopic releaseRequested() {
        return TopicBuilder.name(RELEASE_REQUESTED).partitions(3).replicas(1).build();
    }
    @Bean NewTopic taskCreated() {
        return TopicBuilder.name(TASK_CREATED).partitions(3).replicas(1).build();
    }
}
