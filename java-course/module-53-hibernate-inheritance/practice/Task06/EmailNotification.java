import jakarta.persistence.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.time.LocalDateTime;
import java.util.List;

// --- EmailNotification (дополните @Entity @Table) ---

// TODO: добавьте @Entity и @Table(name = "email_notifications")
class EmailNotification extends Notification {
    private String subject;
    private String body;
    private String recipientEmail;

    // TODO: конструктор(subject, body, recipientEmail, status), геттеры, toString()
}
