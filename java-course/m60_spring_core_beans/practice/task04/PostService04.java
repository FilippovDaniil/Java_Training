package m60_spring_core_beans.practice.task04;

import org.springframework.beans.factory.annotation.Autowired;
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
    private final PostRepository04 repository;

    // TODO: добавьте конструктор, принимающий PostRepository04
    //       (присвойте this.postRepository = postRepository)
    @Autowired
    public PostService04(PostRepository04 repository){
        this.repository = repository;
    }

    public String getPost(long id) {
        // TODO: верните postRepository.findById(id)
        return repository.findById(id);
    }

    // Преимущества конструкторной инъекции (заполните комментарий):
    // 1) поле может быть final — ...Да может быть final и поэтому не изменится
    // 2) зависимость обязательна — ...Да
    // 3) unit-тест без Spring — ...Да может работать, так как мы внедряем зависимость
    // 4) цикл зависимостей виден сразу при старте — ...Да.
}
