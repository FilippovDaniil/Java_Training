/**
 * Задача 05 — Модуль 63: @AfterThrowing + pointcut-выражения
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   org.springframework:spring-context:6.1.x,
 *   org.springframework:spring-aspects:6.1.x,
 *   org.aspectj:aspectjweaver:1.9.x  (см. theory.md)
 * Это программа с main — запускайте в IDE с подключёнными зависимостями.
 *
 * ЗАДАНИЕ:
 *   1) PaymentService05 уже готов. Метод processPayment(String cardNumber, double amount)
 *      бросает IllegalArgumentException если сумма <= 0.
 *   2) Реализуйте аспект ExceptionLoggingAspect05:
 *        - перехватывайте исключения с помощью @AfterThrowing
 *        - параметр throwing свяжет аспект с брошенным Exception
 *        - выведите: "[ERROR] Исключение в <метод>: <сообщение исключения>"
 *   3) В main вызовите processPayment("4111-1111-1111-1111", -500.0) —
 *      аспект должен поймать исключение и залогировать его.
 *      Само исключение продолжит распространение (добавьте try/catch в main).
 *
 * POINTCUT-РАЗБОР:
 *   execution(* com.example.service.*.*(..))
 *   ─────────────────────────────────────
 *   execution  — перехват вызова метода
 *   *          — любой возвращаемый тип
 *   com.example.service.*  — любой класс в пакете service
 *   .*         — любой метод
 *   (..)       — любые аргументы (0 и более)
 *
 *   В нашем случае (без пакетов) используйте: execution(* PaymentService05.*(..))
 *
 * ПОДСКАЗКА:
 *   @AfterThrowing(pointcut = "execution(* PaymentService05.*(..))", throwing = "ex")
 *   public void logException(JoinPoint joinPoint, Exception ex) {
 *       System.out.println("[ERROR] Исключение в "
 *           + joinPoint.getSignature().getName() + ": " + ex.getMessage());
 *   }
 *
 * ВАЖНО: @AfterThrowing НЕ подавляет исключение — оно продолжает распространяться.
 *   Для подавления нужен @Around с try/catch внутри.
 */
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

public class Task05 {

    public static void main(String[] args) {
        // TODO: создайте контекст с AppConfig05
        // TODO: получите бин PaymentService05
        // TODO: вызовите processPayment("4111-1111-1111-1111", -500.0) в try/catch
        //       — аспект залогирует ошибку, исключение всё равно пробросится
        // TODO: закройте контекст
    }
}

// ============================================================
// Конфигурация
// ============================================================

@Configuration
@ComponentScan
@EnableAspectJAutoProxy
class AppConfig05 { }

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

// ============================================================
// Аспект логирования исключений
// ============================================================

// TODO: добавьте @Aspect и @Component
class ExceptionLoggingAspect05 {

    // TODO: добавьте @AfterThrowing(pointcut = "...", throwing = "ex")
    public void logException(JoinPoint joinPoint, Exception ex) {
        // TODO: выведите "[ERROR] Исключение в <метод>: <сообщение>"
    }
}
