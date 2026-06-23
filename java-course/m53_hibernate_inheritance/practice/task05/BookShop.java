package m53_hibernate_inheritance.practice.task05;

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

    public BookShop() {}

    public BookShop(String name, BigDecimal price, String author, String genre) {
        super(name, price);
        this.author = author;
        this.genre = genre;
    }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }

    @Override
    public String toString() {
        return String.format("Book{id=%d, name='%s', price=%s, author='%s', genre='%s'}",
                getId(), getName(), getPrice(), author, genre);
    }
}
