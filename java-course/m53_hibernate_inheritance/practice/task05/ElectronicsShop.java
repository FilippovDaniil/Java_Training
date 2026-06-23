package m53_hibernate_inheritance.practice.task05;

import jakarta.persistence.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.math.BigDecimal;
import java.util.List;

@Entity
@DiscriminatorValue("ELECTRONICS")
class ElectronicsShop extends ProductShop {

    private String brand;
    private int warrantyMonths;

    public ElectronicsShop() {}

    public ElectronicsShop(String name, BigDecimal price, String brand, int warrantyMonths) {
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
        return String.format("Electronics{id=%d, name='%s', price=%s, brand='%s', warrantyMonths=%d}",
                getId(), getName(), getPrice(), brand, warrantyMonths);
    }
}
