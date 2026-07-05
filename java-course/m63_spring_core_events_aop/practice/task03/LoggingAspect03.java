package m63_spring_core_events_aop.practice.task03;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import java.util.Arrays;

// ============================================================
// Аспект логирования
// ============================================================

// TODO: добавьте @Aspect
// TODO: добавьте @Component
@Aspect
@Component
class LoggingAspect03 {

    // TODO: добавьте @Before с нужным pointcut-выражением
    @Before("execution(public * ProductService03.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        // TODO: выведите "[LOG] Вызов: <метод>, аргументы: <аргументы>"
        System.out.println("[LOG] Вызов: <" + joinPoint.getSignature().getName() + ">, аргументы: "
        + Arrays.toString(joinPoint.getArgs()));
    }
}
