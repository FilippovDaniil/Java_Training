import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

// ============================================================
// Сервис (готов — не изменяйте)
// ============================================================

@Service
class PaymentService05 {

    public void processPayment(String cardNumber, double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Сумма платежа должна быть положительной: " + amount);
        }
        System.out.println("Платёж по карте " + cardNumber + " на сумму " + amount + " выполнен.");
    }
}
