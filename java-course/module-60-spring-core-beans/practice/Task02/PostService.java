import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

// TODO: какую аннотацию ставим на бизнес-логику? (@Service)
class PostService {
    public String getPost(long id) {
        return "Сервис: пост " + id;  // заглушка
    }
}
