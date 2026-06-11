package m88_hibernate_deep_dive_modeling.practice.task07;

import jakarta.persistence.*;
import org.hibernate.Session;
import org.hibernate.annotations.NaturalId;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

// TODO: @Embeddable
class OrderLineId07 implements Serializable {
    private Long orderId;
    private Long productId;
    protected OrderLineId07() {}
    public OrderLineId07(Long orderId, Long productId) { this.orderId = orderId; this.productId = productId; }
    // TODO: equals/hashCode по orderId+productId
}
