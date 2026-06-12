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
// Сервис (готов — не изменяйте)
// ============================================================

@Service
class ReportService04 {

    public void generateReport(int rows) {
        System.out.println("Генерируем отчёт на " + rows + " строк...");
        try {
            Thread.sleep(100); // имитация долгой работы
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("Отчёт готов.");
    }
}
