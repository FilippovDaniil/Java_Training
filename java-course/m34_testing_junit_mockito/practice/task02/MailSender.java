package m34_testing_junit_mockito.practice.task02;

import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

interface MailSender {
    void send(String email, String text);
    void sendSms(String phone);
}
