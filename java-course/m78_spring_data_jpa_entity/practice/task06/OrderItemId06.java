package m78_spring_data_jpa_entity.practice.task06;

import jakarta.persistence.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.io.Serializable;
import java.util.Objects;

// TODO: @Embeddable
@Embeddable
class OrderItemId06 implements Serializable {

    private Long orderId;
    private Long productId;

    protected OrderItemId06() {

    }

    public OrderItemId06(Long orderId, Long productId) {
        this.orderId = orderId;
        this.productId = productId;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        OrderItemId06 that = (OrderItemId06) object;
        return Objects.equals(orderId, that.orderId) && Objects.equals(productId, that.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, productId);
    }
}
