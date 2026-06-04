/**
 * Задача 03 — Модуль 63: AOP @Before — логирование вызова метода
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   org.springframework:spring-context:6.1.x,
 *   org.springframework:spring-aspects:6.1.x,
 *   org.aspectj:aspectjweaver:1.9.x  (см. theory.md)
 * Это программа с main — запускайте в IDE с подключёнными зависимостями.
 *
 * ЗАДАНИЕ:
 *   1) В AppConfig03 включите поддержку AOP: добавьте аннотацию @EnableAspectJAutoProxy.
 *   2) ProductService03 уже готов. Он имеет метод addProduct(String name, double price).
 *   3) Реализуйте аспект LoggingAspect03:
 *        - добавьте аннотации @Aspect и @Component
 *        - добавьте @Before с pointcut-выражением, которое перехватывает
 *          все публичные методы класса ProductService03
 *        - в advice-методе выведите:
 *          "[LOG] Вызов: <имя метода>, аргументы: <аргументы>"
 *   4) В main создайте контекст, получите бин ProductService03 и вызовите
 *      addProduct("Ноутбук", 75000.0).
 *
 * ОЖИДАЕМЫЙ ВЫВОД (порядок строк):
 *   [LOG] Вызов: addProduct, аргументы: [Ноутбук, 75000.0]
 *   Добавляем товар: Ноутбук, цена: 75000.0
 *
 * ПОДСКАЗКА:
 *   @Before("execution(public * ProductService03.*(..))")
 *   public void logBefore(JoinPoint joinPoint) {
 *       String method = joinPoint.getSignature().getName();
 *       Object[] args = joinPoint.getArgs();
 *       System.out.println("[LOG] Вызов: " + method + ", аргументы: " + Arrays.toString(args));
 *   }
 */

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

public class Task03 {

    public static void main(String[] args) {
        // TODO: создайте контекст с AppConfig03
        // TODO: получите бин ProductService03 и вызовите addProduct("Ноутбук", 75000.0)
        // TODO: закройте контекст
    }
}
