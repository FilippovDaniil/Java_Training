package m88_hibernate_deep_dive_modeling.practice.task07;

import jakarta.persistence.*;
import org.hibernate.Session;
import org.hibernate.annotations.NaturalId;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

// TODO: @Embeddable
@Embeddable
class OrderLineId07 implements Serializable {
    private Long orderId;
    private Long productId;
    protected OrderLineId07() {}
    public OrderLineId07(Long orderId, Long productId) { this.orderId = orderId; this.productId = productId; }
    // TODO: equals/hashCode по orderId+productId
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderLineId07 that = (OrderLineId07) o;
        return Objects.equals(orderId, that.orderId) &&
                Objects.equals(productId, that.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, productId);
    }

    @Override
    public String toString() {
        return "OrderLineId07{orderId=" + orderId + ", productId=" + productId + "}";
    }
}
