package m88_hibernate_deep_dive_modeling.practice.task03;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "customers")
@Getter
class Customer03 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    // TODO: @Embedded @AttributeOverrides({ ship_city, ship_street })
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "city",   column = @Column(name = "ship_city")),
            @AttributeOverride(name = "street", column = @Column(name = "ship_street"))
    })
    private Address03 shipping;

    // TODO: @Embedded @AttributeOverrides({ bill_city, bill_street })
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "bill_city",   column = @Column(name = "bill_city")),
            @AttributeOverride(name = "bill_street", column = @Column(name = "bill_street"))
    })
    private Address03 billing;

    protected Customer03() {}

    public Customer03(String name, Address03 shipping, Address03 billing) {
        this.name = name;
        this.shipping = shipping;
        this.billing = billing;
    }

}
