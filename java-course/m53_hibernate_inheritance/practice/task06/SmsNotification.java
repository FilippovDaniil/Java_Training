package m53_hibernate_inheritance.practice.task06;

import jakarta.persistence.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.time.LocalDateTime;
import java.util.List;

// --- SmsNotification (дополните @Entity @Table) ---

// TODO: добавьте @Entity и @Table(name = "sms_notifications")
class SmsNotification extends Notification {
    private String phoneNumber;
    private String messageText;

    // TODO: конструктор(phoneNumber, messageText, status), геттеры, toString()
}
