/**
 * Задача 04 — Модуль 60: Конструкторная инъекция (constructor injection)
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: org.springframework:spring-context:6.1.x (см. theory.md)
 *
 * ЗАДАНИЕ:
 *   Переработайте PostService так, чтобы зависимость PostRepository
 *   передавалась через конструктор:
 *     1) Объявите поле  private final PostRepository04 postRepository.
 *        (Заметьте: теперь поле final — значит зависимость обязательна.)
 *     2) Добавьте конструктор, принимающий PostRepository04.
 *        (Аннотация @Autowired не нужна — единственный конструктор Spring находит сам.)
 *     3) Реализуйте getPost(long id) через postRepository.findById(id).
 *     4) В main поднимите контекст и проверьте работу сервиса.
 *
 *   После реализации ответьте (в комментарии):
 *     Почему конструкторная инъекция лучше field injection
 *     с точки зрения тестируемости?
 *
 * ПОДСКАЗКА:
 *   @Service
 *   public class PostService04 {
 *       private final PostRepository04 postRepository;
 *
 *       public PostService04(PostRepository04 postRepository) {
 *           this.postRepository = postRepository;
 *       }
 *   }
 *
 *   В unit-тесте (без Spring):
 *       PostRepository04 mockRepo = new PostRepository04();
 *       PostService04 service = new PostService04(mockRepo);  // никакой рефлексии!
 */
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

public class Task04 {

    public static void main(String[] args) {
        // TODO: создайте контекст с ConstructorInjectionConfig.class
        // TODO: получите бин PostService04
        // TODO: вызовите getPost(42L) и выведите результат
        // TODO: закройте контекст
    }
}

@Configuration
@ComponentScan(basePackages = "blog")
class ConstructorInjectionConfig { }

// ============================================================
// Репозиторий — готов
// ============================================================

@Repository
class PostRepository04 {
    public String findById(long id) {
        return "Пост #" + id + " (constructor injection)";
    }
}

// ============================================================
// Сервис — реализуйте конструкторную инъекцию
// ============================================================

@Service
class PostService04 {

    // TODO: объявите поле  private final PostRepository04 postRepository

    // TODO: добавьте конструктор, принимающий PostRepository04
    //       (присвойте this.postRepository = postRepository)

    public String getPost(long id) {
        // TODO: верните postRepository.findById(id)
        return null;
    }

    // Преимущества конструкторной инъекции (заполните комментарий):
    // 1) поле может быть final — ...
    // 2) зависимость обязательна — ...
    // 3) unit-тест без Spring — ...
    // 4) цикл зависимостей виден сразу при старте — ...
}
