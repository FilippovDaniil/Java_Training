package m87_hibernate_deep_dive_querying.practice.task03;

/**
 * Задача 03 — Модуль 87: Criteria API — динамический фильтр
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   org.hibernate.orm:hibernate-core, com.h2database:h2 + META-INF/persistence.xml ("shop-pu").
 *
 * ЗАДАНИЕ:
 *   Реализуйте метод search(em, category, minPrice, maxPrice), где ЛЮБОЙ из параметров
 *   может быть null (не задан). Соберите запрос через Criteria API:
 *     1) CriteriaBuilder cb = em.getCriteriaBuilder();
 *        CriteriaQuery<Product03> cq = cb.createQuery(Product03.class);
 *        Root<Product03> root = cq.from(Product03.class);
 *     2) Накапливайте предикаты в List<Predicate> ТОЛЬКО для не-null параметров:
 *        - category != null → cb.equal(root.get("category"), category)
 *        - minPrice != null → cb.ge(root.get("price"), minPrice)
 *        - maxPrice != null → cb.le(root.get("price"), maxPrice)
 *     3) cq.select(root).where(cb.and(filters.toArray(new Predicate[0])))
 *           .orderBy(cb.asc(root.get("name")));
 *     4) Верните em.createQuery(cq).getResultList().
 *
 *   В main вызовите search с разными комбинациями (только категория; диапазон цен; всё null).
 *
 * ЦЕЛЬ: освоить типобезопасный динамический запрос без конкатенации HQL-строк.
 *
 * ПОДСКАЗКА: null-параметр = НЕ добавляем предикат (условие не применяется).
 *            cb.and() от пустого массива = «истина» (вернутся все).
 */

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class Task03 {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("shop-pu");
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(new Product03("Хлеб", "Еда", 40));
            em.persist(new Product03("Сыр", "Еда", 300));
            em.persist(new Product03("Мышь", "Техника", 800));
            em.persist(new Product03("Кабель", "Техника", 120));
            em.getTransaction().commit();
            em.clear();

            // TODO: System.out.println("только категория:"); search(em, "Еда", null, null).forEach(...);
            // TODO: System.out.println("диапазон цен:");    search(em, null, 100, 500).forEach(...);
            // TODO: System.out.println("без фильтров:");     search(em, null, null, null).forEach(...);
        } finally {
            em.close();
            emf.close();
        }
    }

    static List<Product03> search(EntityManager em, String category, Integer minPrice, Integer maxPrice) {
        // TODO: CriteriaBuilder cb = em.getCriteriaBuilder();
        // TODO: CriteriaQuery<Product03> cq = cb.createQuery(Product03.class);
        // TODO: Root<Product03> root = cq.from(Product03.class);
        // TODO: List<Predicate> filters = new ArrayList<>();
        // TODO: if (category != null) filters.add(cb.equal(root.get("category"), category));
        // TODO: if (minPrice != null) filters.add(cb.ge(root.get("price"), minPrice));
        // TODO: if (maxPrice != null) filters.add(cb.le(root.get("price"), maxPrice));
        // TODO: cq.select(root).where(cb.and(filters.toArray(new Predicate[0]))).orderBy(cb.asc(root.get("name")));
        // TODO: return em.createQuery(cq).getResultList();
        return List.of();
    }
}
