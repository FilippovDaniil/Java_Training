/**
 * Задача 07 — Модуль 87: МИНИ-ПРОЕКТ «Один запрос — три языка + управление связью»
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   org.hibernate.orm:hibernate-core, com.h2database:h2 + META-INF/persistence.xml ("shop-pu").
 *
 * ЦЕЛЬ: один и тот же бизнес-вопрос — «товары категории X дороже N, по убыванию цены» —
 *       выразить ТРЕМЯ способами (HQL, Criteria, native SQL) и убедиться, что результат
 *       одинаков; плюс корректно управлять двунаправленной связью Category—Product.
 *
 * ЗАДАНИЕ (всё в одном файле):
 *   Засейте 2 категории; в "Еда" — 4 товара (40/80/150/300), в "Техника" — 2 (120/800).
 *   Используйте Category07.addProduct(...) (синхронизирует обе стороны!).
 *
 *   Реализуйте три метода, каждый возвращает List<Product07> «Еда, цена > 100, desc»:
 *     1) byHql(em)      — "select p from Product07 p where p.category.name = :c and p.price > :min order by p.price desc"
 *     2) byCriteria(em) — CriteriaBuilder/Query/Root + предикаты equal+gt + orderBy desc
 *                         (join к категории: root.get("category").get("name"))
 *     3) byNative(em)   — native SQL с JOIN products↔categories, маппинг в Product07.class
 *
 *   В main вызовите все три и выведите названия — списки должны совпасть
 *   (Сыр 300, Чай 150 — в порядке убывания цены).
 *
 * АРХИТЕКТУРА:
 *   HQL      → читаемый статический запрос
 *   Criteria → то же, но собирается типобезопасно (годится для динамики)
 *   native   → когда нужен диалект СУБД; маппинг строк в сущности
 *
 * ОЖИДАЕМЫЙ ИТОГ: три метода вернули один и тот же набор [Сыр(300), Чай(150)].
 *
 * ПОДСКАЗКА: соберите Task01 (HQL), Task03 (Criteria), Task04 (native), Task05 (owning side).
 */

import jakarta.persistence.*;
import jakarta.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

public class Task07 {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("shop-pu");
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Category07 food = new Category07("Еда");
            food.addProduct(new Product07("Хлеб", 40));
            food.addProduct(new Product07("Молоко", 80));
            food.addProduct(new Product07("Чай", 150));
            food.addProduct(new Product07("Сыр", 300));
            Category07 tech = new Category07("Техника");
            tech.addProduct(new Product07("Кабель", 120));
            tech.addProduct(new Product07("Мышь", 800));
            em.persist(food); em.persist(tech);
            em.getTransaction().commit();
            em.clear();

            // TODO: System.out.println("HQL:");      byHql(em).forEach(p -> System.out.println(p.getName() + " " + p.getPrice()));
            // TODO: System.out.println("Criteria:"); byCriteria(em).forEach(p -> System.out.println(p.getName() + " " + p.getPrice()));
            // TODO: System.out.println("native:");   byNative(em).forEach(p -> System.out.println(p.getName() + " " + p.getPrice()));
        } finally {
            em.close();
            emf.close();
        }
    }

    static List<Product07> byHql(EntityManager em) {
        // TODO: return em.createQuery("select p from Product07 p where p.category.name = :c " +
        // TODO:     "and p.price > :min order by p.price desc", Product07.class)
        // TODO:     .setParameter("c", "Еда").setParameter("min", 100).getResultList();
        return List.of();
    }

    static List<Product07> byCriteria(EntityManager em) {
        // TODO: CriteriaBuilder cb = em.getCriteriaBuilder();
        // TODO: CriteriaQuery<Product07> cq = cb.createQuery(Product07.class);
        // TODO: Root<Product07> root = cq.from(Product07.class);
        // TODO: cq.select(root).where(
        // TODO:     cb.equal(root.get("category").get("name"), "Еда"),
        // TODO:     cb.gt(root.get("price"), 100))
        // TODO:   .orderBy(cb.desc(root.get("price")));
        // TODO: return em.createQuery(cq).getResultList();
        return List.of();
    }

    @SuppressWarnings("unchecked")
    static List<Product07> byNative(EntityManager em) {
        // TODO: return em.createNativeQuery(
        // TODO:     "SELECT p.* FROM products p JOIN categories c ON p.category_id = c.id " +
        // TODO:     "WHERE c.name = :c AND p.price > :min ORDER BY p.price DESC", Product07.class)
        // TODO:     .setParameter("c", "Еда").setParameter("min", 100).getResultList();
        return List.of();
    }
}
