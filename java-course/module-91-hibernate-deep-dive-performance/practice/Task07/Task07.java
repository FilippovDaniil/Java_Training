/**
 * Задача 07 — Модуль 91: МИНИ-ПРОЕКТ «Эффективный импорт каталога shop-data-jpa»
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   org.hibernate.orm:hibernate-core, com.h2database:h2 + META-INF/persistence.xml ("shop-pu").
 *   В persistence.xml: hibernate.jdbc.batch_size=50, hibernate.order_inserts=true,
 *                      hibernate.generate_statistics=true.
 *
 * ЦЕЛЬ: собрать производительный сценарий загрузки и чтения каталога, применив ВСЕ
 *       приёмы модуля: SEQUENCE + batching + flush/clear, bulk-обновление, read-only отчёт.
 *
 * ЗАДАНИЕ (всё в одном файле):
 *
 *   Product07: SEQUENCE-id (для батчинга), поля name, category, price.
 *
 *   1) importProducts(em, count): в одной транзакции вставить count товаров
 *      (чередуя категории "Еда"/"Техника", цена = i), каждые 50 — flush()+clear().
 *
 *   2) raisePrices(em, category, delta): bulk-UPDATE цены по категории одним запросом
 *      + em.clear() после. Вернуть число обновлённых.
 *
 *   3) report(em): read-only выборка top-5 самых дорогих товаров
 *      ("select p from Product07 p order by p.price desc" + hint readOnly + setMaxResults(5));
 *      вывести их.
 *
 *   4) В конце через Statistics выведите счётчики:
 *        Statistics st = em.getEntityManagerFactory().unwrap(SessionFactory.class).getStatistics();
 *        st.getEntityInsertCount(), st.getQueryExecutionCount() и т.п.
 *
 *   СЦЕНАРИЙ: importProducts(em, 1000) → raisePrices("Еда", 10) → report() → статистика.
 *
 * АРХИТЕКТУРА:
 *   импорт     → SEQUENCE + batch_size + flush/clear (память стабильна, INSERT пачками)
 *   массовое   → bulk UPDATE (одна команда вместо N) + clear()
 *   отчёт      → read-only (без dirty-checking снимков) + пагинация (top-5)
 *   контроль   → Statistics (вставки/запросы)
 *
 * ОЖИДАЕМЫЙ ИТОГ:
 *   1000 товаров импортированы пачками; цены "Еда" подняты одним UPDATE; отчёт из 5 строк;
 *   статистика показывает разумное число запросов (НЕ тысячи).
 *
 * ПОДСКАЗКА: соберите Task01/02 (batching+flush/clear), Task03 (bulk), Task04 (read-only),
 *            Task06 (Statistics).
 */

import jakarta.persistence.*;
import org.hibernate.SessionFactory;
import org.hibernate.stat.Statistics;
import java.util.List;

public class Task07 {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("shop-pu");
        EntityManager em = emf.createEntityManager();
        try {
            // TODO: importProducts(em, 1000);
            // TODO: int n = raisePrices(em, "Еда", 10); System.out.println("обновлено: " + n);
            // TODO: report(em);
            // TODO: Statistics st = emf.unwrap(SessionFactory.class).getStatistics();
            // TODO: System.out.println("inserts=" + st.getEntityInsertCount() +
            // TODO:                    " queries=" + st.getQueryExecutionCount());
        } finally {
            em.close();
            emf.close();
        }
    }

    static void importProducts(EntityManager em, int count) {
        // TODO: em.getTransaction().begin();
        // TODO: for (int i = 1; i <= count; i++) {
        // TODO:     em.persist(new Product07("Товар-" + i, i % 2 == 0 ? "Еда" : "Техника", i));
        // TODO:     if (i % 50 == 0) { em.flush(); em.clear(); }
        // TODO: }
        // TODO: em.getTransaction().commit();
    }

    static int raisePrices(EntityManager em, String category, int delta) {
        // TODO: em.getTransaction().begin();
        // TODO: int n = em.createQuery("update Product07 p set p.price = p.price + :d where p.category = :c")
        // TODO:     .setParameter("d", delta).setParameter("c", category).executeUpdate();
        // TODO: em.getTransaction().commit();
        // TODO: em.clear();
        // TODO: return n;
        return 0;
    }

    static void report(EntityManager em) {
        // TODO: List<Product07> top = em.createQuery("select p from Product07 p order by p.price desc", Product07.class)
        // TODO:     .setHint("org.hibernate.readOnly", true).setMaxResults(5).getResultList();
        // TODO: top.forEach(p -> System.out.println(p.getName() + " " + p.getPrice()));
    }
}
