/**
 * Задача 02 — Модуль 82: воспроизвести и устранить LazyInitializationException
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   org.springframework.boot:spring-boot-starter-data-jpa:3.2.x, com.h2database:h2 (см. theory.md).
 *
 * ЗАДАНИЕ:
 *   1) brokenRead(id): БЕЗ @Transactional загрузить категорию через репозиторий и
 *      обратиться к c.getProducts().size(). Поймать LazyInitializationException и
 *      напечатать "Поймано: " + e.getClass().getSimpleName().
 *   2) fixedRead(id): пометить @Transactional, тот же доступ к products — теперь работает.
 *      Вернуть products.size().
 *
 * ЦЕЛЬ: на практике увидеть, что ленивая связь требует открытой сессии (транзакции).
 *
 * ПОДСКАЗКА: чтобы "сессия закрылась" между загрузкой и доступом, в brokenRead не
 *            ставьте @Transactional и выполняйте size() уже после возврата из репозитория.
 *            Для чистоты эксперимента можно выключить spring.jpa.open-in-view=false.
 */

import jakarta.persistence.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;

public class Task02 {
    public static void main(String[] args) {
        SpringApplication.run(Task02.class, args);
    }
}
