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

// ============================================================
// PaymentRepository — репозиторий (каркас с TODO)
// ============================================================
class PaymentRepository {

    private final SessionFactory sessionFactory;

    PaymentRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /** Сохраняет новый платёж в транзакции */
    public void save(Payment payment) {
        // TODO: открыть сессию, начать транзакцию, persist, commit
    }

    /** Загружает платёж по id; возвращает Optional.empty() если не найден */
    public Optional<Payment> findById(Long id) {
        // TODO: session.get(Payment.class, id) → Optional.ofNullable(...)
        return Optional.empty();
    }

    /** Загружает все платежи (полиморфный запрос) */
    public List<Payment> findAll() {
        // TODO: JPQL "FROM Payment"
        return List.of();
    }

    /** Загружает платежи по статусу */
    public List<Payment> findByStatus(PaymentStatus status) {
        // TODO: JPQL "FROM Payment p WHERE p.status = :status"
        return List.of();
    }

    /** Загружает только карточные платежи */
    public List<CardPayment> findAllCardPayments() {
        // TODO: JPQL "FROM CardPayment"
        return List.of();
    }

    /**
     * Возвращает карту: имя класса → количество.
     * Используйте JPQL: "SELECT TYPE(p), COUNT(p) FROM Payment p GROUP BY TYPE(p)"
     */
    public Map<String, Long> countByType() {
        // TODO: выполните запрос, преобразуйте List<Object[]> в Map<String, Long>
        //       Object[0] = Class (простое имя через getSimpleName()), Object[1] = Long count
        return Map.of();
    }

    /** Обновляет статус платежа через JPQL UPDATE */
    public void updateStatus(Long id, PaymentStatus newStatus) {
        // TODO: JPQL "UPDATE Payment p SET p.status = :status WHERE p.id = :id"
        //       executeUpdate() требует явной транзакции!
    }
}
