package m60_spring_core_beans.practice.task07;

import org.springframework.beans.factory.annotation.Autowired;
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
@Service
class PostService07 {

    // TODO: поля  private final PostRepository07 postRepository
    //             private final CommentService commentService
    private final CommentService commentService;
    private final PostRepository07 postRepository;

    // TODO: конструктор с PostRepository07 и CommentService
    @Autowired
    public PostService07(CommentService commentService, PostRepository07 postRepository) {
        this.commentService = commentService;
        this.postRepository = postRepository;
    }

    public long createPost(String title) {
        System.out.println("[PostService] Сохранение поста: " + title);
        // TODO: вызовите postRepository.save(title) и верните id
        return postRepository.save(title);
    }

    public void addComment(long postId, String text) {
        // TODO: вызовите commentService.addComment(postId, text)
        commentService.addComment(postId, text);
    }
}
