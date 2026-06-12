package m53_hibernate_inheritance.practice.task02;

import jakarta.persistence.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.math.BigDecimal;

// --- Корневой класс (дополните) ---

@Entity
@Table(name = "products")
@Inheritance(strategy = InheritanceType.JOINED)
abstract class Product2 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private BigDecimal price;

    // TODO: конструктор, геттеры, toString()
}
