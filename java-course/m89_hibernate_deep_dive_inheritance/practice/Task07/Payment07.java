package m89_hibernate_deep_dive_inheritance.practice.task07;

import jakarta.persistence.*;
import java.util.List;

@Entity @Table(name = "payments")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "payment_type")
abstract class Payment07 {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private long amount;
    protected Payment07() {}
    protected Payment07(long amount) { this.amount = amount; }
    public long getAmount() { return amount; }
    // TODO: public abstract String describe();
}
