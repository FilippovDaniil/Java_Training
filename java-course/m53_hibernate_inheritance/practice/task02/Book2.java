package m53_hibernate_inheritance.practice.task02;

import jakarta.persistence.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.math.BigDecimal;

// --- Подклассы (дополните аннотациями @Entity @Table и полями) ---

/**
 * Подкласс Book (JOINED)
 */
@Entity
@Table(name = "books")
class Book2 extends Product2 {

    private String author;
    private String isbn;

    public Book2() {}

    public Book2(String name, BigDecimal price, String author, String isbn) {
        super(name, price);
        this.author = author;
        this.isbn = isbn;
    }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }

    @Override
    public String toString() {
        return String.format("Book2{id=%d, name='%s', price=%s, author='%s', isbn='%s'}",
                getId(), getName(), getPrice(), author, isbn);
    }
}

