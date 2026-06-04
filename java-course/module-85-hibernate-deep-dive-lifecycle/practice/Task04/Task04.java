/**
 * Задача 04 — Модуль 85: removed (em.remove) и порядок удаления
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   org.hibernate.orm:hibernate-core, com.h2database:h2 + META-INF/persistence.xml ("shop-pu").
 *
 * ЗАДАНИЕ:
 *   1) Сохраните Product04("Старьё", 10).
 *   2) В транзакции: найдите managed-объект, вызовите em.remove(p) — он переходит в removed
 *      (DELETE откладывается до flush). До commit em.contains(p) ещё true.
 *   3) Закоммитьте — выполнится DELETE.
 *   4) Перечитайте em.find — должно вернуть null (запись удалена).
 *   5) Доп.: попробуйте em.remove на detached-объекте → должно бросить
 *      IllegalArgumentException (remove применим к managed). Поймайте и напечатайте.
 *
 * ЦЕЛЬ: понять состояние removed и что remove работает только с managed-сущностью.
 *
 * ПОДСКАЗКА: чтобы получить detached-объект для п.5 — em.detach(найденного) перед remove.
 */

import jakarta.persistence.*;

public class Task04 {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("shop-pu");
        EntityManager em = emf.createEntityManager();
        Long id;
        try {
            em.getTransaction().begin();
            Product04 p = new Product04("Старьё", 10);
            em.persist(p);
            em.getTransaction().commit();
            id = p.getId();

            // TODO: em.getTransaction().begin();
            // TODO: Product04 managed = em.find(Product04.class, id);
            // TODO: em.remove(managed);
            // TODO: System.out.println("contains после remove (до commit) = " + em.contains(managed));
            // TODO: em.getTransaction().commit();   // DELETE
            // TODO: System.out.println("после удаления find = " + em.find(Product04.class, id)); // null

            // TODO (п.5): попытка remove detached:
            // TODO: try { Product04 d = new Product04("x", 1); d ... em.remove(d); }
            // TODO: catch (IllegalArgumentException e) { System.out.println("remove detached → " + e.getClass().getSimpleName()); }
        } finally {
            em.close();
            emf.close();
        }
    }
}
