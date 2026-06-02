/**
 * Задача 05 — Модуль 78: @Embeddable / @Embedded — value object Money
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   org.springframework.boot:spring-boot-starter-data-jpa:3.2.x, com.h2database:h2 (см. theory.md).
 *
 * ЗАДАНИЕ:
 *   Вынесите цену в переиспользуемый value object.
 *   1) Класс Money05 пометьте @Embeddable. Поля: long amount, String currency.
 *      (НЕТ @Id и НЕТ своей таблицы — поля встроятся в таблицу владельца)
 *   2) В Product05 поле price (типа Money05) пометьте @Embedded.
 *   3) Запустите: в таблице products должны появиться колонки amount и currency
 *      (а не отдельная таблица money).
 *
 * ОЖИДАЕМЫЙ DDL (примерно):
 *   CREATE TABLE products (id ..., name ..., amount BIGINT, currency VARCHAR ...)
 *
 * ВОПРОС (ответьте комментарием):
 *   Чем @Embeddable отличается от @Entity?
 *
 * ПОДСКАЗКА: @Embeddable не имеет идентичности — это объект-значение (равенство по значению).
 */
import jakarta.persistence.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Task05 {
    public static void main(String[] args) {
        SpringApplication.run(Task05.class, args);
    }
}

// TODO: @Embeddable
class Money05 {
    private long amount;
    private String currency;

    protected Money05() {}
    public Money05(long amount, String currency) { this.amount = amount; this.currency = currency; }
}

@Entity
@Table(name = "products")
class Product05 {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    // TODO: @Embedded
    private Money05 price;

    protected Product05() {}
    public Product05(String name, Money05 price) { this.name = name; this.price = price; }
}
