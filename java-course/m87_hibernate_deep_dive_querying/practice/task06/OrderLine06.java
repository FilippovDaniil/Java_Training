package m87_hibernate_deep_dive_querying.practice.task06;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity @Table(name = "order_lines")
class OrderLine06 {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String product;
    private int qty;
    @ManyToOne @JoinColumn(name = "order_id")
    private Order06 order;
    protected OrderLine06() {}
    public OrderLine06(String product, int qty) { this.product = product; this.qty = qty; }
    public void setOrder(Order06 o) { this.order = o; }
}
