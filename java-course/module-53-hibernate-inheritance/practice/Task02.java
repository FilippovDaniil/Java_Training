/**
 * Задача 02 — Модуль 53: JOINED — иерархия с отдельными таблицами на подкласс
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: org.hibernate.orm:hibernate-core, com.h2database:h2 (см. theory.md).
 * Файл не компилируется без этих зависимостей — это ожидаемо.
 *
 * ЗАДАНИЕ:
 *   Перепишите иерархию Product → Book / Electronics / Clothing стратегией JOINED:
 *     1) Product — @Entity @Table("products") с полями id, name, price.
 *     2) Book — @Entity @Table("books") с author, isbn.
 *        PK таблицы books одновременно FK на products.id.
 *     3) Electronics — @Entity @Table("electronics") с brand, warrantyMonths.
 *     4) Clothing — @Entity @Table("clothing") с size, material.
 *     5) В main() создайте H2 SessionFactory, сохраните по одному объекту каждого типа,
 *        проверьте через Native SQL (session.createNativeQuery), что:
 *        - в таблице products 3 строки;
 *        - в таблице books 1 строка.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (примерно):
 *   Строк в products: 3
 *   Строк в books: 1
 *
 * ПОДСКАЗКА:
 *   @Inheritance(strategy = InheritanceType.JOINED) — только на корневом классе.
 *   Hibernate при INSERT в Book вставляет 2 строки:
 *     - одну в products (общие поля)
 *     - одну в books (специфичные поля)
 *   При SELECT Book делается JOIN: SELECT * FROM products p JOIN books b ON p.id = b.id.
 *   Дискриминатор при JOINED опционален, но можно добавить для ясности.
 *
 *   Для нативного запроса:
 *     Number count = (Number) session
 *         .createNativeQuery("SELECT COUNT(*) FROM products")
 *         .getSingleResult();
 */
import jakarta.persistence.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.math.BigDecimal;

public class Task02 {

    public static void main(String[] args) {
        // TODO: создайте SessionFactory — добавьте Product, Book, Electronics, Clothing через addAnnotatedClass

        // TODO: сохраните:
        //   Book: "Чистый код", 1200.00, author="Роберт Мартин", isbn="978-5-4461-0959-9"
        //   Electronics: "Смартфон", 45000.00, brand="Samsung", warrantyMonths=12
        //   Clothing: "Худи", 3500.00, size="M", material="Хлопок"

        // TODO: откройте новую сессию; выполните нативный SELECT COUNT(*) FROM products
        //       и SELECT COUNT(*) FROM books; выведите результаты

        // TODO: закройте SessionFactory
    }
}

// --- Корневой класс (дополните) ---

@Entity
@Table(name = "products")
@Inheritance(strategy = InheritanceType.JOINED)
abstract class Product2 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private BigDecimal price;

    // TODO: конструктор, геттеры, toString()
}

// --- Подклассы (дополните аннотациями @Entity @Table и полями) ---

@Entity
@Table(name = "books")
class Book2 extends Product2 {
    private String author;
    private String isbn;
    // TODO: конструктор, геттеры, toString()
}

@Entity
@Table(name = "electronics")
class Electronics2 extends Product2 {
    private String brand;
    private int warrantyMonths;
    // TODO: конструктор, геттеры, toString()
}

@Entity
@Table(name = "clothing")
class Clothing extends Product2 {
    private String size;
    private String material;
    // TODO: конструктор, геттеры, toString()
}
