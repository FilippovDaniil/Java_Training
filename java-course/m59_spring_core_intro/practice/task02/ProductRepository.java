package m59_spring_core_intro.practice.task02;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

// ============================================================
// Доменные классы (готовы — не менять)
// ============================================================
interface ProductRepository {
    List<String> findAll();
}
