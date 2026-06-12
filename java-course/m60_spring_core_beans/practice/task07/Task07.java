package m60_spring_core_beans.practice.task07;

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
