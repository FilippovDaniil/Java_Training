import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// TODO: @RestController + @RequestMapping("/api/products")
class CatalogController01 {

    // TODO: @GetMapping("/{id}")
    public ProductDto getOne(/* TODO: @PathVariable */ Long id) {
        // TODO: верните new ProductDto(id, "Демо-товар", 9990)
        return null;
    }
}
