/**
 * Задача 01 — Модуль 91: JDBC batching (batch_size + SEQUENCE)
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   org.hibernate.orm:hibernate-core, com.h2database:h2 + META-INF/persistence.xml ("shop-pu").
 *   В persistence.xml: hibernate.jdbc.batch_size=50, hibernate.order_inserts=true,
 *                      hibernate.show_sql=true.
 *
 * ЗАДАНИЕ:
 *   1) Product01 использует SEQUENCE-генератор (НЕ IDENTITY — она отключает батчинг вставок!):
 *        @GeneratedValue(strategy = SEQUENCE, generator = "prod_seq")
 *        @SequenceGenerator(name = "prod_seq", sequenceName = "prod_seq", allocationSize = 50)
 *   2) В одной транзакции вставьте 200 товаров в цикле.
 *   3) В логе (с batch_size=50) INSERT'ы пойдут пачками, а не 200 отдельных round-trip.
 *   4) В комментарии зафиксируйте: почему IDENTITY несовместима с батч-вставкой.
 *
 * ЦЕЛЬ: ускорить массовую вставку батчингом и понять связь стратегии id и батчинга.
 *
 * ПОДСКАЗКА: allocationSize у SEQUENCE стоит согласовать с batch_size (меньше обращений
 *            к последовательности).
 */

import jakarta.persistence.*;

public class Task01 {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("shop-pu");
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            // TODO: for (int i = 1; i <= 200; i++) em.persist(new Product01("Товар-" + i));
            em.getTransaction().commit();
            // TODO: System.out.println("200 товаров вставлены пачками по 50 (см. show_sql)");
        } finally {
            em.close();
            emf.close();
        }
    }
}
