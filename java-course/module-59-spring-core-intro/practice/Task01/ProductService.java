/** Сервисный слой — зависит от ProductRepository через конструктор. */
class ProductService {
    private final ProductRepository repository;

    // Конструкторный DI: зависимость передаётся снаружи
    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public void listAll() {
        System.out.println("=== Список товаров ===");
        repository.findAll().forEach(System.out::println);
    }
}
