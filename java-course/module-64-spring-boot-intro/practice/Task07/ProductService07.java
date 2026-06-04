import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;

// ============================================================
// Сервис товаров (каркас)
// ============================================================

// TODO: добавьте @Service
class ProductService07 {
    private final List<String> products = new ArrayList<>();

    public void add(String name) {
        // TODO: добавьте name в products
    }

    public List<String> findAll() {
        // TODO: верните products
        return null;
    }

    public int count() {
        // TODO: верните размер списка
        return 0;
    }
}
