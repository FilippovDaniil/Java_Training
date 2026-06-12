package m19_ddd_tactical.practice.task02;

import java.util.List;

interface ProductRepository {
    void add(Product product);
    Product findById(String id);
    List<Product> all();
}
