package m90_hibernate_deep_dive_locking.practice.task03;

/**
 * Задача 03 — Модуль 90: пессимистичная блокировка PESSIMISTIC_WRITE (SELECT FOR UPDATE)
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   org.hibernate.orm:hibernate-core, com.h2database:h2 + META-INF/persistence.xml ("shop-pu").
 *   hibernate.show_sql=true — увидеть FOR UPDATE.
 *
 * ЗАДАНИЕ:
 *   Реализуйте «безопасное» списание остатка под эксклюзивной блокировкой строки.
 *   1) Сохраните Product03("Билет", stock=1).
 *   2) В транзакции загрузите товар с блокировкой записи:
 *        Product03 p = em.find(Product03.class, id, LockModeType.PESSIMISTIC_WRITE);
 *      В логе show_sql будет SELECT ... FOR UPDATE — строка залочена до commit.
 *   3) Спишите остаток (stock - 1), commit. Пока транзакция активна, другая транзакция,
 *      пытающаяся залочить ту же строку, ждала бы освобождения.
 *   4) Выведите остаток после операции.
 *
 * ЦЕЛЬ: освоить пессимистичную блокировку для критичных операций (бронирование, остатки).
 *
 * ПОДСКАЗКА: пессимистичная блокировка действует только в транзакции — короткой, чтобы
 *            не держать чужие транзакции в ожидании.
 */

import jakarta.persistence.*;

public class Task03 {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("shop-pu");
        EntityManager em = emf.createEntityManager();
        Long id;
        try {
            em.getTransaction().begin();
            Product03 p = new Product03("Билет", 1);
            em.persist(p);
            em.getTransaction().commit();
            id = p.getId();
            em.clear();

            // TODO: em.getTransaction().begin();
            // TODO: Product03 locked = em.find(Product03.class, id, LockModeType.PESSIMISTIC_WRITE);
            // TODO: locked.setStock(locked.getStock() - 1);   // под FOR UPDATE
            // TODO: em.getTransaction().commit();
            // TODO: System.out.println("остаток = " + em.find(Product03.class, id).getStock()); // 0
        } finally {
            em.close();
            emf.close();
        }
    }
}
