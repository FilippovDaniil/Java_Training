package m53_hibernate_inheritance.practice.task02;

import jakarta.persistence.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.math.BigDecimal;

/**
 * Подкласс Clothing (JOINED)
 */
@Entity
@Table(name = "clothing")
class Clothing extends Product2 {

    private String size;
    private String material;

    public Clothing() {}

    public Clothing(String name, BigDecimal price, String size, String material) {
        super(name, price);
        this.size = size;
        this.material = material;
    }

    public String getSize() { return size; }
    public void setSize(String size) { this.size = size; }

    public String getMaterial() { return material; }
    public void setMaterial(String material) { this.material = material; }

    @Override
    public String toString() {
        return String.format("Clothing{id=%d, name='%s', price=%s, size='%s', material='%s'}",
                getId(), getName(), getPrice(), size, material);
    }
}
