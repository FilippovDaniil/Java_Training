/**
 * Задача 01 — Модуль 87: HQL — параметры, getResultList/getSingleResult, пагинация
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   org.hibernate.orm:hibernate-core, com.h2database:h2 + META-INF/persistence.xml ("shop-pu").
 *
 * ЗАДАНИЕ:
 *   Засейте 6 товаров с разными категориями и ценами. Затем:
 *   1) HQL с именованными параметрами: выбрать товары категории :cat дороже :min,
 *      сортировка по цене desc; вывести.
 *   2) getSingleResult: посчитать count(p) по категории → Long.
 *   3) Пагинация: setFirstResult(2).setMaxResults(2) на "select p from Product01 p order by p.price"
 *      — получить «вторую страницу» из 2 элементов.
 *
 * ЦЕЛЬ: освоить базовый HQL: параметры, агрегаты, постраничную выборку.
 *
 * ПОДСКАЗКА: setParameter по имени (:cat), getSingleResult для одного значения,
 *            setFirstResult/setMaxResults для пагинации.
 */

import jakarta.persistence.*;
import java.util.List;

public class Task01 {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("shop-pu");
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(new Product01("Хлеб", "Еда", 40));
            em.persist(new Product01("Сыр", "Еда", 300));
            em.persist(new Product01("Молоко", "Еда", 80));
            em.persist(new Product01("Мышь", "Техника", 800));
            em.persist(new Product01("Кабель", "Техника", 120));
            em.persist(new Product01("Чай", "Еда", 150));
            em.getTransaction().commit();
            em.clear();

            // 1) параметры + сортировка
            // TODO: List<Product01> food = em.createQuery(
            // TODO:     "select p from Product01 p where p.category = :cat and p.price > :min order by p.price desc",
            // TODO:     Product01.class).setParameter("cat", "Еда").setParameter("min", 100).getResultList();
            // TODO: food.forEach(p -> System.out.println(p.getName() + " " + p.getPrice()));

            // 2) count
            // TODO: Long n = em.createQuery("select count(p) from Product01 p where p.category = :c", Long.class)
            // TODO:           .setParameter("c", "Еда").getSingleResult();
            // TODO: System.out.println("еды: " + n);

            // 3) пагинация
            // TODO: List<Product01> page = em.createQuery("select p from Product01 p order by p.price", Product01.class)
            // TODO:           .setFirstResult(2).setMaxResults(2).getResultList();
            // TODO: page.forEach(p -> System.out.println("стр.2: " + p.getName()));
        } finally {
            em.close();
            emf.close();
        }
    }
}
