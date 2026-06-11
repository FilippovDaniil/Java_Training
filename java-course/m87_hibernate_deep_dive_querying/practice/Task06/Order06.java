package m87_hibernate_deep_dive_querying.practice.task06;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity @Table(name = "orders")
class Order06 {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String number;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderLine06> lines = new ArrayList<>();
    protected Order06() {}
    public Order06(String number) { this.number = number; }
    public Long getId() { return id; }
    public List<OrderLine06> getLines() { return lines; }
    public void addLine(OrderLine06 l) { lines.add(l); l.setOrder(this); }
}
