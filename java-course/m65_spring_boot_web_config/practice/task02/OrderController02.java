package m65_spring_boot_web_config.practice.task02;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// TODO: @RestController + @RequestMapping("/api/orders")
@RequestMapping("/api/orders")
@RestController
class OrderController02 {

    // TODO: @PostMapping
    @PostMapping
    public ResponseEntity<OrderDto> create(/* TODO: @RequestBody */ @RequestBody OrderDto dto) {
        // TODO: создайте OrderDto с id=100 (продукт и количество — из dto)
        OrderDto saved = new OrderDto(dto.id(),dto.product(),dto.quantity());
        // TODO: верните ResponseEntity.status(HttpStatus.CREATED).body(saved)
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }
}
