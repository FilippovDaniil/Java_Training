/**
 * Задача 06 — Модуль 52: FetchType LAZY vs EAGER и проблема N+1
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: org.hibernate.orm:hibernate-core, com.h2database:h2 (см. theory.md)
 *
 * ЗАДАНИЕ:
 *   1) Включите логирование SQL. В Hibernate это делается через свойство
 *      "hibernate.show_sql" = "true" при создании SessionFactory.
 *      Добавьте также "hibernate.format_sql" = "true".
 *
 *   2) Сценарий N+1 (воспроизвести и посчитать запросы):
 *      а) Сохраните 5 авторов, у каждого по 3 поста.
 *      б) Выполните JPQL: "SELECT a FROM Author6 a"  — загрузит 5 авторов.
 *      в) В цикле для каждого автора вызовите author.getPosts().size().
 *         Посчитайте SQL-запросы в консоли. Сколько их?
 *
 *   3) Решение через JOIN FETCH:
 *      а) Замените запрос на:
 *           "SELECT DISTINCT a FROM Author6 a JOIN FETCH a.posts"
 *         Снова посчитайте SQL-запросы.
 *      б) Объясните в комментарии разницу: сколько запросов без JOIN FETCH
 *         и сколько с JOIN FETCH при 5 авторах?
 *
 *   4) Сценарий EAGER (показать проблему загрузки):
 *      Временно измените @ManyToOne на авторе комментария на FetchType.EAGER.
 *      Загрузите все комментарии: "SELECT c FROM Comment6 c".
 *      Обратите внимание на количество JOIN в одном SELECT.
 *      Объясните в комментарии: чем EAGER опасен при большом графе объектов?
 *
 * ОЖИДАЕМЫЙ ПОДСЧЁТ (в комментарии):
 *   Без JOIN FETCH:  1 + 5 = 6 SQL-запросов (N+1)
 *   С JOIN FETCH:    1 SQL-запрос
 *
 * ПОДСКАЗКА:
 *   DISTINCT в JPQL нужен, чтобы убрать дублирование авторов (из-за JOIN).
 *   Hibernate 6 добавляет DISTINCT автоматически при JOIN FETCH в большинстве случаев,
 *   но явный DISTINCT — лучшая практика.
 */

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Task06 {

    public static void main(String[] args) {
        // TODO: создать EMF с hibernate.show_sql = true, hibernate.format_sql = true

        // TODO: [ПОДГОТОВКА] сохранить 5 авторов, каждому 3 поста

        // Сценарий N+1
        // TODO: выполнить "SELECT a FROM Author6 a"
        // TODO: цикл — authors.forEach(a -> a.getPosts().size())
        // TODO: подсчитать в консоли количество SQL-запросов (ожидается 6)

        // Решение через JOIN FETCH
        // TODO: выполнить "SELECT DISTINCT a FROM Author6 a JOIN FETCH a.posts"
        // TODO: проверить — должен быть 1 SQL-запрос

        // Сценарий EAGER
        // TODO: изменить FetchType у Comment6.post на EAGER
        // TODO: выполнить "SELECT c FROM Comment6 c", наблюдать SQL
    }
}
