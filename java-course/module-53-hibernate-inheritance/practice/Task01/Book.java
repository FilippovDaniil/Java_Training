import jakarta.persistence.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.math.BigDecimal;

// --- Подкласс Book (дополните) ---

@Entity
@DiscriminatorValue("BOOK")
class Book extends Product {
    private String author;
    private String isbn;

    // TODO: добавьте конструктор, геттеры, toString()
}
