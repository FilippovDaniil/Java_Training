/**
 * Задача 02 — Модуль 81: Dirty checking (UPDATE без save())
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   org.springframework.boot:spring-boot-starter-data-jpa:3.2.x, com.h2database:h2 (см. theory.md).
 *
 * НАСТРОЙКА: spring.jpa.show-sql=true (чтобы увидеть автоматический UPDATE).
 *
 * ЗАДАНИЕ:
 *   1) ProductService02.rename(id, newName) пометьте @Transactional.
 *      Внутри: найдите товар через repo.findById(id).orElseThrow(), вызовите setName(newName).
 *      НЕ вызывайте repo.save()! При коммите Hibernate сам сделает UPDATE (dirty checking).
 *   2) В CommandLineRunner: сохраните товар, вызовите rename, затем перечитайте
 *      из репозитория и убедитесь, что имя изменилось. В логах должен быть UPDATE.
 *
 * ВОПРОС (ответьте комментарием):
 *   Почему изменение managed-сущности сохраняется БЕЗ явного save()?
 *   Сработает ли это для detached-объекта (полученного вне транзакции)?
 *
 * ПОДСКАЗКА: внутри транзакции объект managed → Hibernate отслеживает изменения.
 */

import jakarta.persistence.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

public class Task02 {
    public static void main(String[] args) {
        SpringApplication.run(Task02.class, args);
    }
}
