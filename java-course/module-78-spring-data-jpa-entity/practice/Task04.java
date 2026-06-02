/**
 * Задача 04 — Модуль 78: @Transient — вычисляемое поле без колонки
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   org.springframework.boot:spring-boot-starter-data-jpa:3.2.x, com.h2database:h2 (см. theory.md).
 *
 * ЗАДАНИЕ:
 *   1) В Product04 поле displayLabel пометьте @Transient — оно НЕ должно быть колонкой.
 *   2) Реализуйте getDisplayLabel(): вернуть name + " — " + price + " руб.".
 *      (вычисляемое представление, не хранится в БД)
 *   3) Запустите и убедитесь, что в DDL таблицы products НЕТ колонки display_label.
 *
 * ВОПРОС (ответьте комментарием):
 *   Когда поле стоит сделать @Transient, а когда — реальной колонкой?
 *
 * ПОДСКАЗКА: @Transient исключает поле из персистентности (живёт только в объекте).
 */
import jakarta.persistence.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Task04 {
    public static void main(String[] args) {
        SpringApplication.run(Task04.class, args);
    }
}

@Entity
@Table(name = "products")
class Product04 {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private long price;

    // TODO: @Transient
    private String displayLabel;

    protected Product04() {}
    public Product04(String name, long price) { this.name = name; this.price = price; }

    public String getDisplayLabel() {
        // TODO: верните name + " — " + price + " руб."
        return null;
    }
}
