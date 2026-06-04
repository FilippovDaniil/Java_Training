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

    // TODO: конструктор(name, price, brand, warrantyMonths), геттеры
}
