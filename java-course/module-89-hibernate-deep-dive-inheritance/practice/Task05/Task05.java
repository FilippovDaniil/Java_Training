/**
 * Задача 05 — Модуль 89: @DynamicUpdate — UPDATE только изменённых колонок
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   org.hibernate.orm:hibernate-core, com.h2database:h2 + META-INF/persistence.xml ("shop-pu").
 *   hibernate.show_sql=true — чтобы СРАВНИТЬ SQL обновления.
 *
 * ЗАДАНИЕ:
 *   1) Создайте две «широкие» сущности с одинаковыми полями (name, price, stock, description):
 *        - WideStatic05 — БЕЗ @DynamicUpdate (поведение по умолчанию);
 *        - WideDynamic05 — с @org.hibernate.annotations.DynamicUpdate.
 *   2) Сохраните по объекту каждого типа.
 *   3) В новой транзакции загрузите оба и измените ТОЛЬКО price у каждого (dirty checking).
 *   4) Сравните UPDATE в логе:
 *        - WideStatic05: UPDATE ... SET name=?, price=?, stock=?, description=? (ВСЕ колонки);
 *        - WideDynamic05: UPDATE ... SET price=? (только изменённая).
 *
 * ЦЕЛЬ: понять @DynamicUpdate и где он полезен (широкие таблицы, оптимистичная блокировка).
 *
 * ПОДСКАЗКА: @DynamicUpdate генерирует SQL на лету (не кэшируется) — это компромисс.
 */

import jakarta.persistence.*;

public class Task05 {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("shop-pu");
        EntityManager em = emf.createEntityManager();
        Long staticId, dynamicId;
        try {
            em.getTransaction().begin();
            WideStatic05 s = new WideStatic05("A", 100, 5, "desc-a");
            WideDynamic05 d = new WideDynamic05("B", 200, 7, "desc-b");
            em.persist(s); em.persist(d);
            em.getTransaction().commit();
            staticId = s.getId(); dynamicId = d.getId();
            em.clear();

            // TODO: em.getTransaction().begin();
            // TODO: em.find(WideStatic05.class, staticId).setPrice(111);   // UPDATE всех колонок
            // TODO: em.find(WideDynamic05.class, dynamicId).setPrice(222);  // UPDATE только price
            // TODO: em.getTransaction().commit();
            // TODO: System.out.println("Сравните два UPDATE в логе show_sql");
        } finally {
            em.close();
            emf.close();
        }
    }
}
