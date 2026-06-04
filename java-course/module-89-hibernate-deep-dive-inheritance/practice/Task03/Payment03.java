import jakarta.persistence.*;

@Entity
// TODO: @Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
abstract class Payment03 {
    @Id
    // TODO: @GeneratedValue(strategy = GenerationType.TABLE)   // НЕ IDENTITY!
    private Long id;
    private long amount;
    protected Payment03() {}
    protected Payment03(long amount) { this.amount = amount; }
    public Long getId() { return id; }
    public long getAmount() { return amount; }
}
