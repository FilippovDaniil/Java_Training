package m89_hibernate_deep_dive_inheritance.practice.task04;

import jakarta.persistence.*;
import java.time.Instant;

@Entity @Table(name = "orders")
class Order04 extends BaseEntity04 {
    private String number;
    protected Order04() {}
    public Order04(String number) { this.number = number; }
}
