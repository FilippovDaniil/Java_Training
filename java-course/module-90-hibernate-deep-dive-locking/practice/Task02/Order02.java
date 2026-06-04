import jakarta.persistence.*;

@Entity @Table(name = "orders")
class Order02 {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String number;
    @Version
    private long version;
    protected Order02() {}
    public Order02(String number) { this.number = number; }
    public Long getId() { return id; }
    public long getVersion() { return version; }
}
