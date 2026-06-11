package m86_hibernate_deep_dive_fetching.practice.task01;

/**
 * Задача 01 — Модуль 86: lazy-прокси, Hibernate.isInitialized / initialize
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   org.hibernate.orm:hibernate-core, com.h2database:h2 + META-INF/persistence.xml ("shop-pu").
 *
 * ЗАДАНИЕ:
 *   1) Засейте категорию "Электроника" с 2 товарами (связь @OneToMany — LAZY).
 *   2) В транзакции: em.find(Category01) → коллекция products ещё не загружена.
 *      Выведите Hibernate.isInitialized(c.getProducts()) → false.
 *   3) Вызовите Hibernate.initialize(c.getProducts()) — догрузка пока контекст открыт.
 *      Выведите isInitialized → true и products.size() → 2.
 *
 * ЦЕЛЬ: увидеть прокси/обёртку коллекции и управлять её инициализацией вручную.
 *
 * ПОДСКАЗКА: org.hibernate.Hibernate.isInitialized(proxy) / initialize(proxy).
 *            Без initialize доступ к коллекции вне контекста → LazyInitializationException.
 */

import jakarta.persistence.*;
import org.hibernate.Hibernate;
import java.util.ArrayList;
import java.util.List;

public class Task01 {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("shop-pu");
        EntityManager em = emf.createEntityManager();
        Long id;
        try {
            em.getTransaction().begin();
            Category01 c = new Category01("Электроника");
            c.addProduct(new Product01("Ноутбук"));
            c.addProduct(new Product01("Телефон"));
            em.persist(c);
            em.getTransaction().commit();
            id = c.getId();

            em.clear();
            // TODO: Category01 found = em.find(Category01.class, id);
            // TODO: System.out.println("init? " + Hibernate.isInitialized(found.getProducts())); // false
            // TODO: Hibernate.initialize(found.getProducts());
            // TODO: System.out.println("init? " + Hibernate.isInitialized(found.getProducts())); // true
            // TODO: System.out.println("size = " + found.getProducts().size()); // 2
        } finally {
            em.close();
            emf.close();
        }
    }
}
