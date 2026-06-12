package m64_spring_boot_intro.practice.task07;

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
// REST-контроллер (каркас)
// ============================================================

// TODO: добавьте @RestController
class ProductController07 {

    // TODO: внедрите ProductService07 через конструктор

    // TODO: @GetMapping("/products")
    public List<String> all() {
        return null;
    }

    // TODO: @GetMapping("/products/count")
    public int count() {
        return 0;
    }

    // TODO: @PostMapping("/products")
    public String add(/* TODO: @RequestParam */ String name) {
        // TODO: добавьте товар и верните его имя
        return null;
    }
}
