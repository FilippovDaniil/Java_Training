package m86_hibernate_deep_dive_fetching.practice.task07;

/**
 * Задача 07 — Модуль 86: МИНИ-ПРОЕКТ «Лаборатория стратегий загрузки»
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   org.hibernate.orm:hibernate-core, com.h2database:h2 + META-INF/persistence.xml ("shop-pu").
 *   hibernate.show_sql=true.
 *
 * ЦЕЛЬ: для одной модели Category07 (1) —— (N) Product07 решить ТРИ задачи витрины,
 *       каждый раз выбирая корректный приём, и по логу show_sql доказать отсутствие N+1.
 *
 * ЗАДАНИЕ (всё в одном файле):
 *   Засейте 4 категории по 2 товара + 1 пустую.
 *
 *   1) loadWithProducts() — нужен список категорий с их товарами:
 *        HQL "select distinct c from Category07 c join fetch c.products"
 *        → один SELECT с JOIN. Вывести имя + size.
 *
 *   2) loadViaGraph(id) — одна категория с товарами через EntityGraph hint:
 *        createEntityGraph + addAttributeNodes("products") + find(..., fetchgraph).
 *        Проверить Hibernate.isInitialized(products) == true после em.clear().
 *
 *   3) overview() — сводка для главной (read-model): DTO-проекция
 *        "select new Row07(c.name, count(p)) ... left join ... group by".
 *        Сущности НЕ грузятся → нет lazy/N+1.
 *
 *   Сравните в логе число SELECT для каждого метода: везде 1 (или 1 агрегат), НЕ 1+N.
 *
 * АРХИТЕКТУРА (приём под кейс):
 *   список+коллекция → JOIN FETCH (distinct)
 *   одна сущность+граф → EntityGraph hint
 *   read-model сводка → DTO-проекция
 *
 * ОЖИДАЕМЫЙ ИТОГ:
 *   loadWithProducts(): 4 категории по 2 товара, один JOIN-запрос;
 *   loadViaGraph(id): products инициализированы вне исходного контекста;
 *   overview(): 5 строк (4 по 2 + пустая 0), один агрегирующий запрос.
 *
 * ПОДСКАЗКА: соберите Task03 (JOIN FETCH), Task04 (EntityGraph), Task06 (DTO).
 */

import jakarta.persistence.*;
import org.hibernate.Hibernate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Task07 {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("shop-pu");
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            for (int i = 1; i <= 4; i++) {
                Category07 c = new Category07("Категория-" + i);
                c.addProduct(new Product07("Товар-" + i + "-1"));
                c.addProduct(new Product07("Товар-" + i + "-2"));
                em.persist(c);
            }
            em.persist(new Category07("Распродажа"));   // пустая
            em.getTransaction().commit();
            em.clear();

            // 1) JOIN FETCH
            // TODO: List<Category07> withProducts = em.createQuery(
            // TODO:     "select distinct c from Category07 c join fetch c.products", Category07.class)
            // TODO:     .getResultList();
            // TODO: withProducts.forEach(c -> System.out.println(c.getName() + ": " + c.getProducts().size()));

            // 2) EntityGraph hint
            // TODO: Long someId = withProducts.get(0).getId();
            // TODO: em.clear();
            // TODO: EntityGraph<Category07> g = em.createEntityGraph(Category07.class);
            // TODO: g.addAttributeNodes("products");
            // TODO: Category07 viaGraph = em.find(Category07.class, someId,
            // TODO:         Map.of("jakarta.persistence.fetchgraph", g));
            // TODO: System.out.println("graph init? " + Hibernate.isInitialized(viaGraph.getProducts()));

            // 3) DTO overview
            // TODO: List<Row07> rows = em.createQuery(
            // TODO:     "select new Row07(c.name, count(p)) from Category07 c " +
            // TODO:     "left join c.products p group by c.id, c.name", Row07.class).getResultList();
            // TODO: rows.forEach(r -> System.out.println(r.name() + " -> " + r.count()));
        } finally {
            em.close();
            emf.close();
        }
    }
}
