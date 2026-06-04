import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// TODO: @RestController + @RequestMapping("/api/orders")
class OrderController02 {

    // TODO: @PostMapping
    public ResponseEntity<OrderDto> create(/* TODO: @RequestBody */ OrderDto dto) {
        // TODO: создайте OrderDto с id=100 (продукт и количество — из dto)
        // TODO: верните ResponseEntity.status(HttpStatus.CREATED).body(saved)
        return null;
    }
}
