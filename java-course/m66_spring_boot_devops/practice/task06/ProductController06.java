package m66_spring_boot_devops.practice.task06;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.stereotype.Service;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// Контроллер (готов)
@RestController
@RequestMapping("/api/products")
public class ProductController06 {
    private final ProductService06 service;
    public ProductController06(ProductService06 service) { this.service = service; }

    @GetMapping("/{id}")
    public ProductView06 one(@PathVariable("id") Long id) {
        return new ProductView06(id, service.getName(id));
    }
}
