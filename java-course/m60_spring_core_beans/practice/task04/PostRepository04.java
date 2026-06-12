package m60_spring_core_beans.practice.task04;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

// ============================================================
// Репозиторий — готов
// ============================================================

@Repository
class PostRepository04 {
    public String findById(long id) {
        return "Пост #" + id + " (constructor injection)";
    }
}
