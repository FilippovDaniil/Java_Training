package m90_hibernate_deep_dive_locking.practice.task07;

import jakarta.persistence.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.envers.Audited;

import java.util.List;

@Entity
@Table(name = "products")
@SQLDelete(sql = "UPDATE products SET deleted = true WHERE id = ? AND version = ?")
@SQLRestriction("deleted = false")
@Audited
// TODO: @SQLDelete(sql = "UPDATE products SET deleted = true WHERE id = ? AND version = ?")
// TODO: @SQLRestriction("deleted = false")
// TODO: @Audited
class Product07 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private int stock;

    @Version
    private long version;

    private boolean deleted = false;

    protected Product07() {}

    public Product07(String name, int stock) {
        this.name = name;
        this.stock = stock;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public int getStock() { return stock; }
    public long getVersion() { return version; }
    public boolean isDeleted() { return deleted; }

    public void setStock(int stock) { this.stock = stock; }
    public void setDeleted(boolean deleted) { this.deleted = deleted; }

    @Override
    public String toString() {
        return "Product07{id=" + id + ", name='" + name + "', stock=" + stock +
                ", version=" + version + ", deleted=" + deleted + "}";
    }
}
