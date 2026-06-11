package m59_spring_core_intro.practice.task06;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.Map;

// ============================================================
// Доменные классы (готовы — не менять)
// ============================================================
interface ProductRepository {
    java.util.List<String> findAll();
}
