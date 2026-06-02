/**
 * Задача 02 — Модуль 78: Ограничения колонок через @Column
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   org.springframework.boot:spring-boot-starter-data-jpa:3.2.x, com.h2database:h2 (см. theory.md).
 *
 * ЗАДАНИЕ:
 *   Разметьте поля Product02 ограничениями @Column:
 *     - name: @Column(name = "product_name", nullable = false, length = 100)
 *     - sku:  @Column(nullable = false, unique = true, length = 32)
 *     - description: @Column(length = 1000)   (длинный текст)
 *   Запустите с show-sql=true и проверьте DDL: должны появиться NOT NULL, UNIQUE,
 *   нестандартные длины VARCHAR и имя колонки product_name.
 *
 * ЦЕЛЬ: управлять схемой таблицы через аннотации полей.
 *
 * ПОДСКАЗКА: имя колонки по умолчанию = имя поля; length по умолчанию = 255.
 */
import jakarta.persistence.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Task02 {
    public static void main(String[] args) {
        SpringApplication.run(Task02.class, args);
    }
}

@Entity
@Table(name = "products")
class Product02 {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // TODO: @Column(name = "product_name", nullable = false, length = 100)
    private String name;

    // TODO: @Column(nullable = false, unique = true, length = 32)
    private String sku;

    // TODO: @Column(length = 1000)
    private String description;

    protected Product02() {}
    public Product02(String name, String sku) { this.name = name; this.sku = sku; }
}
