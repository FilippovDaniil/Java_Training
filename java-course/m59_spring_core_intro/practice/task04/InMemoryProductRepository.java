package m59_spring_core_intro.practice.task04;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

class InMemoryProductRepository implements ProductRepository {
    @Override
    public java.util.List<String> findAll() {
        return java.util.List.of("Ноутбук", "Мышь", "Клавиатура");
    }

    @Override
    public java.util.Map<String, Double> getPriceMap() {
        return java.util.Map.of("Ноутбук", 75000.0, "Мышь", 1500.0, "Клавиатура", 3500.0);
    }
}
