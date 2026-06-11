package m85_hibernate_deep_dive_lifecycle.practice.task07;

/**
 * Задача 07 — Модуль 85: МИНИ-ПРОЕКТ «Лаборатория жизненного цикла сущности»
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   org.hibernate.orm:hibernate-core, com.h2database:h2 + META-INF/persistence.xml ("shop-pu").
 *
 * ЦЕЛЬ: провести ОДИН товар через ВСЕ четыре состояния, на каждом шаге печатая
 *       его состояние, и доказать поведение каждого перехода.
 *
 * ЗАДАНИЕ — реализуйте метод state(em, p), возвращающий строку состояния:
 *   - если p.getId() == null && !em.contains(p)            → "TRANSIENT"
 *   - если em.contains(p)                                  → "MANAGED"
 *   - если p.getId() != null && !em.contains(p)            → "DETACHED"
 *   (removed отдельно отметьте по факту вызова remove до commit)
 *
 * СЦЕНАРИЙ (печатайте состояние после каждого шага):
 *   1) Product07 p = new Product07("Гаджет", 999);     → TRANSIENT
 *   2) em.persist(p) (в транзакции)                    → MANAGED
 *   3) p.setPrice(899); commit                         → MANAGED, в БД UPDATE (dirty checking)
 *   4) em.detach(p); p.setPrice(0)                     → DETACHED, изменение НЕ уйдёт в БД
 *   5) перечитать в новой транзакции                    → цена 899 (detached-правка пропала)
 *   6) Product07 m = em.merge(p) с p.setPrice(799)      → m MANAGED, commit → в БД 799
 *      (учтите: merge вернул m; исходный p остался DETACHED)
 *   7) em.remove(m); commit                             → REMOVED → find вернёт null
 *
 * ИТОГ: распечатанная «трасса состояний» TRANSIENT→MANAGED→...→REMOVED + проверки в БД
 *       (899 после dirty checking, 899 после detached-правки, 799 после merge, null после remove).
 *
 * ПОДСКАЗКА: соберите Task01 (transient/managed), Task02 (dirty checking), Task03 (detached/merge),
 *            Task04 (remove). Используйте em.contains() и getId() для определения состояния.
 */

import jakarta.persistence.*;

public class Task07 {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("shop-pu");
        EntityManager em = emf.createEntityManager();
        try {
            // TODO: реализуйте сценарий 1–7, печатая state(em, p) после каждого шага
            // TODO: и проверяя значения в БД через em.find в отдельных транзакциях.
        } finally {
            em.close();
            emf.close();
        }
    }

    /** Определяет состояние сущности по контексту и наличию id. */
    static String state(EntityManager em, Product07 p) {
        // TODO: if (em.contains(p)) return "MANAGED";
        // TODO: if (p.getId() == null) return "TRANSIENT";
        // TODO: return "DETACHED";
        return "?";
    }
}
