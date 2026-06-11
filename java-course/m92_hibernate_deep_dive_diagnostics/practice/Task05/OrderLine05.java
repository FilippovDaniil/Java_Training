package m92_hibernate_deep_dive_diagnostics.practice.task05;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity @Table(name = "order_lines")
class OrderLine05 {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String product;
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "order_id")
    private Order05 order;
    protected OrderLine05() {}
    public OrderLine05(String product) { this.product = product; }
    public void setOrder(Order05 o) { this.order = o; }
}
