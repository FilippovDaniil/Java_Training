package m87_hibernate_deep_dive_querying.practice.task03;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class Task03 {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("shop-pu");
        EntityManager em = emf.createEntityManager();
        try {
            // ===== Подготовка данных =====
            em.getTransaction().begin();
            em.persist(new Product03("Хлеб", "Еда", 40));
            em.persist(new Product03("Сыр", "Еда", 300));
            em.persist(new Product03("Мышь", "Техника", 800));
            em.persist(new Product03("Кабель", "Техника", 120));
            em.getTransaction().commit();
            em.clear();

            // ===== Тест 1: только категория =====
            System.out.println("=== только категория (Еда) ===");
            search(em, "Еда", null, null).forEach(p ->
                    System.out.println("  " + p.getName() + " | " + p.getCategory() + " | " + p.getPrice())
            );

            // ===== Тест 2: диапазон цен =====
            System.out.println("\n=== диапазон цен (100-500) ===");
            search(em, null, 100, 500).forEach(p ->
                    System.out.println("  " + p.getName() + " | " + p.getCategory() + " | " + p.getPrice())
            );

            // ===== Тест 3: без фильтров (все) =====
            System.out.println("\n=== без фильтров (все) ===");
            search(em, null, null, null).forEach(p ->
                    System.out.println("  " + p.getName() + " | " + p.getCategory() + " | " + p.getPrice())
            );

        } finally {
            em.close();
            emf.close();
        }
    }

    /**
     * Динамический поиск товаров с фильтрацией через Criteria API.
     *
     * @param em        EntityManager
     * @param category  категория (может быть null)
     * @param minPrice  минимальная цена (может быть null)
     * @param maxPrice  максимальная цена (может быть null)
     * @return список товаров, отсортированный по имени
     */
    static List<Product03> search(EntityManager em, String category, Integer minPrice, Integer maxPrice) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Product03> cq = cb.createQuery(Product03.class);
        Root<Product03> root = cq.from(Product03.class);

        List<Predicate> filters = new ArrayList<>();

        // Добавляем предикаты только для не-null параметров
        if (category != null) {
            filters.add(cb.equal(root.get("category"), category));
        }
        if (minPrice != null) {
            filters.add(cb.ge(root.get("price"), minPrice));  // price >= minPrice
        }
        if (maxPrice != null) {
            filters.add(cb.le(root.get("price"), maxPrice));  // price <= maxPrice
        }

        // Строим запрос
        cq.select(root)
                .where(cb.and(filters.toArray(new Predicate[0])))
                .orderBy(cb.asc(root.get("name")));

        return em.createQuery(cq).getResultList();
    }
}