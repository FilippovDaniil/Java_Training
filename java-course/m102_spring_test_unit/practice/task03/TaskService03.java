package m102_spring_test_unit.practice.task03;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class TaskService03 {
    private final TaskRepository03 repo;
    TaskService03(TaskRepository03 repo) { this.repo = repo; }
    Task03 find(Long id) { return repo.findById(id).orElseThrow(); }
    String findOrDefault(Long id) { return repo.findById(id).map(Task03::title).orElse("(нет)"); }
    Task03 create(String title) { return repo.save(new Task03(null, title)); }
}
