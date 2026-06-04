import jakarta.persistence.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.math.BigDecimal;

// --- Подклассы (дополните аннотациями @Entity @Table и полями) ---

@Entity
@Table(name = "books")
class Book2 extends Product2 {
    private String author;
    private String isbn;
    // TODO: конструктор, геттеры, toString()
}
