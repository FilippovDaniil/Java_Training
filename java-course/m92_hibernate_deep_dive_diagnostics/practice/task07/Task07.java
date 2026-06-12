package m92_hibernate_deep_dive_diagnostics.practice.task07;

/**
 * Задача 07 — Модуль 92: КАПСТОУН «Финальный аудит производительности shop-data-jpa»
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   org.hibernate.orm:hibernate-core, com.h2database:h2 + META-INF/persistence.xml ("shop-pu").
 *   hibernate.generate_statistics=true, hibernate.show_sql=true, hibernate.format_sql=true.
 *
 * ЦЕЛЬ (финал всего persistence-трека 77–92): провести АУДИТ типичного набора операций
 *       каталога, измерить их через Statistics, выявить и устранить N+1, и подтвердить
 *       соответствие чек-листу аудита из theory.md.
 *
 * МОДЕЛЬ: Category07 (1) —— (N) Product07, обе связи LAZY.
 *
 * ЗАДАНИЕ (всё в одном файле):
 *
 *   1) seed(em): засеять 10 категорий по 5 товаров (через addProduct — обе стороны).
 *
 *   2) Аудируемые операции (каждую обернуть в audit(name, st, op), печатающий имя +
 *      getPrepareStatementCount() + getEntityLoadCount()):
 *        a) listBad(em)   — "select c from Category07 c" + цикл по products (N+1: 1+10=11);
 *        b) listGood(em)  — "select distinct c ... join fetch c.products" (1 запрос);
 *        c) overview(em)  — DTO-проекция "select new CatRow07(c.name, count(p)) ..." (1, без сущностей);
 *        d) raise(em)     — bulk "update Product07 p set p.price=p.price+1" (1 UPDATE) + clear().
 *
 *   3) Сформируйте отчёт: для каждой операции — число SQL; отметьте listBad как НАРУШЕНИЕ
 *      (N+1), остальные — OK.
 *
 *   4) Напечатайте итог чек-листа (по пунктам из theory.md), отметив, что в правильных
 *      операциях: связи LAZY + точечный fetch, нет N+1, read-модель через DTO, массовое — bulk.
 *
 * АРХИТЕКТУРА АУДИТА:
 *   Statistics.clear() → операция → getPrepareStatementCount()/getEntityLoadCount()
 *        │
 *   сравнение с ожиданием (1 запрос = OK; 1+N = N+1-нарушение)
 *        │
 *   отчёт + чек-лист готовности к проду
 *
 * ОЖИДАЕМЫЙ ИТОГ (примерно):
 *   listBad  → 11 запросов  [НАРУШЕНИЕ: N+1]
 *   listGood → 1 запрос     [OK]
 *   overview → 1 запрос     [OK, DTO]
 *   raise    → 1 запрос     [OK, bulk]
 *   Вывод: устранение N+1 (11 → 1) — главный эффект; правильные приёмы подтверждены метрикой.
 *
 * ПОДСКАЗКА: это синтез модулей 86 (fetch), 87 (bulk/проекции), 91 (производительность),
 *            92 (Statistics). Соберите всё вместе и докажите числами.
 */

import jakarta.persistence.*;
import org.hibernate.SessionFactory;
import org.hibernate.stat.Statistics;
import java.util.ArrayList;
import java.util.List;

public class Task07 {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("shop-pu");
        EntityManager em = emf.createEntityManager();
        try {
            // TODO: seed(em);
            Statistics st = emf.unwrap(SessionFactory.class).getStatistics();
            // TODO: audit("listBad",  em, st, () -> listBad(em));   // ~11 [N+1]
            // TODO: audit("listGood", em, st, () -> listGood(em));  // 1  [OK]
            // TODO: audit("overview", em, st, () -> overview(em));  // 1  [OK, DTO]
            // TODO: audit("raise",    em, st, () -> raise(em));     // 1  [OK, bulk]
            // TODO: напечатать итоговый чек-лист готовности (см. theory.md)
        } finally {
            em.close();
            emf.close();
        }
    }

    static void seed(EntityManager em) {
        // TODO: em.getTransaction().begin();
        // TODO: for (int i = 1; i <= 10; i++) {
        // TODO:     Category07 c = new Category07("Кат-" + i);
        // TODO:     for (int j = 1; j <= 5; j++) c.addProduct(new Product07("Т-" + i + "-" + j, i * 10 + j));
        // TODO:     em.persist(c);
        // TODO: }
        // TODO: em.getTransaction().commit(); em.clear();
    }

    static void audit(String name, EntityManager em, Statistics st, Runnable op) {
        // TODO: st.clear();
        // TODO: em.getTransaction().begin();
        // TODO: op.run();
        // TODO: em.getTransaction().commit(); em.clear();
        // TODO: System.out.println(name + ": statements=" + st.getPrepareStatementCount()
        // TODO:     + " entityLoads=" + st.getEntityLoadCount());
    }

    static void listBad(EntityManager em) {
        // TODO: em.createQuery("select c from Category07 c", Category07.class)
        // TODO:   .getResultList().forEach(c -> c.getProducts().size());   // N+1
    }

    static void listGood(EntityManager em) {
        // TODO: em.createQuery("select distinct c from Category07 c join fetch c.products", Category07.class)
        // TODO:   .getResultList().forEach(c -> c.getProducts().size());   // 1 запрос
    }

    static void overview(EntityManager em) {
        // TODO: em.createQuery("select new CatRow07(c.name, count(p)) from Category07 c " +
        // TODO:   "left join c.products p group by c.id, c.name", CatRow07.class).getResultList();
    }

    static void raise(EntityManager em) {
        // TODO: em.createQuery("update Product07 p set p.price = p.price + 1").executeUpdate();
        // TODO: em.clear();
    }
}
