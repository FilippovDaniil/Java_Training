package m87_hibernate_deep_dive_querying.practice.task05;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categories")
@Getter
class Category05 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "category")   // обратная сторона (не владелец)
    private List<Product05> products = new ArrayList<>();

    protected Category05() {}

    public Category05(String name) { this.name = name; }

    /** Хелпер: синхронизирует ОБЕ стороны связи. */
    public void addProduct(Product05 p) {
        products.add(p);
        p.setCategory(this);   // ВЛАДЕЛЕЦ — пишет FK
    }
}
