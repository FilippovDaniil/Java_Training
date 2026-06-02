/**
 * Задача 07 — Модуль 60: Мини-проект «Блог на компонентах»
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: org.springframework:spring-context:6.1.x (см. theory.md)
 *
 * ЗАДАНИЕ:
 *   Соберите минимальный бэкенд блога с тремя Spring-бинами, связанными
 *   через конструкторную инъекцию.
 *
 *   Архитектура:
 *
 *     BlogApp (main)
 *        └─ PostController (@Controller, конструктор ← PostService)
 *               └─ PostService (@Service, конструктор ← PostRepository + CommentService)
 *                      ├─ PostRepository (@Repository)
 *                      └─ CommentService (@Service, конструктор ← CommentRepository)
 *                               └─ CommentRepository (@Repository)
 *
 *   Шаги:
 *     1) Расставьте стереотипные аннотации на все пять классов.
 *     2) Реализуйте конструкторную инъекцию во всех зависимостях (поля final).
 *     3) Реализуйте методы согласно TODO-меткам.
 *     4) В main поднимите контекст (@ComponentScan), получите PostController,
 *        вызовите controller.createPost("Введение в Spring")
 *        и controller.addComment(1L, "Отличная статья!").
 *     5) Убедитесь, что вывод выглядит примерно так:
 *
 *          [PostController] Создание поста: Введение в Spring
 *          [PostService]    Сохранение поста #1: Введение в Spring
 *          [PostController] Добавление комментария к посту 1: Отличная статья!
 *          [CommentService] Комментарий сохранён для поста 1: Отличная статья!
 *
 * ПОДСКАЗКА:
 *   Порядок при конструкторной инъекции нескольких зависимостей:
 *
 *     @Service
 *     public class PostService {
 *         private final PostRepository postRepository;
 *         private final CommentService commentService;
 *
 *         public PostService(PostRepository postRepository, CommentService commentService) {
 *             this.postRepository = postRepository;
 *             this.commentService = commentService;
 *         }
 *     }
 *
 *   Начните с «нижних» бинов (CommentRepository, PostRepository),
 *   они не зависят ни от кого — Spring создаёт их первыми.
 */
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

public class Task07 {

    public static void main(String[] args) {
        // TODO: создайте контекст с BlogAppConfig.class
        // TODO: получите бин PostController
        // TODO: вызовите controller.createPost("Введение в Spring")
        // TODO: вызовите controller.addComment(1L, "Отличная статья!")
        // TODO: закройте контекст
    }
}

// ============================================================
// Конфигурация
// ============================================================

// TODO: @Configuration
// TODO: @ComponentScan(basePackages = "blog")
class BlogAppConfig { }

// ============================================================
// CommentRepository — нижний слой (ни от кого не зависит)
// ============================================================

// TODO: @Repository
class CommentRepository {
    public void save(long postId, String text) {
        // Заглушка сохранения в "БД"
        System.out.println("[CommentRepository] Сохранён комментарий для поста " + postId + ": " + text);
    }
}

// ============================================================
// PostRepository — нижний слой (ни от кого не зависит)
// ============================================================

// TODO: @Repository
class PostRepository07 {
    private long idCounter = 0;

    public long save(String title) {
        idCounter++;
        System.out.println("[PostRepository] Сохранён пост #" + idCounter + ": " + title);
        return idCounter;
    }
}

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
