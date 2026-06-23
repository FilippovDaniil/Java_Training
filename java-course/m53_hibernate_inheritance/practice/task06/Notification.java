package m53_hibernate_inheritance.practice.task06;

import jakarta.persistence.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.time.LocalDateTime;
import java.util.List;

// --- Базовый класс уведомлений (дополните аннотациями и выберите стратегию) ---

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
abstract class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE) // TABLE — безопасно для TABLE_PER_CLASS
    private Long id;

    @Column(name = "sent_at")
    private LocalDateTime sentAt;

    @Column(nullable = false)
    private String status; // PENDING, SENT, FAILED

    public Notification() {}

    public Notification(String status) {
        this.status = status;
        this.sentAt = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDateTime getSentAt() { return sentAt; }
    public void setSentAt(LocalDateTime sentAt) { this.sentAt = sentAt; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return String.format("Notification{id=%d, status='%s', sentAt=%s}",
                id, status, sentAt);
    }
}
