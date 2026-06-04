/**
 * Задача 03 — Модуль 53: TABLE_PER_CLASS — отдельная таблица на каждый класс
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: org.hibernate.orm:hibernate-core, com.h2database:h2 (см. theory.md).
 * Файл не компилируется без этих зависимостей — это ожидаемо.
 *
 * ЗАДАНИЕ:
 *   Реализуйте иерархию Product → Book / Electronics стратегией TABLE_PER_CLASS:
 *     1) Product — абстрактный @Entity с @Inheritance(TABLE_PER_CLASS).
 *        Генератор id: GenerationType.TABLE (IDENTITY не работает с TABLE_PER_CLASS!).
 *     2) Book — @Entity @Table("tpc_books"), поля: author, isbn.
 *        Таблица tpc_books содержит ВСЕ поля: id, name, price, author, isbn.
 *     3) Electronics — @Entity @Table("tpc_electronics"), поля: brand, warrantyMonths.
 *        Таблица tpc_electronics содержит: id, name, price, brand, warrantyMonths.
 *     4) Сохраните несколько объектов; выполните JPQL "FROM Product2 p"
 *        и убедитесь, что возвращаются объекты обоих типов.
 *     5) Выведите SQL, который генерирует Hibernate для полиморфного запроса
 *        (включите show_sql=true) — вы увидите UNION.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (примерно):
 *   Hibernate: select ... from ( select ... from tpc_books union all select ... from tpc_electronics ) ...
 *   Всего товаров: 3
 *
 * ПОДСКАЗКА:
 *   GenerationType.TABLE требует таблицы hibernate_sequences (Hibernate создаёт автоматически
 *   при hbm2ddl.auto=create-drop).
 *   Полиморфный JPQL "FROM ProductTPC" → Hibernate строит UNION ALL по всем таблицам.
 *   При 10 подтипах это 10 подзапросов — один из минусов стратегии.
 *   Также нельзя создать FK из другой таблицы, ссылающийся на «любой Product».
 *
 *   После выполнения задания ответьте на вопросы в комментариях ниже.
 */

import jakarta.persistence.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.math.BigDecimal;
import java.util.List;

public class Task03 {

    public static void main(String[] args) {
        // TODO: создайте SessionFactory с show_sql=true чтобы видеть генерируемый SQL

        // TODO: сохраните 2 книги и 1 электронику

        // TODO: выполните JPQL: em.createQuery("FROM ProductTPC", ProductTPC.class).getResultList()
        //       и выведите размер списка

        // TODO: проверьте в консоли — Hibernate должен сгенерировать UNION ALL

        // TODO: закройте SessionFactory
    }

    /*
     * ВОПРОСЫ ДЛЯ РАЗМЫШЛЕНИЯ (ответьте в комментариях):
     *
     * Q1: Почему GenerationType.IDENTITY не работает с TABLE_PER_CLASS?
     *     (Подсказка: id должен быть уникален СРЕДИ ВСЕХ подтипов, а не только в одной таблице)
     *
     * Q2: Можно ли создать внешний ключ из таблицы orders на «любой Product»
     *     при стратегии TABLE_PER_CLASS? Почему?
     *
     * Q3: При каком числе подтипов UNION ALL становится заметно медленнее?
     */
}
