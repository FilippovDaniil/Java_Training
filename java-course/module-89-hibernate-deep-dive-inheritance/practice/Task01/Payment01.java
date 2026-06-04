import jakarta.persistence.*;

@Entity @Table(name = "payments")
// TODO: @Inheritance(strategy = InheritanceType.SINGLE_TABLE)
// TODO: @DiscriminatorColumn(name = "payment_type")
abstract class Payment01 {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private long amount;
    protected Payment01() {}
    protected Payment01(long amount) { this.amount = amount; }
    public Long getId() { return id; }
    public long getAmount() { return amount; }
}
