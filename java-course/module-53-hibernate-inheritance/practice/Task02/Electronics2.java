import jakarta.persistence.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.math.BigDecimal;

@Entity
@Table(name = "electronics")
class Electronics2 extends Product2 {
    private String brand;
    private int warrantyMonths;
    // TODO: конструктор, геттеры, toString()
}
