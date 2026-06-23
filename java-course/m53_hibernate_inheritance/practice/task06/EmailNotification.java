package m53_hibernate_inheritance.practice.task06;

import jakarta.persistence.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.time.LocalDateTime;
import java.util.List;

// --- EmailNotification (дополните @Entity @Table) ---

@Entity
@Table(name = "email_notifications")
class EmailNotification extends Notification {

    private String subject;
    private String body;
    private String recipientEmail;

    public EmailNotification() {}

    public EmailNotification(String subject, String body, String recipientEmail, String status) {
        super(status);
        this.subject = subject;
        this.body = body;
        this.recipientEmail = recipientEmail;
    }

    public String getSubject() { return subject; }
    public void setSubject(String subject) { this.subject = subject; }

    public String getBody() { return body; }
    public void setBody(String body) { this.body = body; }

    public String getRecipientEmail() { return recipientEmail; }
    public void setRecipientEmail(String recipientEmail) { this.recipientEmail = recipientEmail; }

    @Override
    public String toString() {
        return String.format("EmailNotification{id=%d, subject='%s', recipient='%s', status='%s'}",
                getId(), subject, recipientEmail, getStatus());
    }
}
