/**
 * Задача 07 — Модуль 52: МИНИ-ПРОЕКТ — полная доменная модель блога
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: org.hibernate.orm:hibernate-core, com.h2database:h2 (см. theory.md)
 *
 * ЗАДАНИЕ:
 *   Перед вами каркасы всех сущностей блога: Author7, Post7, Comment7, Tag7, UserProfile7.
 *   Все связи помечены TODO-аннотациями. Ваша задача:
 *
 *   1) РАЗМЕТИТЬ ВСЕ СВЯЗИ — заменить TODO-комментарии на правильные аннотации JPA:
 *      - Author7 ──1:N──► Post7      (@OneToMany mappedBy + @ManyToOne @JoinColumn)
 *      - Post7   ──1:N──► Comment7   (@OneToMany mappedBy + @ManyToOne @JoinColumn)
 *      - Post7   ──N:M──► Tag7       (@ManyToMany @JoinTable / mappedBy)
 *      - Author7 ──1:1──► UserProfile7 (@OneToOne @JoinColumn / mappedBy)
 *
 *   2) НАСТРОИТЬ CASCADE и FETCH:
 *      - Author7.posts    → cascade ALL, orphanRemoval, LAZY
 *      - Post7.comments   → cascade ALL, orphanRemoval, LAZY
 *      - Post7.tags       → cascade PERSIST+MERGE (не ALL!), LAZY
 *      - Author7.profile  → cascade ALL, orphanRemoval, LAZY
 *
 *   3) РЕАЛИЗОВАТЬ BlogRepository — класс с методами:
 *      а) save(Author7 author)               — persist в транзакции
 *      б) findById(Long id)                  — em.find(Author7.class, id)
 *      в) findAuthorsWithPosts()             — JOIN FETCH a.posts (без N+1!)
 *      г) findPostsWithTagsAndComments(Long authorId)
 *                                            — JOIN FETCH p.tags, JOIN FETCH p.comments
 *
 *   4) В методе main создайте полный сценарий:
 *      - 2 автора, у каждого профиль
 *      - у первого автора 3 поста, у второго 2 поста
 *      - 4 тега, распределить по постам (некоторые теги — общие)
 *      - у каждого поста 2–3 комментария
 *      - вызвать BlogRepository.findAuthorsWithPosts(), вывести всё дерево
 *
 * ОЖИДАЕМЫЙ ВЫВОД (примерно):
 *   === Авторы с постами ===
 *   Анна Смирнова [anna@blog.ru]
 *     POST: "Начало работы с Hibernate"  [теги: java, hibernate]
 *       COMMENT: "Спасибо, очень понятно!"
 *       COMMENT: "Когда будет продолжение?"
 *     POST: "Spring Data JPA — просто"  [теги: java, spring]
 *       COMMENT: "Отличная статья"
 *   Иван Козлов [ivan@blog.ru]
 *     POST: "Микросервисы с нуля"  [теги: spring, architecture]
 *       COMMENT: "А как с мониторингом?"
 *
 * ПОДСКАЗКА:
 *   Для findPostsWithTagsAndComments используйте два отдельных JOIN FETCH
 *   или @EntityGraph — нельзя сделать два JOIN FETCH одновременно
 *   на разные коллекции в одном запросе (HibernateException: cannot simultaneously fetch).
 *   Решение: загрузить посты с тегами, потом отдельно с комментариями,
 *   или использовать @BatchSize для одной из коллекций.
 */

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Task07 {

    public static void main(String[] args) {
        // TODO: создать EMF (H2 in-memory)

        // TODO: создать авторов с профилями
        // Author7 anna = new Author7("Анна Смирнова", "anna@blog.ru");
        // UserProfile7 annaProfile = new UserProfile7("Java-разработчик ...", "https://...");
        // anna.setProfile(annaProfile);

        // TODO: создать посты, теги, комментарии
        // TODO: связать всё через вспомогательные методы

        // TODO: BlogRepository repo = new BlogRepository(emf);
        // TODO: repo.save(anna); repo.save(ivan);

        // TODO: List<Author7> authors = repo.findAuthorsWithPosts();
        // TODO: вывести дерево автор → посты → теги → комментарии
    }
}
