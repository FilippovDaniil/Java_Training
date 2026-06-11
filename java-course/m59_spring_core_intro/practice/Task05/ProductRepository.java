package m59_spring_core_intro.practice.task05;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

// ============================================================
// Доменные классы (готовы — не менять)
// ============================================================
interface ProductRepository {
    java.util.List<String> findAll();
    java.util.Map<String, Double> getPriceMap();
}
