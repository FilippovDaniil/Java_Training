/**
 * Задача 06 — Модуль 87: каскады и orphanRemoval
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   org.hibernate.orm:hibernate-core, com.h2database:h2 + META-INF/persistence.xml ("shop-pu").
 *
 * ЗАДАНИЕ:
 *   Order06 (1) —— (N) OrderLine06 с cascade = ALL и orphanRemoval = true.
 *
 *   1) CASCADE PERSIST: создайте заказ с 3 строками, em.persist(ТОЛЬКО заказ) — строки
 *      сохранятся каскадно. Проверьте count строк = 3.
 *   2) ORPHAN REMOVAL: загрузите заказ, удалите одну строку из коллекции
 *      (order.getLines().remove(0)) — на commit «осиротевшая» строка удалится (count = 2).
 *   3) CASCADE REMOVE: em.remove(order) — удалит заказ И оставшиеся строки (count = 0).
 *
 *   После каждого шага печатайте число строк OrderLine06 в БД.
 *
 * ЦЕЛЬ: различить cascade REMOVE (удаление родителя) и orphanRemoval (отвязывание ребёнка).
 *
 * ПОДСКАЗКА: addLine синхронизирует обе стороны; orphanRemoval срабатывает именно при
 *            удалении ребёнка ИЗ КОЛЛЕКЦИИ родителя.
 */
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

public class Task06 {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("shop-pu");
        EntityManager em = emf.createEntityManager();
        Long orderId;
        try {
            // 1) cascade persist
            em.getTransaction().begin();
            Order06 order = new Order06("ORD-1");
            order.addLine(new OrderLine06("Хлеб", 2));
            order.addLine(new OrderLine06("Молоко", 1));
            order.addLine(new OrderLine06("Сыр", 1));
            em.persist(order);   // строки сохранятся каскадно
            em.getTransaction().commit();
            orderId = order.getId();
            em.clear();
            // TODO: System.out.println("строк после persist: " + countLines(em)); // 3

            // 2) orphanRemoval
            // TODO: em.getTransaction().begin();
            // TODO: Order06 loaded = em.find(Order06.class, orderId);
            // TODO: loaded.getLines().remove(0);   // осиротевшая строка
            // TODO: em.getTransaction().commit();
            // TODO: em.clear();
            // TODO: System.out.println("строк после orphanRemoval: " + countLines(em)); // 2

            // 3) cascade remove
            // TODO: em.getTransaction().begin();
            // TODO: em.remove(em.find(Order06.class, orderId));
            // TODO: em.getTransaction().commit();
            // TODO: em.clear();
            // TODO: System.out.println("строк после remove заказа: " + countLines(em)); // 0
        } finally {
            em.close();
            emf.close();
        }
    }

    static long countLines(EntityManager em) {
        return em.createQuery("select count(l) from OrderLine06 l", Long.class).getSingleResult();
    }
}

@Entity @Table(name = "orders")
class Order06 {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String number;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderLine06> lines = new ArrayList<>();
    protected Order06() {}
    public Order06(String number) { this.number = number; }
    public Long getId() { return id; }
    public List<OrderLine06> getLines() { return lines; }
    public void addLine(OrderLine06 l) { lines.add(l); l.setOrder(this); }
}

@Entity @Table(name = "order_lines")
class OrderLine06 {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String product;
    private int qty;
    @ManyToOne @JoinColumn(name = "order_id")
    private Order06 order;
    protected OrderLine06() {}
    public OrderLine06(String product, int qty) { this.product = product; this.qty = qty; }
    public void setOrder(Order06 o) { this.order = o; }
}
