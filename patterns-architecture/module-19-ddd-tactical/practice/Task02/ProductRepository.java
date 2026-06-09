import java.util.List;

interface ProductRepository {
    void add(Product product);
    Product findById(String id);
    List<Product> all();
}
