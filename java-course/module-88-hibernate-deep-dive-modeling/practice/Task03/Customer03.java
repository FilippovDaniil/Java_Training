import jakarta.persistence.*;

@Entity @Table(name = "customers")
class Customer03 {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    // TODO: @Embedded @AttributeOverrides({ ship_city, ship_street })
    private Address03 shipping;

    // TODO: @Embedded @AttributeOverrides({ bill_city, bill_street })
    private Address03 billing;

    protected Customer03() {}
    public Customer03(String name, Address03 shipping, Address03 billing) {
        this.name = name; this.shipping = shipping; this.billing = billing;
    }
    public Long getId() { return id; }
    public Address03 getShipping() { return shipping; }
    public Address03 getBilling() { return billing; }
}
