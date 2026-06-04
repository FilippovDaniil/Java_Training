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
