/**
 * Задача 04 — Модуль 51: Обновление detached-объекта через merge()
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: org.hibernate.orm:hibernate-core, com.h2database:h2 (см. theory.md)
 *
 * ЗАДАНИЕ:
 *   1) Сохраните пост (первая сессия — объект в состоянии PERSISTENT).
 *   2) Закройте первую сессию → объект становится DETACHED.
 *   3) Измените title у detached-объекта (без сессии).
 *   4) Откройте вторую сессию, вызовите session.merge(detachedPost) → объект снова PERSISTENT.
 *   5) Сделайте commit — Hibernate выполнит UPDATE.
 *   6) Откройте третью сессию, загрузите пост по id и убедитесь, что title обновился.
 *
 * СОСТОЯНИЯ:
 *   PERSISTENT  — изменения объекта отслеживаются сессией (dirty checking)
 *   DETACHED    — сессия закрыта; изменения объекта НЕ попадут в БД без merge()
 *
 * ОЖИДАЕМЫЙ ВЫВОД (примерно):
 *   Hibernate: insert into posts …
 *   Hibernate: update posts set title=? … where id=?
 *   Обновлённый title: "Изменённый заголовок"
 *
 * ПОДСКАЗКА:
 *   // После закрытия первой сессии:
 *   post.setTitle("Изменённый заголовок");  // меняем detached
 *   try (Session session2 = factory.openSession()) {
 *       Transaction tx = session2.beginTransaction();
 *       Post4 managed = session2.merge(post);  // merge возвращает managed-копию
 *       tx.commit();
 *   }
 */

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import java.time.LocalDateTime;

public class Task04 {

    public static void main(String[] args) {
        // TODO 1: создайте SessionFactory

        Post4 post = null;

        // TODO 2: первая сессия — сохраните пост, запомните объект post
        //         после закрытия сессии post становится DETACHED

        // TODO 3: измените post.setTitle("Изменённый заголовок")
        //         (пока сессия закрыта — это detached-операция, в БД не попадёт)

        // TODO 4: вторая сессия — вызовите session.merge(post)
        //         обратите внимание: merge() возвращает новый managed-экземпляр

        // TODO 5: третья сессия — загрузите пост по id и проверьте title

        // TODO 6: закройте factory
    }
}
