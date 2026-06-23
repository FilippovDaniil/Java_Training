package m53_hibernate_inheritance.practice.task07;

import jakarta.persistence.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

// ============================================================
// PaymentRepository — репозиторий (каркас с TODO)
// ============================================================
class PaymentRepository {

    private final SessionFactory sessionFactory;

    PaymentRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void save(Payment payment) {
        try (Session session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            session.persist(payment);
            session.getTransaction().commit();
        }
    }

    public Optional<Payment> findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return Optional.ofNullable(session.get(Payment.class, id));
        }
    }

    public List<Payment> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Payment ORDER BY id", Payment.class)
                    .getResultList();
        }
    }

    public List<Payment> findByStatus(PaymentStatus status) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery(
                            "FROM Payment p WHERE p.status = :status ORDER BY id",
                            Payment.class
                    )
                    .setParameter("status", status)
                    .getResultList();
        }
    }

    public List<CardPayment> findAllCardPayments() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM CardPayment ORDER BY id", CardPayment.class)
                    .getResultList();
        }
    }

    public Map<String, Long> countByType() {
        try (Session session = sessionFactory.openSession()) {
            List<Object[]> results = session.createQuery(
                            "SELECT TYPE(p), COUNT(p) FROM Payment p GROUP BY TYPE(p)",
                            Object[].class
                    )
                    .getResultList();

            return results.stream()
                    .collect(Collectors.toMap(
                            row -> ((Class<?>) row[0]).getSimpleName(),
                            row -> (Long) row[1]
                    ));
        }
    }

    public void updateStatus(Long id, PaymentStatus newStatus) {
        try (Session session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            session.createQuery(
                            "UPDATE Payment p SET p.status = :status WHERE p.id = :id"
                    )
                    .setParameter("status", newStatus)
                    .setParameter("id", id)
                    .executeUpdate();
            session.getTransaction().commit();
        }
    }
}
