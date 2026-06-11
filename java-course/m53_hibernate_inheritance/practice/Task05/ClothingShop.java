package m53_hibernate_inheritance.practice.task05;

import jakarta.persistence.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.math.BigDecimal;
import java.util.List;

@Entity
@DiscriminatorValue("CLOTHING")
class ClothingShop extends ProductShop {
    private String size;
    private String material;

    // TODO: конструктор(name, price, size, material), геттеры
}
