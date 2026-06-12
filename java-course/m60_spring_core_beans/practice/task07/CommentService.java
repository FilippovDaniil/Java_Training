package m60_spring_core_beans.practice.task07;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

// ============================================================
// CommentService — зависит от CommentRepository
// ============================================================

// TODO: @Service
class CommentService {

    // TODO: поле  private final CommentRepository commentRepository

    // TODO: конструктор с CommentRepository

    public void addComment(long postId, String text) {
        System.out.println("[CommentService] Комментарий сохранён для поста " + postId + ": " + text);
        // TODO: вызовите commentRepository.save(postId, text)
    }
}
