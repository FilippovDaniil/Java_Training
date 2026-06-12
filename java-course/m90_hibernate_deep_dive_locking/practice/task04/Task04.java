package m90_hibernate_deep_dive_locking.practice.task04;

/**
 * Задача 04 — Модуль 90: таймаут пессимистичной блокировки
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   org.hibernate.orm:hibernate-core, com.h2database:h2 + META-INF/persistence.xml ("shop-pu").
 *
 * ЗАДАНИЕ:
 *   Блокировка без ограничения времени опасна (вечное ожидание/дедлок). Зададим таймаут.
 *   1) Сохраните Product04("Лимитированный", 5).
 *   2) Загрузите товар с PESSIMISTIC_WRITE и таймаутом блокировки:
 *        Map<String,Object> hints = Map.of("jakarta.persistence.lock.timeout", 2000); // мс
 *        Product04 p = em.find(Product04.class, id, LockModeType.PESSIMISTIC_WRITE, hints);
 *   3) Выполните списание и commit.
 *   4) В комментарии опишите сценарий дедлока и почему таймаут/единый порядок захвата
 *      строк его предотвращают.
 *
 * ЦЕЛЬ: научиться ограничивать ожидание блокировки и осознавать риск дедлоков.
 *
 * ПОДСКАЗКА: поддержка таймаута зависит от СУБД; H2/Postgres его понимают.
 *            При превышении ожидания обычно бросается LockTimeoutException.
 */

import jakarta.persistence.*;
import java.util.Map;

public class Task04 {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("shop-pu");
        EntityManager em = emf.createEntityManager();
        Long id;
        try {
            em.getTransaction().begin();
            Product04 p = new Product04("Лимитированный", 5);
            em.persist(p);
            em.getTransaction().commit();
            id = p.getId();
            em.clear();

            // TODO: em.getTransaction().begin();
            // TODO: Map<String, Object> hints = Map.of("jakarta.persistence.lock.timeout", 2000);
            // TODO: Product04 locked = em.find(Product04.class, id, LockModeType.PESSIMISTIC_WRITE, hints);
            // TODO: locked.setStock(locked.getStock() - 1);
            // TODO: em.getTransaction().commit();
            // TODO: System.out.println("остаток = " + em.find(Product04.class, id).getStock()); // 4
        } finally {
            em.close();
            emf.close();
        }
    }
}
