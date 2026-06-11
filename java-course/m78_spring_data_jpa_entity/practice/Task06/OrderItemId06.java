package m78_spring_data_jpa_entity.practice.task06;

import jakarta.persistence.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.io.Serializable;
import java.util.Objects;

// TODO: @Embeddable
class OrderItemId06 implements Serializable {
    private Long orderId;
    private Long productId;

    protected OrderItemId06() {}
    public OrderItemId06(Long orderId, Long productId) { this.orderId = orderId; this.productId = productId; }

    // TODO: реализуйте equals(...) по (orderId, productId)
    @Override
    public boolean equals(Object o) {
        // TODO
        return false;
    }

    // TODO: реализуйте hashCode() по (orderId, productId)
    @Override
    public int hashCode() {
        // TODO: return Objects.hash(orderId, productId);
        return 0;
    }
}
