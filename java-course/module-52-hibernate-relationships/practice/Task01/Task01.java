/**
 * Задача 01 — Модуль 52: @ManyToOne и @OneToMany (Author — Post)
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: org.hibernate.orm:hibernate-core, com.h2database:h2 (см. theory.md)
 *
 * ЗАДАНИЕ:
 *   1) Разметьте класс Post аннотацией @ManyToOne, указав FetchType.LAZY.
 *      Добавьте @JoinColumn(name = "author_id") — это внешний ключ в таблице posts.
 *   2) Разметьте класс Author аннотацией @OneToMany.
 *      Укажите mappedBy = "author" — Hibernate узнает о владельце связи.
 *   3) В методе main создайте EntityManagerFactory (H2 in-memory),
 *      сохраните одного автора с двумя постами и снова загрузите из БД.
 *      Выведите: имя автора и заголовки его постов.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (примерно):
 *   Автор: Иван Петров
 *   Посты:
 *     - Введение в Java
 *     - Hibernate за 30 минут
 *
 * ПОДСКАЗКА:
 *   При сохранении обязательно устанавливайте обе стороны связи:
 *     post.setAuthor(author);
 *     author.getPosts().add(post);
 *   Использовать em.persist(author) достаточно, если cascade = CascadeType.ALL.
 */

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Task01 {

    public static void main(String[] args) {
        // Создайте EntityManagerFactory с конфигурацией H2 in-memory
        // (persistence-unit или Map<String,Object> с параметрами Hibernate)
        // TODO: настроить EMF
        // TODO: открыть транзакцию, создать Author + 2 Post, persist, commit
        // TODO: загрузить Author по id, вывести имя и посты
    }
}
