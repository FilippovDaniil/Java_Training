package m59_spring_core_intro.practice.task04;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;

class InMemoryProductRepository implements ProductRepository {
    @Override
    public List<String> findAll() {
        return List.of("Ноутбук", "Мышь", "Клавиатура");
    }

    @Override
    public Map<String, Double> getPriceMap() {
        return Map.of(
                "Ноутбук", 75000.0,
                "Мышь", 1500.0,
                "Клавиатура", 3500.0
        );
    }
}
