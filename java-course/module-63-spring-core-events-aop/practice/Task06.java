/**
 * Задача 06 — Модуль 63: Интеграционный тест Spring-контекста
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   org.springframework:spring-context:6.1.x,
 *   org.springframework:spring-aspects:6.1.x,
 *   org.aspectj:aspectjweaver:1.9.x,
 *   org.springframework:spring-test:6.1.x,
 *   org.junit.jupiter:junit-jupiter:5.x  (см. theory.md)
 * Это тест, а не программа с main — запускайте в IDE/Maven/Gradle.
 *
 * ЗАДАНИЕ:
 *   AppConfig06, CatalogService и LoggingAspect06 уже готовы.
 *   Напишите тесты в классе Task06:
 *
 *   1) addProductLogsToList():
 *        - вызовите catalogService.addProduct("Мышь")
 *        - проверьте, что catalogService.getProducts() содержит "Мышь"
 *        - проверьте, что auditLog.getLogs() содержит строку с "addProduct"
 *          (аспект должен добавить запись в AuditLog06)
 *
 *   2) multipleProductsAreStored():
 *        - добавьте три товара: "Клавиатура", "Монитор", "Наушники"
 *        - проверьте, что getProducts().size() == 3
 *
 * ПОДСКАЗКА:
 *   @SpringJUnitConfig загружает контекст Spring.
 *   @Autowired инжектирует бины прямо в поля теста.
 *   assertFalse(list.isEmpty()), assertTrue(list.contains("Мышь"))
 */
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

@SpringJUnitConfig(AppConfig06.class)
public class Task06 {

    @Autowired
    CatalogService catalogService;

    @Autowired
    AuditLog06 auditLog;

    @Test
    void addProductLogsToList() {
        // TODO: вызовите catalogService.addProduct("Мышь")
        // TODO: проверьте, что catalogService.getProducts().contains("Мышь")
        // TODO: проверьте, что auditLog.getLogs() не пуст
        //       и содержит строку, включающую "addProduct"
    }

    @Test
    void multipleProductsAreStored() {
        // TODO: добавьте три товара
        // TODO: проверьте, что catalogService.getProducts().size() >= 3
    }
}

// ============================================================
// Конфигурация (готова)
// ============================================================

@Configuration
@ComponentScan
@EnableAspectJAutoProxy
class AppConfig06 { }

// ============================================================
// Журнал аудита — накапливает записи аспекта (готов)
// ============================================================

@Component
class AuditLog06 {
    private final List<String> logs = new ArrayList<>();

    public void add(String entry) { logs.add(entry); }
    public List<String> getLogs() { return logs; }
}

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

// ============================================================
// Сервис-под-тестом (готов)
// ============================================================

@Service
class CatalogService {
    private final List<String> products = new ArrayList<>();

    public void addProduct(String name) {
        products.add(name);
    }

    public List<String> getProducts() {
        return products;
    }
}
