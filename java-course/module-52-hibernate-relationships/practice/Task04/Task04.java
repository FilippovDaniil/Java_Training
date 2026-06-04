/**
 * Задача 04 — Модуль 52: @OneToOne (Author — UserProfile)
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: org.hibernate.orm:hibernate-core, com.h2database:h2 (см. theory.md)
 *
 * ЗАДАНИЕ:
 *   Каждый автор имеет ровно один профиль (UserProfile4).
 *
 *   1) Разметьте связь на стороне Author4:
 *      - @OneToOne с cascade = CascadeType.ALL и orphanRemoval = true
 *      - @JoinColumn(name = "profile_id", unique = true)
 *      - fetch = FetchType.LAZY
 *   2) Разметьте обратную сторону на UserProfile4:
 *      - @OneToOne(mappedBy = "profile", fetch = FetchType.LAZY)
 *   3) В методе main:
 *      а) Создайте Author4 с UserProfile4 (bio, avatarUrl).
 *      б) Сохраните автора — профиль должен сохраниться каскадно.
 *      в) Загрузите профиль по id. Обратитесь к profile.getAuthor() — выведите имя.
 *      г) Удалите профиль у автора (author.setProfile(null)) и сохраните —
 *         проверьте, что orphanRemoval = true удалил запись из user_profiles.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (примерно):
 *   Автор профиля: Иван Петров
 *   После удаления профиля: profile IS NULL in DB
 *
 * ПОДСКАЗКА:
 *   Для двунаправленного @OneToOne важно синхронизировать обе стороны:
 *     author.setProfile(profile);
 *     profile.setAuthor(author);
 *   Иначе profile.getAuthor() вернёт null до перезагрузки из БД.
 *
 *   Чтобы убедиться в удалении через orphanRemoval, выполните em.flush()
 *   и повторно загрузите запись через em.find(UserProfile4.class, id).
 */

import jakarta.persistence.*;

public class Task04 {

    public static void main(String[] args) {
        // TODO: создать EMF (H2 in-memory)
        // TODO: создать Author4 + UserProfile4, установить связи обеих сторон
        // TODO: persist author, commit (каскад сохранит profile)
        // TODO: загрузить profile, получить profile.getAuthor().getName()
        // TODO: author.setProfile(null), merge, flush → убедиться в orphanRemoval
    }
}
