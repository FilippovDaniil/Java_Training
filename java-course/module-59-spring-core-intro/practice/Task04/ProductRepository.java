import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// ============================================================
// Доменные классы (готовы — не менять)
// ============================================================
interface ProductRepository {
    java.util.List<String> findAll();
    java.util.Map<String, Double> getPriceMap();
}
