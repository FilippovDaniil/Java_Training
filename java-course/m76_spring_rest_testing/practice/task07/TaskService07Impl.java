package m76_spring_rest_testing.practice.task07;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
class TaskService07Impl implements TaskService07 {

    private final Map<Long, TaskDto07> store = new ConcurrentHashMap<>();
    private final AtomicLong seq = new AtomicLong(1);

    // Инициализация с тестовыми данными
    {
        store.put(1L, new TaskDto07(1L, "Купить кофе", "NEW"));
        store.put(2L, new TaskDto07(2L, "Отчёт", "IN_PROGRESS"));
        store.put(3L, new TaskDto07(3L, "Изучить Spring Boot", "DONE"));
    }

    @Override
    public List<TaskDto07> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public TaskDto07 find(Long id) {
        TaskDto07 task = store.get(id);
        if (task == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Задача с id " + id + " не найдена"
            );
        }
        return task;
    }

    @Override
    public TaskDto07 create(String title) {
        if (title == null || title.isBlank()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Заголовок не может быть пустым"
            );
        }

        // Проверка на дубликат
        boolean exists = store.values().stream()
                .anyMatch(task -> task.title().equalsIgnoreCase(title));
        if (exists) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Задача с заголовком '" + title + "' уже существует"
            );
        }

        Long id = seq.getAndIncrement();
        TaskDto07 task = new TaskDto07(id, title, "NEW");
        store.put(id, task);
        return task;
    }

    @Override
    public void delete(Long id) {
        TaskDto07 removed = store.remove(id);
        if (removed == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Задача с id " + id + " не найдена"
            );
        }
    }
}
