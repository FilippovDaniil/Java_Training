import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

class PriceService {
    private final ProductRepository repository;

    public PriceService(ProductRepository repository) {
        this.repository = repository;
    }

    public ProductRepository getRepository() { return repository; }

    public double getPrice(String name) {
        return repository.getPriceMap().getOrDefault(name, -1.0);
    }
}
