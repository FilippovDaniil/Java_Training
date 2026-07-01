package m60_spring_core_beans.practice.task07;

import org.springframework.beans.factory.annotation.Autowired;
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
@Service
class CommentService {

    // TODO: поле  private final CommentRepository commentRepository
    private final CommentRepository repository;

    // TODO: конструктор с CommentRepository
    @Autowired
    public CommentService(CommentRepository repository){
        this.repository = repository;
    }

    public void addComment(long postId, String text) {
        System.out.println("[CommentService] Комментарий сохранён для поста " + postId + ": " + text);
        // TODO: вызовите commentRepository.save(postId, text)
        repository.save(postId, text);
    }
}
