package m63_spring_core_events_aop.practice.task04;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

// ============================================================
// Аспект замера времени
// ============================================================

// TODO: добавьте @Aspect и @Component
@Aspect
@Component
class TimingAspect04 {

    // TODO: добавьте @Around с pointcut на все методы ReportService04
    @Around("execution(* ReportService04.*(..))")
    public Object measureTime(ProceedingJoinPoint pjp) throws Throwable {
        long start = System.nanoTime();
        Object result = pjp.proceed();
        long ms = (System.nanoTime() - start) / 1_000_000;
        System.out.println("[TIMING] " + pjp.getSignature().getName() + " выполнился за " + ms + " мс");
        return result;
    }
}
