package m86_hibernate_deep_dive_fetching.practice.task03;

/**
 * Задача 03 — Модуль 86: JOIN FETCH в HQL (устранение N+1)
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   org.hibernate.orm:hibernate-core, com.h2database:h2 + META-INF/persistence.xml ("shop-pu").
 *   hibernate.show_sql=true.
 *
 * ЗАДАНИЕ:
 *   1) Засейте 3 категории по 2 товара.
 *   2) Загрузите их ОДНИМ запросом с join fetch:
 *        "select distinct c from Category03 c join fetch c.products"
 *   3) В цикле выведите имя + products.size() — БЕЗ дополнительных запросов.
 *   4) Сравните с Task02: теперь в логе ОДИН SELECT с JOIN.
 *   5) Доп.: уберите distinct и посмотрите на дубли категорий в результате — объясните,
 *      почему distinct нужен (JOIN размножает строки корня по числу товаров).
 *
 * ЦЕЛЬ: освоить JOIN FETCH на уровне HQL как основной приём против N+1.
 *
 * ПОДСКАЗКА: имя сущности в HQL — Category03 (класс), не имя таблицы.
 */

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class Task03 {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("shop-pu");
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            for (String n : new String[]{"Электроника", "Книги", "Одежда"}) {
                Category03 c = new Category03(n);
                c.addProduct(new Product03(n + "-1"));
                c.addProduct(new Product03(n + "-2"));
                em.persist(c);
            }
            em.getTransaction().commit();
            em.clear();

            // TODO: List<Category03> cats = em.createQuery(
            // TODO:     "select distinct c from Category03 c join fetch c.products", Category03.class)
            // TODO:     .getResultList();   // ОДИН SELECT с JOIN
            // TODO: for (Category03 c : cats)
            // TODO:     System.out.println(c.getName() + ": " + c.getProducts().size());
        } finally {
            em.close();
            emf.close();
        }
    }
}
