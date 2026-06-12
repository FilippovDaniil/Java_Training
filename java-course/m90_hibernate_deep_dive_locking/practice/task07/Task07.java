package m90_hibernate_deep_dive_locking.practice.task07;

/**
 * Задача 07 — Модуль 90: МИНИ-ПРОЕКТ «Надёжный склад: блокировка + soft delete + аудит»
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   org.hibernate.orm:hibernate-core, org.hibernate.orm:hibernate-envers, com.h2database:h2
 *   + META-INF/persistence.xml ("shop-pu").
 *
 * ЦЕЛЬ: собрать сущность склада «как в проде»: защита от конкуренции (@Version с ретраем),
 *       мягкое удаление (списанные товары не теряются) и полный аудит истории (Envers).
 *
 * ЗАДАНИЕ (всё в одном файле):
 *
 *   Product07:
 *     - @Id @GeneratedValue Long id; String name; int stock;
 *     - @Version long version;                      // оптимистичная блокировка
 *     - boolean deleted = false;
 *     - @SQLDelete(...) + @SQLRestriction("deleted = false")   // soft delete
 *     - @Audited                                    // история изменений
 *
 *   Сервисные методы (статические, принимают EntityManagerFactory — каждая операция в своей транзакции):
 *     1) purchase(emf, id, qty): в транзакции загрузить, проверить остаток, списать;
 *        при OptimisticLockException — ретрай (до 3 попыток, re-read).
 *     2) discontinue(emf, id): em.remove(товар) → мягкое удаление (UPDATE deleted=true).
 *     3) history(emf, id): через AuditReader вывести stock на каждой ревизии.
 *
 *   СЦЕНАРИЙ в main:
 *     - создать товар stock=10;
 *     - purchase 3 → остаток 7 (ревизия растёт);
 *     - purchase 2 → остаток 5;
 *     - discontinue → товар скрыт из обычных запросов, но физически в БД;
 *     - history → распечатать историю остатков (10 → 7 → 5 ...).
 *
 * АРХИТЕКТУРА:
 *   purchase ──@Version──► конфликт? ──ретрай──► списание
 *   discontinue ──@SQLDelete──► UPDATE deleted=true (видимость скрыта @SQLRestriction)
 *   все изменения ──@Audited──► история в products_AUD (AuditReader для отчёта)
 *
 * ОЖИДАЕМЫЙ ИТОГ:
 *   остаток корректно списан под защитой версии; «снятый с продажи» товар не виден
 *   обычным запросам, но есть в истории; AuditReader показывает эволюцию остатка.
 *
 * ПОДСКАЗКА: соберите Task01 (ретрай по @Version), Task05 (soft delete), Task06 (Envers).
 */

import jakarta.persistence.*;
import java.util.List;

public class Task07 {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("shop-pu");
        try {
            // TODO: создать товар stock=10 (запомнить id)
            // TODO: purchase(emf, id, 3);  // 7
            // TODO: purchase(emf, id, 2);  // 5
            // TODO: discontinue(emf, id);  // скрыт, но в истории есть
            // TODO: history(emf, id);      // распечатать историю остатков
        } finally {
            emf.close();
        }
    }

    static void purchase(EntityManagerFactory emf, Long id, int qty) {
        // TODO: for (int attempt = 0; attempt < 3; attempt++) {
        // TODO:     EntityManager em = emf.createEntityManager();
        // TODO:     try {
        // TODO:         em.getTransaction().begin();
        // TODO:         Product07 p = em.find(Product07.class, id);
        // TODO:         if (p.getStock() < qty) throw new IllegalStateException("Недостаточно остатка");
        // TODO:         p.setStock(p.getStock() - qty);
        // TODO:         em.getTransaction().commit();
        // TODO:         return;
        // TODO:     } catch (OptimisticLockException e) { /* re-read на след. итерации */ }
        // TODO:     finally { em.close(); }
        // TODO: }
        // TODO: throw new IllegalStateException("Не удалось после 3 попыток");
    }

    static void discontinue(EntityManagerFactory emf, Long id) {
        // TODO: EntityManager em = emf.createEntityManager();
        // TODO: em.getTransaction().begin(); em.remove(em.find(Product07.class, id)); em.getTransaction().commit(); em.close();
    }

    static void history(EntityManagerFactory emf, Long id) {
        // TODO: EntityManager em = emf.createEntityManager();
        // TODO: AuditReader reader = AuditReaderFactory.get(em);
        // TODO: for (Number rev : reader.getRevisions(Product07.class, id))
        // TODO:     System.out.println("рев. " + rev + ": stock=" + reader.find(Product07.class, id, rev).getStock());
        // TODO: em.close();
    }
}
