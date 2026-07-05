package m63_spring_core_events_aop.practice.task07;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

// ============================================================
// Аспект аудита (каркас — реализуйте audit)
// ============================================================

// TODO: добавьте @Aspect и @Component
@Aspect
@Component
public class AuditAspect07 {

    private final AuditLog07 auditLog;

    @Autowired
    public AuditAspect07(AuditLog07 auditLog) {
        this.auditLog = auditLog;
    }

    @Around("execution(* ShopOrderService07.*(..))")
    public Object audit(ProceedingJoinPoint pjp) throws Throwable {
        String methodName = pjp.getSignature().getName();
        String args = Arrays.toString(pjp.getArgs());

        // START
        System.out.println("[AUDIT START] " + methodName + "(" + args + ")");
        long start = System.currentTimeMillis();

        // Выполнение целевого метода
        Object result = pjp.proceed();

        // END
        long duration = System.currentTimeMillis() - start;
        String logEntry = "[AUDIT END] " + methodName + " — завершён за " + duration + " мс";
        auditLog.add(logEntry);
        System.out.println(logEntry);

        return result;
    }
}