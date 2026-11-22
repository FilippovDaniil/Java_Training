package m125_kafka_testing.practice.task07;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskProjectionRepository extends JpaRepository<TaskProjection, Long> {}