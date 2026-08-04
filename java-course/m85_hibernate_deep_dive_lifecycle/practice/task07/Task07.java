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
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class Task07 {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("shop-pu");
        EntityManager em = emf.createEntityManager();
        try {
            // ===== ШАГ 1: TRANSIENT =====
            Product07 p = new Product07("Гаджет", 999);
            System.out.println("1) После new: " + state(em, p));  // TRANSIENT

            // ===== ШАГ 2: TRANSIENT → MANAGED (persist) =====
            em.getTransaction().begin();
            em.persist(p);
            System.out.println("2) После persist: " + state(em, p));  // MANAGED
            em.getTransaction().commit();

            // ===== ШАГ 3: Dirty checking (MANAGED) =====
            em.getTransaction().begin();
            p.setPrice(899);  // dirty checking → UPDATE при commit
            System.out.println("3) После setPrice(899): " + state(em, p));  // MANAGED
            em.getTransaction().commit();

            // Проверяем, что цена обновилась в БД
            Product07 check1 = em.find(Product07.class, p.getId());
            System.out.println("   В БД после dirty checking: price=" + check1.getPrice());  // 899

            // ===== ШАГ 4: MANAGED → DETACHED (detach) =====
            em.detach(p);
            System.out.println("4) После detach: " + state(em, p));  // DETACHED

            p.setPrice(0);  // Изменение НЕ уйдёт в БД (detached)
            System.out.println("   После setPrice(0) в detached: " + state(em, p));  // DETACHED

            // Проверяем, что цена НЕ изменилась в БД
            Product07 check2 = em.find(Product07.class, p.getId());
            System.out.println("   В БД после detached-правки: price=" + check2.getPrice());  // 899

            // ===== ШАГ 5: DETACHED → MANAGED (merge) =====
            p.setPrice(799);
            em.getTransaction().begin();
            Product07 m = em.merge(p);  // merge возвращает MANAGED-версию
            System.out.println("5) После merge: p.state=" + state(em, p) + ", m.state=" + state(em, m));  // DETACHED, MANAGED
            em.getTransaction().commit();

            // Проверяем, что цена обновилась в БД
            Product07 check3 = em.find(Product07.class, p.getId());
            System.out.println("   В БД после merge: price=" + check3.getPrice());  // 799

            // ===== ШАГ 6: MANAGED → REMOVED (remove) =====
            em.getTransaction().begin();
            em.remove(m);
            System.out.println("6) После remove: " + state(em, m));  // MANAGED (но помечен на удаление)
            em.getTransaction().commit();

            // После commit объект REMOVED (не управляется, но id есть)
            System.out.println("   После commit: contains=" + em.contains(m) + ", id=" + m.getId());

            // ===== ШАГ 7: Проверка, что объект удалён из БД =====
            Product07 check4 = em.find(Product07.class, p.getId());
            System.out.println("7) После удаления: em.find() вернул " + check4);  // null

        } finally {
            em.close();
            emf.close();
        }
    }

    /**
     * Определяет состояние сущности по контексту и наличию id.
     */
    static String state(EntityManager em, Product07 p) {
        if (em.contains(p)) {
            return "MANAGED";
        }
        if (p.getId() == null) {
            return "TRANSIENT";
        }
        return "DETACHED";
    }
}