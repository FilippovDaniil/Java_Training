import jakarta.persistence.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "tpc_electronics")
class ElectronicsTPC extends ProductTPC {
    private String brand;
    private int warrantyMonths;
    // TODO: конструктор, геттеры, toString()
}
