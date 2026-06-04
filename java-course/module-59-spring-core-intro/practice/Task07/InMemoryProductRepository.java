import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.ArrayList;
import java.util.List;

class InMemoryProductRepository implements ProductRepository {
    private final List<String> catalog = List.of(
            "Ноутбук", "Мышь", "Клавиатура", "Монитор", "Наушники"
    );

    @Override
    public List<String> findAll() {
        return catalog;
    }

    @Override
    public boolean exists(String name) {
        return catalog.contains(name);
    }
}
