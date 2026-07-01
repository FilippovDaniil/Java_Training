package m60_spring_core_beans.practice.task03;

/**
 * Задача 03 — Модуль 60: @Autowired — инъекция в поле (field injection)
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: org.springframework:spring-context:6.1.x (см. theory.md)
 *
 * ЗАДАНИЕ:
 *   Класс PostService должен зависеть от PostRepository.
 *   Реализуйте инъекцию через поле:
 *     1) Добавьте в PostService приватное поле типа PostRepository.
 *     2) Поставьте на это поле @Autowired.
 *     3) В методе getPost(long id) используйте postRepository.findById(id).
 *     4) Поднимите контекст, вызовите postService.getPost(1L), выведите результат.
 *
 *   После выполнения ответьте (в комментарии) на вопрос:
 *     Почему field injection затрудняет unit-тестирование без Spring?
 *
 * ПОДСКАЗКА:
 *   @Autowired
 *   private PostRepository postRepository;
 *
 *   Проблема: поле private, в unit-тесте нельзя передать зависимость
 *   через конструктор — придётся использовать рефлексию или Mockito @InjectMocks.
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

public class Task03 {

    public static void main(String[] args) {
        // TODO: создайте контекст с FieldInjectionConfig.class
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(FieldInjectionConfig.class);
        // TODO: получите бин PostService03
        PostService03 postService03 = ctx.getBean(PostService03.class);
        // TODO: вызовите getPost(1L) и выведите результат
        System.out.println(postService03.getPost(77L));
        // TODO: закройте контекст
        ctx.close();
    }
}
