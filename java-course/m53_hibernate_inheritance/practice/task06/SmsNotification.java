package m53_hibernate_inheritance.practice.task06;

import jakarta.persistence.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.time.LocalDateTime;
import java.util.List;

// --- SmsNotification (дополните @Entity @Table) ---

@Entity
@Table(name = "sms_notifications")
class SmsNotification extends Notification {

    private String phoneNumber;
    private String messageText;

    public SmsNotification() {}

    public SmsNotification(String phoneNumber, String messageText, String status) {
        super(status);
        this.phoneNumber = phoneNumber;
        this.messageText = messageText;
    }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getMessageText() { return messageText; }
    public void setMessageText(String messageText) { this.messageText = messageText; }

    @Override
    public String toString() {
        return String.format("SmsNotification{id=%d, phone='%s', text='%s', status='%s'}",
                getId(), phoneNumber, messageText, getStatus());
    }
}