package m60_spring_core_beans.practice.task07;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

// ============================================================
// PostService — зависит от PostRepository07 и CommentService
// ============================================================

// TODO: @Service
class PostService07 {

    // TODO: поля  private final PostRepository07 postRepository
    //             private final CommentService commentService

    // TODO: конструктор с PostRepository07 и CommentService

    public long createPost(String title) {
        System.out.println("[PostService] Сохранение поста: " + title);
        // TODO: вызовите postRepository.save(title) и верните id
        return 0;
    }

    public void addComment(long postId, String text) {
        // TODO: вызовите commentService.addComment(postId, text)
    }
}
