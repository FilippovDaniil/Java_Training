import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

interface MailSender {
    void send(String email, String text);
    void sendSms(String phone);
}
