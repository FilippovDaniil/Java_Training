package m88_hibernate_deep_dive_modeling.practice.task02;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.Objects;

@Entity
@Table(name = "products")
@Getter
class Product02 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    // TODO: @Embedded
    @Embedded
    private Money02 price;
    protected Product02() {}
    public Product02(String name, Money02 price) { this.name = name; this.price = price; }
}
