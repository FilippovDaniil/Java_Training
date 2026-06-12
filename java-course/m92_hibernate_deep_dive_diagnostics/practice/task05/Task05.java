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
import java.util.ArrayList;
import java.util.List;

public class Task05 {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("shop-pu");
        EntityManager em = emf.createEntityManager();
        try {
            // TODO: засейте несколько Order05 с линиями; вызовите методы Good-версии и сравните
        } finally {
            em.close();
            emf.close();
        }
    }
}
