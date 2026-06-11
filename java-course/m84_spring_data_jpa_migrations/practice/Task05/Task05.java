package m84_spring_data_jpa_migrations.practice.task05;

/**
 * Задача 05 — Модуль 84: конфликт версий — ObjectOptimisticLockingFailureException
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   org.springframework.boot:spring-boot-starter-data-jpa:3.2.x, com.h2database:h2 (см. theory.md).
 *
 * ЗАДАНИЕ:
 *   Смоделируйте «потерянное обновление»: два пользователя прочитали товар одной версии,
 *   первый сохранил (версия выросла), второй сохраняет устаревшую копию → конфликт.
 *
 *   1) Product05 уже содержит @Version и setVersion (setVersion — ТОЛЬКО для имитации
 *      устаревшей клиентской копии в этом учебном примере; в реальном коде версию не трогают).
 *   2) staleUpdate(): @Transactional
 *        a) p1 = repo.findById(id) → version V; p1.setPrice(...); repo.save(p1) (версия → V+1);
 *        b) соберите detached-копию p2 с тем же id и СТАРОЙ версией V (имитируем второго
 *           пользователя, читавшего раньше), p2.setPrice(другая цена);
 *        c) repo.save(p2) → должно бросить ObjectOptimisticLockingFailureException.
 *   3) В Runner поймайте исключение и напечатайте "Конфликт версий: " + e.getClass().getSimpleName().
 *
 * ЦЕЛЬ: убедиться, что @Version защищает от затирания чужих изменений.
 *
 * ПОДСКАЗКА: detached-копия — это new Product05(...) с выставленными id и version
 *            (как будто пришла от клиента). save() для неё = merge → UPDATE с WHERE version=V.
 */

import jakarta.persistence.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

public class Task05 {
    public static void main(String[] args) {
        SpringApplication.run(Task05.class, args);
    }
}
