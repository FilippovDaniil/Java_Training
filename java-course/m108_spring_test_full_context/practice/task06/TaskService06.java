package m108_spring_test_full_context.practice.task06;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import static org.assertj.core.api.Assertions.assertThat;

@Service
class TaskService06 {
    private final TaskRepository06 repo;
    TaskService06(TaskRepository06 repo) { this.repo = repo; }
    Long create(String title) { return repo.save(new TaskEntity06(title)).getId(); }
}
