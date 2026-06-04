import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

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
