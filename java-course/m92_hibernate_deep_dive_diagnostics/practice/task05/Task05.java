package m92_hibernate_deep_dive_diagnostics.practice.task05;

/**
 * Задача 05 — Модуль 92: код-ревью — найти и исправить анти-паттерны
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   org.hibernate.orm:hibernate-core, com.h2database:h2 + META-INF/persistence.xml ("shop-pu").
 *
 * ЗАДАНИЕ (упражнение на ревью):
 *   В классе BadService05 ниже намеренно собраны анти-паттерны из всего трека.
 *   Найдите КАЖДЫЙ, объясните симптом и перепишите метод правильно (в GoodService05).
 *
 *   Анти-паттерны для поиска (минимум 4):
 *     1) N+1: загрузка всех заказов + обращение к lines в цикле без fetch.
 *     2) построчное обновление вместо bulk.
 *     3) лишний em.merge()/save() для managed-сущности (dirty checking и так сработает).
 *     4) загрузка целых сущностей там, где нужна лишь пара полей (надо DTO-проекцию).
 *     (бонус: отсутствие границы транзакции / чтение lazy вне сессии.)
 *
 *   Для каждого пункта:
 *     - пометьте в BadService05 комментарием "АНТИ-ПАТТЕРН N: <симптом>";
 *     - реализуйте корректный аналог в GoodService05 (JOIN FETCH / bulk / без merge / DTO).
 *
 * ЦЕЛЬ: научиться распознавать анти-паттерны в чужом коде — навык ревьюера.
 *
 * ПОДСКАЗКА: сверяйтесь с каталогом анти-паттернов в theory.md (таблица).
 */

import jakarta.persistence.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class Task05 {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("shop-pu");
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();

            // Создаем тестовые данные
            Order05 order1 = new Order05(100);
            order1.addLine(new OrderLine05("Product A"));
            order1.addLine(new OrderLine05("Product B"));

            Order05 order2 = new Order05(200);
            order2.addLine(new OrderLine05("Product C"));

            em.persist(order1);
            em.persist(order2);
            em.getTransaction().commit();

            // Тестируем GoodService
            GoodService05 goodService = new GoodService05(em);
            em.getTransaction().begin();

            System.out.println("Total lines: " + goodService.totalLines()); // Должно быть 3

            goodService.raiseAll(50);
            em.getTransaction().commit();

            // Проверяем результат
            em.getTransaction().begin();
            List<Order05> orders = em.createQuery("select o from Order05 o", Order05.class).getResultList();
            for (Order05 o : orders) {
                System.out.println("Order total after raise: " + o.getTotal());
            }
            em.getTransaction().commit();

        } finally {
            em.close();
            emf.close();
        }
    }
}
