package m92_hibernate_deep_dive_diagnostics.practice.task05;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

/** ✅ Реализуйте корректные версии. */
class GoodService05 {
    private final EntityManager em;
    GoodService05(EntityManager em) { this.em = em; }

    int totalLines() {
        // Используем агрегатную функцию COUNT в одном запросе
        // Решение анти-паттерна N+1 и загрузки целых сущностей
        return em.createQuery(
                        "select count(l) from OrderLine05 l", Long.class)
                .getSingleResult()
                .intValue();

        // Альтернатива с JOIN FETCH (если нужны сами строки):
        // List<Order05> orders = em.createQuery(
        //     "select distinct o from Order05 o join fetch o.lines", Order05.class)
        //     .getResultList();
        // return orders.stream().mapToInt(o -> o.getLines().size()).sum();
    }

    void raiseAll(int delta) {
        // Используем bulk update - один запрос вместо N
        // Решение анти-паттернов: построчное обновление + лишний merge
        em.getTransaction().begin();
        try {
            int updated = em.createQuery(
                            "update Order05 o set o.total = o.total + :delta")
                    .setParameter("delta", delta)
                    .executeUpdate();
            em.getTransaction().commit();

            // Очищаем persistence context после bulk операции
            // чтобы managed-сущности не имели устаревших данных
            em.clear();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        }
    }
}
