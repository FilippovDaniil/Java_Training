package m59_spring_core_intro.practice.task03;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

class InMemoryProductRepository implements ProductRepository {
    @Override
    public java.util.List<String> findAll() {
        return java.util.List.of("Ноутбук", "Мышь", "Клавиатура");
    }
}
