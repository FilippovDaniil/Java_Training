package m63_spring_core_events_aop.practice.task05;

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
// Аспект логирования исключений
// ============================================================

// TODO: добавьте @Aspect и @Component
@Aspect
@Component
class ExceptionLoggingAspect05 {

    // TODO: добавьте @AfterThrowing(pointcut = "...", throwing = "ex")
    @AfterThrowing(pointcut = "execution(* PaymentService05.*(..))", throwing = "ex")
    public void logException(JoinPoint joinPoint, Exception ex) {
        System.out.println("[ERROR] Исключение в "
                           + joinPoint.getSignature().getName() + ": " + ex.getMessage());
    }
}
