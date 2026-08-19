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
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class Task07 {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("shop-pu");

        try {
            // ===== 1. Создаём товар =====
            Long id = createProduct(emf, "Ноутбук", 10);
            System.out.println("=== Товар создан: stock=10 ===");

            // ===== 2. Покупки =====
            purchase(emf, id, 3);
            System.out.println("=== После покупки 3 шт.: stock=7 ===");

            purchase(emf, id, 2);
            System.out.println("=== После покупки 2 шт.: stock=5 ===");

            // ===== 3. Снятие с продажи =====
            discontinue(emf, id);
            System.out.println("=== Товар снят с продажи ===");

            // ===== 4. Проверка, что товар скрыт =====
            EntityManager em = emf.createEntityManager();
            try {
                Product07 found = em.find(Product07.class, id);
                System.out.println("=== Поиск после soft delete: " +
                        (found == null ? "НЕ НАЙДЕН (скрыт)" : "НАЙДЕН!"));
            } finally {
                em.close();
            }

            // ===== 5. История =====
            System.out.println("\n=== ИСТОРИЯ ИЗМЕНЕНИЙ ===");
            history(emf, id);

        } finally {
            emf.close();
        }
    }

    static Long createProduct(EntityManagerFactory emf, String name, int stock) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Product07 p = new Product07(name, stock);
            em.persist(p);
            em.getTransaction().commit();
            return p.getId();
        } finally {
            em.close();
        }
    }

    static void purchase(EntityManagerFactory emf, Long id, int qty) {
        for (int attempt = 1; attempt <= 3; attempt++) {
            EntityManager em = emf.createEntityManager();
            try {
                em.getTransaction().begin();
                Product07 p = em.find(Product07.class, id);
                if (p == null) {
                    throw new IllegalStateException("Товар не найден");
                }
                if (p.getStock() < qty) {
                    throw new IllegalStateException("Недостаточно остатка");
                }
                p.setStock(p.getStock() - qty);
                em.getTransaction().commit();
                System.out.println("  ✅ Покупка " + qty + " шт. успешна (попытка " + attempt + ")");
                return;
            } catch (OptimisticLockException e) {
                System.out.println("  ⚠️ Конфликт версии! Попытка " + attempt + " не удалась");
                if (em.getTransaction().isActive()) {
                    em.getTransaction().rollback();
                }
            } catch (Exception e) {
                if (em.getTransaction().isActive()) {
                    em.getTransaction().rollback();
                }
                throw e;
            } finally {
                em.close();
            }
        }
        throw new IllegalStateException("Не удалось выполнить покупку после 3 попыток");
    }

    static void discontinue(EntityManagerFactory emf, Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Product07 p = em.find(Product07.class, id);
            if (p == null) {
                throw new IllegalStateException("Товар не найден");
            }
            em.remove(p);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    static void history(EntityManagerFactory emf, Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            AuditReader reader = AuditReaderFactory.get(em);
            List<Number> revisions = reader.getRevisions(Product07.class, id);

            if (revisions.isEmpty()) {
                System.out.println("  ⚠️ Нет истории для товара с id=" + id);
                return;
            }

            for (Number rev : revisions) {
                Product07 p = reader.find(Product07.class, id, rev);
                if (p != null) {
                    System.out.println("  Ревизия " + rev + ": stock=" + p.getStock() +
                            ", deleted=" + p.isDeleted() +
                            ", version=" + p.getVersion());
                } else {
                    System.out.println("  Ревизия " + rev + ": данные не найдены");
                }
            }
        } finally {
            em.close();
        }
    }
}
