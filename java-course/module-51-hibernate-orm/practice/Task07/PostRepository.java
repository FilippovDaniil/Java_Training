import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.cfg.Configuration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

// Каркас репозитория — реализуйте методы
class PostRepository {

    private final EntityManagerFactory emf;

    PostRepository(EntityManagerFactory emf) {
        this.emf = emf;
    }

    /** Сохранить новый пост. После вызова post.getId() будет заполнен. */
    public Post7 save(Post7 post) {
        // TODO: открыть EntityManager, начать транзакцию, persist, commit, закрыть em
        return post;
    }

    /** Найти пост по id. Возвращает Optional.empty() если не найден. */
    public Optional<Post7> findById(Long id) {
        // TODO: em.find(Post7.class, id) — не требует транзакции
        return Optional.empty();
    }

    /** Вернуть все посты. */
    public List<Post7> findAll() {
        // TODO: JPQL "FROM Post7 ORDER BY id ASC"
        return List.of();
    }

    /** Обновить detached-пост через merge(). Возвращает managed-копию. */
    public Post7 update(Post7 post) {
        // TODO: em.merge(post) внутри транзакции
        return post;
    }

    /** Удалить пост по id. Ничего не делает если пост не найден. */
    public void delete(Long id) {
        // TODO: найти через find, затем remove()
    }
}
