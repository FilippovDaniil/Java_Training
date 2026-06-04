import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

// ============================================================
// PostController — зависит от PostService07
// ============================================================

// TODO: @Controller
class PostController {

    // TODO: поле  private final PostService07 postService

    // TODO: конструктор с PostService07

    public void createPost(String title) {
        System.out.println("[PostController] Создание поста: " + title);
        // TODO: вызовите postService.createPost(title)
    }

    public void addComment(long postId, String text) {
        System.out.println("[PostController] Добавление комментария к посту " + postId + ": " + text);
        // TODO: вызовите postService.addComment(postId, text)
    }
}
