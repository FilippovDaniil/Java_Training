import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

// ============================================================
// Интерфейс источника данных
// ============================================================
interface DataSource {
    String getUrl();
}
