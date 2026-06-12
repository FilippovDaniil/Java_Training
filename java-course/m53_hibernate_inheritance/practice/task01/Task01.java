package m53_hibernate_inheritance.practice.task01;

/**
 * Задача 01 — Модуль 53: SINGLE_TABLE — иерархия товаров
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: org.hibernate.orm:hibernate-core, com.h2database:h2 (см. theory.md).
 * Файл не компилируется без этих зависимостей — это ожидаемо.
 *
 * ЗАДАНИЕ:
 *   Реализуйте иерархию товаров магазина стратегией SINGLE_TABLE:
 *     1) Корневой @Entity Product: поля id (Long, @GeneratedValue IDENTITY),
 *        name (String), price (BigDecimal).
 *     2) Подкласс Book с полями author (String), isbn (String).
 *        @DiscriminatorValue("BOOK").
 *     3) Подкласс Electronics с полями brand (String), warrantyMonths (int).
 *        @DiscriminatorValue("ELECTRONICS").
 *     4) В main() создайте Hibernate SessionFactory (H2 in-memory),
 *        сохраните по одному экземпляру Book и Electronics,
 *        затем загрузите оба через session.get() и выведите.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (примерно):
 *   Book: Чистый код, Роберт Мартин, 978-5-4461-0959-9
 *   Electronics: Ноутбук Dell, Dell, 12 мес.
 *
 * ПОДСКАЗКА:
 *   @Inheritance(strategy = InheritanceType.SINGLE_TABLE)
 *   @DiscriminatorColumn(name = "product_type", discriminatorType = DiscriminatorType.STRING)
 *   Hibernate автоматически создаёт таблицу products с колонкой product_type.
 *   Для SessionFactory используйте Configuration().configure() или programmatic config.
 */

import jakarta.persistence.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.math.BigDecimal;

public class Task01 {

    public static void main(String[] args) {
        // TODO: создайте SessionFactory с H2 in-memory и addAnnotatedClass для каждого Entity
        // Пример минимальной конфигурации:
        //   Configuration cfg = new Configuration();
        //   cfg.setProperty("hibernate.connection.url", "jdbc:h2:mem:shop;DB_CLOSE_DELAY=-1");
        //   cfg.setProperty("hibernate.connection.driver_class", "org.h2.Driver");
        //   cfg.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        //   cfg.setProperty("hibernate.hbm2ddl.auto", "create-drop");
        //   cfg.setProperty("hibernate.show_sql", "true");
        //   cfg.addAnnotatedClass(Product.class);
        //   cfg.addAnnotatedClass(Book.class);
        //   cfg.addAnnotatedClass(Electronics.class);
        //   SessionFactory sf = cfg.buildSessionFactory();

        // TODO: откройте сессию и транзакцию; создайте Book и Electronics; сделайте persist; закройте транзакцию

        // TODO: откройте новую сессию; загрузите обе сущности через session.get(Book.class, 1L) и session.get(Electronics.class, 2L)

        // TODO: выведите поля обоих объектов; закройте SessionFactory
    }
}
