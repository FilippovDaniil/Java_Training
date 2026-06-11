package m53_hibernate_inheritance.practice.task01;

import jakarta.persistence.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.math.BigDecimal;

// --- Подкласс Electronics (дополните) ---

@Entity
@DiscriminatorValue("ELECTRONICS")
class Electronics extends Product {
    private String brand;
    private int warrantyMonths;

    // TODO: добавьте конструктор, геттеры, toString()
}
