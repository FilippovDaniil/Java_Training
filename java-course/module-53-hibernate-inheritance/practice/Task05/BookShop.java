import jakarta.persistence.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.math.BigDecimal;
import java.util.List;

@Entity
@DiscriminatorValue("BOOK")
class BookShop extends ProductShop {
    private String author;
    private String genre; // художественная, техническая, ...

    // TODO: конструктор(name, price, author, genre), геттеры
}
