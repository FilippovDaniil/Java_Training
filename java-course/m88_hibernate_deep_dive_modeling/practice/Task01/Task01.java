package m88_hibernate_deep_dive_modeling.practice.task01;

/**
 * Задача 01 — Модуль 88: ловушка equals/hashCode по generated id; бизнес-ключ
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   org.hibernate.orm:hibernate-core, com.h2database:h2 + META-INF/persistence.xml ("shop-pu").
 *
 * ЗАДАНИЕ:
 *   ЧАСТЬ A — ВОСПРОИЗВЕСТИ ПРОБЛЕМУ (BrokenProduct с equals/hashCode по id):
 *     1) Создайте BrokenProduct (id == null), положите в HashSet.
 *     2) em.persist → id присвоен → hashCode изменился.
 *     3) set.contains(p) → false (объект «потерян»). Выведите этот результат.
 *
 *   ЧАСТЬ B — ИСПРАВИТЬ (GoodProduct01 с equals/hashCode по sku):
 *     4) В GoodProduct01 реализуйте equals/hashCode ПО ПОЛЮ sku (бизнес-ключ).
 *     5) Повторите сценарий: до и после persist set.contains(p) → true (стабилен).
 *
 * ЦЕЛЬ: на практике доказать, почему equals/hashCode по generated id ломает HashSet.
 *
 * ПОДСКАЗКА: sku задаётся в конструкторе и не меняется → hashCode стабилен до/после persist.
 */

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Task01 {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("shop-pu");
        EntityManager em = emf.createEntityManager();
        try {
            // ЧАСТЬ A
            em.getTransaction().begin();
            BrokenProduct b = new BrokenProduct("Молоко");
            Set<BrokenProduct> brokenSet = new HashSet<>();
            brokenSet.add(b);
            // TODO: em.persist(b);   // id присвоен → hashCode по id изменился
            // TODO: System.out.println("BROKEN contains после persist: " + brokenSet.contains(b)); // false
            em.getTransaction().commit();

            // ЧАСТЬ B
            em.getTransaction().begin();
            GoodProduct01 g = new GoodProduct01("SKU-1", "Молоко");
            Set<GoodProduct01> goodSet = new HashSet<>();
            goodSet.add(g);
            // TODO: em.persist(g);
            // TODO: System.out.println("GOOD contains после persist: " + goodSet.contains(g)); // true
            em.getTransaction().commit();
        } finally {
            em.close();
            emf.close();
        }
    }
}
