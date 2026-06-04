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

/** ❌ Намеренно плохой сервис — найдите анти-паттерны. */
class BadService05 {
    private final EntityManager em;
    BadService05(EntityManager em) { this.em = em; }

    // TODO: АНТИ-ПАТТЕРН 1 (?): ____________
    int totalLines() {
        int sum = 0;
        for (Order05 o : em.createQuery("select o from Order05 o", Order05.class).getResultList()) {
            sum += o.getLines().size();   // lazy в цикле
        }
        return sum;
    }

    // TODO: АНТИ-ПАТТЕРН 2 (?): ____________
    void raiseAll(int delta) {
        for (Order05 o : em.createQuery("select o from Order05 o", Order05.class).getResultList()) {
            o.setTotal(o.getTotal() + delta);
            em.merge(o);   // TODO: нужно ли merge для managed? + построчно вместо bulk
        }
    }
}

/** ✅ Реализуйте корректные версии. */
class GoodService05 {
    private final EntityManager em;
    GoodService05(EntityManager em) { this.em = em; }

    int totalLines() {
        // TODO: JOIN FETCH (один запрос) ИЛИ агрегат "select sum(size(o.lines)) ..." / count
        return 0;
    }

    void raiseAll(int delta) {
        // TODO: bulk "update Order05 o set o.total = o.total + :d" + em.clear()
    }
}

@Entity @Table(name = "orders")
class Order05 {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int total;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderLine05> lines = new ArrayList<>();
    protected Order05() {}
    public Order05(int total) { this.total = total; }
    public int getTotal() { return total; }
    public void setTotal(int total) { this.total = total; }
    public List<OrderLine05> getLines() { return lines; }
    public void addLine(OrderLine05 l) { lines.add(l); l.setOrder(this); }
}

@Entity @Table(name = "order_lines")
class OrderLine05 {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String product;
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "order_id")
    private Order05 order;
    protected OrderLine05() {}
    public OrderLine05(String product) { this.product = product; }
    public void setOrder(Order05 o) { this.order = o; }
}
