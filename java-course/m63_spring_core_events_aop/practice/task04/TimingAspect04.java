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
class TimingAspect04 {

    // TODO: добавьте @Around с pointcut на все методы ReportService04
    public Object measureTime(ProceedingJoinPoint pjp) throws Throwable {
        // TODO: зафиксируйте start = System.nanoTime()
        // TODO: вызовите pjp.proceed() и сохраните результат
        // TODO: вычислите время в мс
        // TODO: выведите "[TIMING] <метод> выполнился за <мс> мс"
        // TODO: верните результат proceed()
        return null; // заменить на реальный return
    }
}
