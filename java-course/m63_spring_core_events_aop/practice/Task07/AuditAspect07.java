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
class AuditAspect07 {

    private final AuditLog07 auditLog;

    public AuditAspect07(AuditLog07 auditLog) {
        this.auditLog = auditLog;
    }

    // TODO: добавьте @Around("execution(* ShopOrderService07.*(..))")
    public Object audit(ProceedingJoinPoint pjp) throws Throwable {
        // TODO: вывести "[AUDIT START] <метод>(<аргументы>)"
        // TODO: зафиксировать start
        // TODO: вызвать pjp.proceed()
        // TODO: вычислить мс
        // TODO: добавить запись в auditLog: "[AUDIT END] <метод> — завершён за <мс> мс"
        // TODO: вывести запись в консоль
        // TODO: вернуть результат
        return null; // заменить на реальный return
    }
}
