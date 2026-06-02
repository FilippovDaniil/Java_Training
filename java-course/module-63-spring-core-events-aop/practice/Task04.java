/**
 * Задача 04 — Модуль 63: AOP @Around — измерение времени выполнения
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   org.springframework:spring-context:6.1.x,
 *   org.springframework:spring-aspects:6.1.x,
 *   org.aspectj:aspectjweaver:1.9.x  (см. theory.md)
 * Это программа с main — запускайте в IDE с подключёнными зависимостями.
 *
 * ЗАДАНИЕ:
 *   1) ReportService04 имеет метод generateReport(int rows), который имитирует
 *      долгую работу через Thread.sleep(100). Код готов.
 *   2) Реализуйте аспект TimingAspect04 с advice-типом @Around:
 *        - зафиксируйте время ДО вызова (System.nanoTime())
 *        - вызовите реальный метод через pjp.proceed()
 *        - зафиксируйте время ПОСЛЕ
 *        - выведите: "[TIMING] <метод> выполнился за <мс> мс"
 *        - верните результат proceed()
 *   3) В main создайте контекст, получите бин ReportService04,
 *      вызовите generateReport(1000).
 *
 * ОЖИДАЕМЫЙ ВЫВОД (числа приблизительные):
 *   Генерируем отчёт на 1000 строк...
 *   Отчёт готов.
 *   [TIMING] generateReport выполнился за ~100 мс
 *
 * ПОДСКАЗКА:
 *   @Around("execution(* ReportService04.*(..))")
 *   public Object measureTime(ProceedingJoinPoint pjp) throws Throwable {
 *       long start = System.nanoTime();
 *       Object result = pjp.proceed();
 *       long ms = (System.nanoTime() - start) / 1_000_000;
 *       System.out.println("[TIMING] " + pjp.getSignature().getName() + " выполнился за " + ms + " мс");
 *       return result;
 *   }
 *
 * ВАЖНО: @Around должен возвращать Object и объявлять throws Throwable.
 *   Забыть вернуть результат proceed() — типичная ошибка: метод вернёт null.
 */
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

public class Task04 {

    public static void main(String[] args) {
        // TODO: создайте контекст с AppConfig04
        // TODO: получите бин ReportService04 и вызовите generateReport(1000)
        // TODO: закройте контекст
    }
}

// ============================================================
// Конфигурация
// ============================================================

@Configuration
@ComponentScan
@EnableAspectJAutoProxy
class AppConfig04 { }

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
