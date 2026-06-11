package m53_hibernate_inheritance.practice.task03;

import jakarta.persistence.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.math.BigDecimal;
import java.util.List;

// --- Корневой класс (дополните) ---

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
abstract class ProductTPC {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE) // TABLE — единый счётчик на все подтипы
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private BigDecimal price;

    // TODO: конструктор(name, price), геттеры, toString()
}
