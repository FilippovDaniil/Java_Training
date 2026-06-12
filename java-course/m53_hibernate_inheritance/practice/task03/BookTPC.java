package m53_hibernate_inheritance.practice.task03;

import jakarta.persistence.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.math.BigDecimal;
import java.util.List;

// --- Подклассы (дополните @Entity @Table и полями) ---

@Entity
@Table(name = "tpc_books")
class BookTPC extends ProductTPC {
    private String author;
    private String isbn;
    // TODO: конструктор, геттеры, toString()
    // Обратите внимание: таблица tpc_books будет содержать name и price ТОЖЕ
}
