package m64_spring_boot_intro.practice.task07;

import org.springframework.beans.factory.annotation.Autowired;
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
@RestController
class ProductController07 {

    // TODO: внедрите ProductService07 через конструктор
    private final ProductService07 service;

    @Autowired
    public ProductController07(ProductService07 service) {
        this.service = service;
    }

    // TODO: @GetMapping("/products")
    @GetMapping("/products")
    public List<String> all() {
        return service.findAll();
    }

    // TODO: @GetMapping("/products/count")
    @GetMapping("/products/count")
    public int count() {
        return service.count();
    }

    // TODO: @PostMapping("/products")
    @PostMapping("/products")
    public String add(/* TODO: @RequestParam */ @RequestParam("name") String name) {
        // TODO: добавьте товар и верните его имя
        service.add(name);
        return name;
    }
}
