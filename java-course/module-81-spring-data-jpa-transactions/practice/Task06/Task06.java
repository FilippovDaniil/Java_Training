/**
 * Задача 06 — Модуль 81: Ловушка self-invocation (@Transactional не сработает)
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   org.springframework.boot:spring-boot-starter-data-jpa:3.2.x, com.h2database:h2 (см. theory.md).
 *
 * ЗАДАНИЕ (исследование ловушки + исправление):
 *   1) ИЗУЧИТЕ BrokenService06: метод outer() (БЕЗ @Transactional) вызывает inner()
 *      (С @Transactional) через this.inner(). Из-за самовызова прокси обходится —
 *      транзакция inner() НЕ откроется.
 *   2) ИСПРАВЬТЕ: вынесите inner() в ОТДЕЛЬНЫЙ бин InnerService06 (@Service) с
 *      @Transactional, и пусть FixedService06 вызывает его через внедрённую ссылку
 *      (тогда вызов идёт через прокси — транзакция работает).
 *   3) В CommandLineRunner вызовите fixedService.outer(...) и убедитесь, что товар сохранён.
 *
 * ВОПРОС (ответьте комментарием):
 *   Почему вызов this.inner() внутри того же класса обходит @Transactional?
 *
 * ПОДСКАЗКА: @Transactional работает через прокси (AOP, модуль 63);
 *            внутренний вызов this.method() не проходит через прокси.
 */

import jakarta.persistence.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

public class Task06 {
    public static void main(String[] args) {
        SpringApplication.run(Task06.class, args);
    }
}
