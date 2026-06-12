package m63_spring_core_events_aop.practice.task06;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

// ============================================================
// Аспект аудита — пишет в AuditLog06 после каждого вызова (готов)
// ============================================================

@Aspect
@Component
class LoggingAspect06 {

    private final AuditLog06 auditLog;

    public LoggingAspect06(AuditLog06 auditLog) {
        this.auditLog = auditLog;
    }

    @After("execution(* CatalogService.*(..))")
    public void logAfter(JoinPoint joinPoint) {
        String entry = "[AUDIT] " + joinPoint.getSignature().getName()
                + "(" + java.util.Arrays.toString(joinPoint.getArgs()) + ")";
        auditLog.add(entry);
    }
}
