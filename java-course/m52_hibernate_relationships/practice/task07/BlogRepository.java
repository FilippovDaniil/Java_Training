package m52_hibernate_relationships.practice.task07;

import jakarta.persistence.*;

import java.util.*;

// ─── Репозиторий — каркас ────────────────────────────────────────────────────
class BlogRepository {

    private final EntityManagerFactory emf;

    public BlogRepository(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public void save(Author7 author) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.merge(author);
            em.getTransaction().commit();
        }
    }

    public Author7 findById(Long id) {
        try (EntityManager em = emf.createEntityManager()) {
            return em.find(Author7.class, id);
        }
    }

    /**
     * Загружает авторов с постами, профилями, тегами и комментариями.
     * Использует несколько запросов для избежания проблем с множественным JOIN FETCH.
     */
    public List<Author7> findAuthorsWithPosts() {
        try (EntityManager em = emf.createEntityManager()) {
            // 1. Загружаем авторов с постами и профилями
            List<Author7> authors = em.createQuery(
                            "SELECT DISTINCT a FROM Author7 a " +
                                    "LEFT JOIN FETCH a.posts p " +
                                    "LEFT JOIN FETCH a.profile " +
                                    "ORDER BY a.id",
                            Author7.class)
                    .getResultList();

            if (authors.isEmpty()) {
                return authors;
            }

            // 2. Собираем ID всех постов
            List<Long> postIds = new ArrayList<>();
            for (Author7 author : authors) {
                for (Post7 post : author.getPosts()) {
                    postIds.add(post.getId());
                }
            }

            // 3. Загружаем теги для всех постов
            if (!postIds.isEmpty()) {
                List<Post7> postsWithTags = em.createQuery(
                                "SELECT DISTINCT p FROM Post7 p " +
                                        "LEFT JOIN FETCH p.tags " +
                                        "WHERE p.id IN :postIds",
                                Post7.class)
                        .setParameter("postIds", postIds)
                        .getResultList();

                Map<Long, Set<Tag7>> tagMap = new HashMap<>();
                for (Post7 post : postsWithTags) {
                    tagMap.put(post.getId(), post.getTags());
                }

                for (Author7 author : authors) {
                    for (Post7 post : author.getPosts()) {
                        Set<Tag7> tags = tagMap.get(post.getId());
                        if (tags != null) {
                            post.getTags().clear();
                            post.getTags().addAll(tags);
                        }
                    }
                }
            }

            // 4. Загружаем комментарии для всех постов
            if (!postIds.isEmpty()) {
                List<Post7> postsWithComments = em.createQuery(
                                "SELECT DISTINCT p FROM Post7 p " +
                                        "LEFT JOIN FETCH p.comments " +
                                        "WHERE p.id IN :postIds",
                                Post7.class)
                        .setParameter("postIds", postIds)
                        .getResultList();

                Map<Long, List<Comment7>> commentMap = new HashMap<>();
                for (Post7 post : postsWithComments) {
                    commentMap.put(post.getId(), post.getComments());
                }

                for (Author7 author : authors) {
                    for (Post7 post : author.getPosts()) {
                        List<Comment7> comments = commentMap.get(post.getId());
                        if (comments != null) {
                            post.getComments().clear();
                            post.getComments().addAll(comments);
                        }
                    }
                }
            }

            return authors;
        }
    }

    /**
     * Загружает посты автора с тегами и комментариями.
     * Использует два запроса и объединяет результаты.
     */
    public List<Post7> findPostsWithTagsAndComments(Long authorId) {
        try (EntityManager em = emf.createEntityManager()) {
            // 1. Загружаем посты с тегами
            List<Post7> postsWithTags = em.createQuery(
                            "SELECT DISTINCT p FROM Post7 p " +
                                    "LEFT JOIN FETCH p.tags " +
                                    "WHERE p.author.id = :authorId",
                            Post7.class)
                    .setParameter("authorId", authorId)
                    .getResultList();

            // 2. Загружаем посты с комментариями
            List<Post7> postsWithComments = em.createQuery(
                            "SELECT DISTINCT p FROM Post7 p " +
                                    "LEFT JOIN FETCH p.comments " +
                                    "WHERE p.author.id = :authorId",
                            Post7.class)
                    .setParameter("authorId", authorId)
                    .getResultList();

            // 3. Объединяем результаты
            Map<Long, Post7> postMap = new HashMap<>();
            for (Post7 post : postsWithTags) {
                postMap.put(post.getId(), post);
            }
            for (Post7 post : postsWithComments) {
                Post7 existing = postMap.get(post.getId());
                if (existing != null) {
                    existing.getComments().clear();
                    existing.getComments().addAll(post.getComments());
                }
            }

            return new ArrayList<>(postMap.values());
        }
    }
}
