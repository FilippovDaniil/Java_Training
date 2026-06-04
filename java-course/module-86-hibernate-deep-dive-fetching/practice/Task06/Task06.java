/**
 * Задача 06 — Модуль 86: проекции — DTO (select new) и Tuple
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   org.hibernate.orm:hibernate-core, com.h2database:h2 + META-INF/persistence.xml ("shop-pu").
 *
 * ЗАДАНИЕ:
 *   Когда нужны лишь «имя категории + число товаров» — не грузите сущности.
 *
 *   ЧАСТЬ A — DTO через constructor expression:
 *     1) record CategorySummary06(String name, long count) объявлен ниже.
 *     2) HQL: "select new CategorySummary06(c.name, count(p)) from Category06 c
 *              left join c.products p group by c.id, c.name"
 *        getResultList(CategorySummary06.class) → вывести строки.
 *
 *   ЧАСТЬ B — Tuple (без отдельного DTO-класса):
 *     3) HQL: "select c.name as name, count(p) as cnt from Category06 c
 *              left join c.products p group by c.id, c.name"
 *        тип результата Tuple; для каждой t вывести t.get("name", String.class)
 *        и t.get("cnt", Long.class).
 *
 *   Засейте 2 категории (одна с товарами, одна пустая) для проверки left join (count=0).
 *
 * ЦЕЛЬ: освоить проекции — read-модели без прокси, lazy и N+1.
 *
 * ПОДСКАЗКА: нет управляемых сущностей → нет LazyInitializationException вовсе.
 */

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class Task06 {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("shop-pu");
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Category06 e = new Category06("Электроника");
            e.addProduct(new Product06("Ноутбук"));
            e.addProduct(new Product06("Телефон"));
            em.persist(e);
            em.persist(new Category06("Пустая"));   // count должен быть 0
            em.getTransaction().commit();
            em.clear();

            // ЧАСТЬ A — DTO
            // TODO: List<CategorySummary06> dtos = em.createQuery(
            // TODO:     "select new CategorySummary06(c.name, count(p)) from Category06 c " +
            // TODO:     "left join c.products p group by c.id, c.name", CategorySummary06.class)
            // TODO:     .getResultList();
            // TODO: dtos.forEach(d -> System.out.println(d.name() + " -> " + d.count()));

            // ЧАСТЬ B — Tuple
            // TODO: List<Tuple> tuples = em.createQuery(
            // TODO:     "select c.name as name, count(p) as cnt from Category06 c " +
            // TODO:     "left join c.products p group by c.id, c.name", Tuple.class)
            // TODO:     .getResultList();
            // TODO: tuples.forEach(t -> System.out.println(
            // TODO:     t.get("name", String.class) + " -> " + t.get("cnt", Long.class)));
        } finally {
            em.close();
            emf.close();
        }
    }
}
