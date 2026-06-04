/**
 * Задача 04 — Модуль 86: EntityGraph как hint к запросу (чистый JPA)
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   org.hibernate.orm:hibernate-core, com.h2database:h2 + META-INF/persistence.xml ("shop-pu").
 *
 * ЗАДАНИЕ:
 *   1) Засейте 1 категорию с 2 товарами.
 *   2) Постройте граф программно:
 *        EntityGraph<Category04> g = em.createEntityGraph(Category04.class);
 *        g.addAttributeNodes("products");
 *   3) Загрузите категорию с hint fetchgraph:
 *        em.find(Category04.class, id, Map.of("jakarta.persistence.fetchgraph", g));
 *   4) После em.clear() (закрыть доступ к контексту) убедитесь, что products уже
 *      инициализированы (Hibernate.isInitialized == true) — граф подтянул их сразу.
 *
 * ЦЕЛЬ: освоить программный EntityGraph и hint'ы fetchgraph/loadgraph (аналог @EntityGraph
 *       из Spring Data, модуль 82, но на уровне EntityManager).
 *
 * ПОДСКАЗКА: ключ hint — строковая константа "jakarta.persistence.fetchgraph".
 */

import jakarta.persistence.*;
import org.hibernate.Hibernate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Task04 {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("shop-pu");
        EntityManager em = emf.createEntityManager();
        Long id;
        try {
            em.getTransaction().begin();
            Category04 c = new Category04("Электроника");
            c.addProduct(new Product04("Ноутбук"));
            c.addProduct(new Product04("Телефон"));
            em.persist(c);
            em.getTransaction().commit();
            id = c.getId();
            em.clear();

            // TODO: EntityGraph<Category04> g = em.createEntityGraph(Category04.class);
            // TODO: g.addAttributeNodes("products");
            // TODO: Category04 found = em.find(Category04.class, id,
            // TODO:         Map.of("jakarta.persistence.fetchgraph", g));
            // TODO: System.out.println("products init? " + Hibernate.isInitialized(found.getProducts())); // true
            // TODO: System.out.println("size = " + found.getProducts().size()); // 2
        } finally {
            em.close();
            emf.close();
        }
    }
}
