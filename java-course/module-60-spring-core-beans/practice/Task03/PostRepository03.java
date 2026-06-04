import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

// ============================================================
// Репозиторий — готов, не изменяйте
// ============================================================

@Repository
class PostRepository03 {
    public String findById(long id) {
        return "Пост #" + id + " (из репозитория)";
    }
}
