package m102_spring_test_unit.practice.task02;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

class TaskService02 {
    private final TaskRepository02 repo;
    TaskService02(TaskRepository02 repo) { this.repo = repo; }
    String title(Long id) {
        return repo.findById(id).map(Task02::title).orElse("(нет)");
    }
}
