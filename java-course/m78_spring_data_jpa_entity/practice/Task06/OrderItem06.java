package m78_spring_data_jpa_entity.practice.task06;

import jakarta.persistence.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "order_items")
class OrderItem06 {
    // TODO: @EmbeddedId
    private OrderItemId06 id;

    private int quantity;

    protected OrderItem06() {}
    public OrderItem06(OrderItemId06 id, int quantity) { this.id = id; this.quantity = quantity; }
}
