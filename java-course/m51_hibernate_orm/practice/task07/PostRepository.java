package m51_hibernate_orm.practice.task07;

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
public class PostRepository {

    private final EntityManagerFactory emf;

    PostRepository(EntityManagerFactory emf) {
        this.emf = emf;
    }

    /** Сохранить новый пост. После вызова post.getId() будет заполнен. */
    public Post7 save(Post7 post) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(post);
            em.getTransaction().commit();
            return post;
        } finally {
            em.close();
        }
    }

    /** Найти пост по id. Возвращает Optional.empty() если не найден. */
    public Optional<Post7> findById(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            Post7 post = em.find(Post7.class, id);
            return Optional.ofNullable(post);
        } finally {
            em.close();
        }
    }

    /** Вернуть все посты. */
    public List<Post7> findAll() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("FROM Post7 ORDER BY id ASC", Post7.class)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    /** Обновить detached-пост через merge(). Возвращает managed-копию. */
    public Post7 update(Post7 post) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Post7 merged = em.merge(post);
            em.getTransaction().commit();
            return merged;
        } finally {
            em.close();
        }
    }

    /** Удалить пост по id. Ничего не делает если пост не найден. */
    public void delete(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Post7 post = em.find(Post7.class, id);
            if (post != null) {
                em.remove(post);
                System.out.println("   ✅ Пост с id=" + id + " удален");
            } else {
                System.out.println("   ⚠️ Пост с id=" + id + " не найден");
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    /** Вернуть количество постов. */
    public long count() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT COUNT(p) FROM Post7 p", Long.class)
                    .getSingleResult();
        } finally {
            em.close();
        }
    }

    /** Найти посты по заголовку (поиск по подстроке). */
    public List<Post7> findByTitleContaining(String pattern) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery(
                            "FROM Post7 p WHERE p.title LIKE :pattern ORDER BY p.id",
                            Post7.class)
                    .setParameter("pattern", "%" + pattern + "%")
                    .getResultList();
        } finally {
            em.close();
        }
    }

    /** Удалить все посты. */
    public void deleteAll() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createQuery("DELETE FROM Post7").executeUpdate();
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}
