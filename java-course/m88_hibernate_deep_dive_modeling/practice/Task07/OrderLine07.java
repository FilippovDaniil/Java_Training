package m88_hibernate_deep_dive_modeling.practice.task07;

import jakarta.persistence.*;
import org.hibernate.Session;
import org.hibernate.annotations.NaturalId;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity @Table(name = "order_lines")
class OrderLine07 {
    // TODO: @EmbeddedId
    private OrderLineId07 id;
    private int qty;
    // TODO: @Embedded
    private Money07 lineTotal;
    protected OrderLine07() {}
    public OrderLine07(OrderLineId07 id, int qty, Money07 lineTotal) {
        this.id = id; this.qty = qty; this.lineTotal = lineTotal;
    }
    public int getQty() { return qty; }
}
