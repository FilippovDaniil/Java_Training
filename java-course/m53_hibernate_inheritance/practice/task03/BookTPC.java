package m53_hibernate_inheritance.practice.task03;

import jakarta.persistence.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.math.BigDecimal;
import java.util.List;

/**
 * Подкласс Book (TABLE_PER_CLASS)
 */
@Entity
@Table(name = "tpc_books")
class BookTPC extends ProductTPC {

    private String author;
    private String isbn;

    public BookTPC() {}

    public BookTPC(String name, BigDecimal price, String author, String isbn) {
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
        return String.format("BookTPC{id=%d, name='%s', price=%s, author='%s', isbn='%s'}",
                getId(), getName(), getPrice(), author, isbn);
    }
}
