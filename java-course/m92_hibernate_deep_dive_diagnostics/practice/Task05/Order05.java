package m92_hibernate_deep_dive_diagnostics.practice.task05;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity @Table(name = "orders")
class Order05 {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int total;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderLine05> lines = new ArrayList<>();
    protected Order05() {}
    public Order05(int total) { this.total = total; }
    public int getTotal() { return total; }
    public void setTotal(int total) { this.total = total; }
    public List<OrderLine05> getLines() { return lines; }
    public void addLine(OrderLine05 l) { lines.add(l); l.setOrder(this); }
}
