package m53_hibernate_inheritance.practice.task05;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.math.BigDecimal;
import java.util.List;

@Entity
@DiscriminatorValue("CLOTHING")
@Getter
@Setter
@ToString
class ClothingShop extends ProductShop {

    private String size;
    private String material;

    public ClothingShop() {}

    public ClothingShop(String name, BigDecimal price, String size, String material) {
        super(name, price);
        this.size = size;
        this.material = material;
    }

    public String getSize() { return size; }
    public void setSize(String size) { this.size = size; }

    public String getMaterial() { return material; }
    public void setMaterial(String material) { this.material = material; }


}
