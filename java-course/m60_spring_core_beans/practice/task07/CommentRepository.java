package m60_spring_core_beans.practice.task07;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

// ============================================================
// CommentRepository — нижний слой (ни от кого не зависит)
// ============================================================

// TODO: @Repository
@Repository
class CommentRepository {
    public void save(long postId, String text) {
        // Заглушка сохранения в "БД"
        System.out.println("[CommentRepository] Сохранён комментарий для поста " + postId + ": " + text);
    }
}
