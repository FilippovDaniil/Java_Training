import jakarta.persistence.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.time.LocalDateTime;
import java.util.List;

// --- Базовый класс уведомлений (дополните аннотациями и выберите стратегию) ---

@Entity
// TODO: добавьте @Inheritance(strategy = ...) — выберите стратегию для Сценария C
abstract class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE) // TABLE — безопасно для TABLE_PER_CLASS
    private Long id;

    @Column(name = "sent_at")
    private LocalDateTime sentAt;

    @Column(nullable = false)
    private String status; // PENDING, SENT, FAILED

    // TODO: конструктор(status), геттеры, toString()
}
