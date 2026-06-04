import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

class ProductService {
    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public void listAll() {
        System.out.println("=== Список товаров (из XML-контекста) ===");
        repository.findAll().forEach(System.out::println);
    }
}
