package m53_hibernate_inheritance.practice.task02;

import jakarta.persistence.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.math.BigDecimal;

/**
 * Подкласс Electronics (JOINED)
 */
@Entity
@Table(name = "electronics")
class Electronics2 extends Product2 {

    private String brand;
    private int warrantyMonths;

    public Electronics2() {}

    public Electronics2(String name, BigDecimal price, String brand, int warrantyMonths) {
        super(name, price);
        this.brand = brand;
        this.warrantyMonths = warrantyMonths;
    }

    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }

    public int getWarrantyMonths() { return warrantyMonths; }
    public void setWarrantyMonths(int warrantyMonths) { this.warrantyMonths = warrantyMonths; }

    @Override
    public String toString() {
        return String.format("Electronics2{id=%d, name='%s', price=%s, brand='%s', warrantyMonths=%d}",
                getId(), getName(), getPrice(), brand, warrantyMonths);
    }
}
