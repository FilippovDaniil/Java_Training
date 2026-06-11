package m52_hibernate_relationships.practice.task07;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// ─── Репозиторий — каркас ────────────────────────────────────────────────────
class BlogRepository {

    private final EntityManagerFactory emf;

    public BlogRepository(EntityManagerFactory emf) {
        this.emf = emf;
    }

    /** Сохранить автора в транзакции (каскад сохранит всё дерево) */
    public void save(Author7 author) {
        // TODO: em.getTransaction().begin(); em.persist(author); em.getTransaction().commit();
    }

    /** Найти автора по id (простой em.find) */
    public Author7 findById(Long id) {
        // TODO: return em.find(Author7.class, id);
        return null;
    }

    /**
     * Загрузить всех авторов с постами одним запросом (решение N+1).
     * JPQL: "SELECT DISTINCT a FROM Author7 a JOIN FETCH a.posts"
     */
    public List<Author7> findAuthorsWithPosts() {
        // TODO: createQuery + JOIN FETCH
        return List.of();
    }

    /**
     * Загрузить посты автора с тегами и комментариями.
     * ПОДСКАЗКА: нельзя делать два JOIN FETCH на разные коллекции в одном запросе.
     * Выполните два отдельных запроса или используйте @BatchSize на одной коллекции.
     */
    public List<Post7> findPostsWithTagsAndComments(Long authorId) {
        // TODO
        return List.of();
    }
}
