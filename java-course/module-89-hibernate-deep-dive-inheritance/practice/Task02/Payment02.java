import jakarta.persistence.*;

@Entity @Table(name = "payments")
// TODO: @Inheritance(strategy = InheritanceType.JOINED)
abstract class Payment02 {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private long amount;
    protected Payment02() {}
    protected Payment02(long amount) { this.amount = amount; }
    public Long getId() { return id; }
    public long getAmount() { return amount; }
}
