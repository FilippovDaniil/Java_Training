/**
 * Задача 05 — Модуль 53: Полиморфные JPQL-запросы и instanceof-обработка
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: org.hibernate.orm:hibernate-core, com.h2database:h2 (см. theory.md).
 * Файл не компилируется без этих зависимостей — это ожидаемо.
 *
 * ЗАДАНИЕ:
 *   Используя стратегию SINGLE_TABLE (быстрые полиморфные запросы), реализуйте:
 *     1) Иерархию ProductShop (abstract @Entity, SINGLE_TABLE) → Book, Electronics, Clothing.
 *        Каждый подкласс имеет минимум 1-2 уникальных поля.
 *     2) Заполните БД: 3 книги, 2 электроники, 2 одежды.
 *     3) Полиморфный запрос: загрузите ВСЕ товары одним JPQL "FROM ProductShop".
 *     4) Обработайте список через instanceof (pattern matching Java 16+):
 *        - для Book выведите: "Книга: {name} — {author}"
 *        - для Electronics: "Электроника: {name} — {brand}, гарантия {warrantyMonths} мес."
 *        - для Clothing: "Одежда: {name} — размер {size}"
 *     5) Напишите JPQL с фильтром по типу: "FROM Book b WHERE b.author = :author"
 *        и убедитесь, что возвращаются только книги нужного автора.
 *     6) Подсчитайте количество каждого типа через JPQL:
 *        "SELECT TYPE(p), COUNT(p) FROM ProductShop p GROUP BY TYPE(p)"
 *
 * ОЖИДАЕМЫЙ ВЫВОД (примерно):
 *   Книга: Чистый код — Роберт Мартин
 *   Электроника: MacBook Pro — Apple, гарантия 12 мес.
 *   Одежда: Джинсы — размер 32
 *   ... (все 7 товаров)
 *   По автору 'Роберт Мартин': 2 книги
 *   Тип BOOK: 3 шт., ELECTRONICS: 2 шт., CLOTHING: 2 шт.
 *
 * ПОДСКАЗКА:
 *   Полиморфный запрос:
 *     List<ProductShop> all = session
 *         .createQuery("FROM ProductShop", ProductShop.class)
 *         .getResultList();
 *
 *   Pattern matching (Java 16+):
 *     if (p instanceof Book b) { ... b.getAuthor() ... }
 *
 *   Запрос по типу:
 *     session.createQuery("FROM Book b WHERE b.author = :author", Book.class)
 *            .setParameter("author", "Роберт Мартин")
 *            .getResultList();
 *
 *   GROUP BY TYPE():
 *     session.createQuery(
 *         "SELECT TYPE(p), COUNT(p) FROM ProductShop p GROUP BY TYPE(p)",
 *         Object[].class
 *     ).getResultList();
 */

import jakarta.persistence.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.math.BigDecimal;
import java.util.List;

public class Task05 {

    public static void main(String[] args) {
        // TODO: создайте SessionFactory, зарегистрируйте все классы иерархии

        // TODO: сохраните тестовые данные (3 книги, 2 электроники, 2 одежды)

        // TODO: полиморфный запрос "FROM ProductShop" — загрузите все товары

        // TODO: обработайте список через instanceof с pattern matching
        //       и выведите информацию о каждом товаре

        // TODO: запрос "FROM BookShop b WHERE b.author = :author"
        //       подсчитайте результат и выведите

        // TODO: запрос "SELECT TYPE(p), COUNT(p) FROM ProductShop p GROUP BY TYPE(p)"
        //       выведите карту тип → количество

        // TODO: закройте SessionFactory
    }
}
