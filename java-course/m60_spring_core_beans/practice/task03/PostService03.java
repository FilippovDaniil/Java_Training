package m60_spring_core_beans.practice.task03;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

// ============================================================
// Сервис — добавьте инъекцию в поле
// ============================================================

@Service
class PostService03 {

    // TODO: добавьте поле PostRepository03 postRepository с аннотацией @Autowired
    // private PostRepository03 postRepository;

    public String getPost(long id) {
        // TODO: используйте postRepository.findById(id)
        return null;
    }

    // Вопрос (ответьте в комментарии):
    // Почему этот класс трудно тестировать без Spring?
    // TODO: ваш ответ здесь
}
