package m60_spring_core_beans.practice.task02;

/**
 * Задача 02 — Модуль 60: Стереотипы @Service / @Repository / @Controller
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: org.springframework:spring-context:6.1.x (см. theory.md)
 *
 * ЗАДАНИЕ:
 *   Перед вами каркасы трёх классов — слои приложения «Блог».
 *   Расставьте правильные стереотипные аннотации:
 *     1) PostRepository — слой доступа к данным → @Repository
 *     2) PostService    — бизнес-логика         → @Service
 *     3) PostController — веб-слой              → @Controller
 *   Все три — специализации @Component; Spring зарегистрирует их как бины.
 *
 *   После расстановки аннотаций:
 *     4) Создайте @Configuration + @ComponentScan(basePackages = "blog") класс BlogConfig.
 *     5) В main поднимите контекст и получите все три бина.
 *     6) Для каждого выведите: getClass().getSimpleName() + " зарегистрирован".
 *
 * ПОДСКАЗКА:
 *   ctx.getBean(PostService.class) // получить бин по типу
 *   Обратите внимание: @Repository дополнительно включает трансляцию
 *   исключений доступа к данным в Spring DataAccessException.
 */

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

public class Task02 {

    public static void main(String[] args) {
        // TODO: создайте контекст с BlogConfig.class
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(BlogConfig.class);
        // TODO: получите бины PostRepository, PostService, PostController
        PostController controller = ctx.getBean(PostController.class);
        PostRepository repository = ctx.getBean(PostRepository.class);
        PostService service = ctx.getBean(PostService.class);
        // TODO: для каждого выведите getClass().getSimpleName() + " зарегистрирован"
        System.out.println("Controller: " + controller.getClass().getSimpleName());
        System.out.println(controller.handle("Swag"));
        System.out.println("Repository: " + repository.getClass().getSimpleName());
        System.out.println(repository.findById(1L));
        System.out.println("Service: " + service.getClass().getSimpleName());
        System.out.println(service.getPost(2L));
        // TODO: закройте контекст
        ctx.close();
    }
}
