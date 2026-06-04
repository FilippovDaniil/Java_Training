/**
 * Задача 05 — Модуль 86: batch fetching (@BatchSize / @Fetch(SUBSELECT))
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   org.hibernate.orm:hibernate-core, com.h2database:h2 + META-INF/persistence.xml ("shop-pu").
 *   hibernate.show_sql=true.
 *
 * ЗАДАНИЕ:
 *   1) На поле products в Category05 добавьте Hibernate-аннотацию батч-загрузки.
 *      Вариант A: @org.hibernate.annotations.BatchSize(size = 10)
 *      Вариант B: @org.hibernate.annotations.Fetch(FetchMode.SUBSELECT)
 *      (раскомментируйте ОДИН из вариантов и сравните логи).
 *   2) Засейте 5 категорий по 2 товара.
 *   3) Загрузите все категории обычным "select c from Category05 c", затем в цикле
 *      трогайте products каждой.
 *   4) Подсчитайте запросы:
 *        - без аннотаций было бы 1 + 5 (N+1);
 *        - с @BatchSize(10): 1 + 1 (все коллекции одним IN(...));
 *        - с SUBSELECT: 1 + 1 (догрузка под-запросом для всех корней).
 *
 * ЦЕЛЬ: освоить batch fetching как способ смягчить N+1 без переписывания запросов.
 *
 * ПОДСКАЗКА: эти аннотации специфичны для Hibernate (org.hibernate.annotations.*),
 *            в чистом JPA их нет.
 */

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class Task05 {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("shop-pu");
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            for (int i = 1; i <= 5; i++) {
                Category05 c = new Category05("Категория-" + i);
                c.addProduct(new Product05("Товар-" + i + "-1"));
                c.addProduct(new Product05("Товар-" + i + "-2"));
                em.persist(c);
            }
            em.getTransaction().commit();
            em.clear();

            // TODO: em.getTransaction().begin();
            // TODO: List<Category05> cats = em.createQuery("select c from Category05 c", Category05.class)
            // TODO:                            .getResultList();
            // TODO: for (Category05 c : cats) c.getProducts().size();   // догрузка батчем
            // TODO: em.getTransaction().commit();
            // TODO: System.out.println("Сравните число SELECT в логе для BatchSize vs SUBSELECT vs без них");
        } finally {
            em.close();
            emf.close();
        }
    }
}
