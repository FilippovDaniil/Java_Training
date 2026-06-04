/**
 * Задача 06 — Модуль 88: стратегии генерации идентификатора
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   org.hibernate.orm:hibernate-core, com.h2database:h2 + META-INF/persistence.xml ("shop-pu").
 *
 * ЗАДАНИЕ:
 *   Сравните три стратегии генерации id на трёх сущностях:
 *     1) IdentityEntity06   — @GeneratedValue(strategy = IDENTITY): id известен ТОЛЬКО после INSERT.
 *        Проверьте: до flush id == null; после persist+flush id != null.
 *     2) SequenceEntity06   — @GeneratedValue(strategy = SEQUENCE, generator="seq06")
 *        + @SequenceGenerator(name="seq06", sequenceName="entity_seq", allocationSize=1).
 *        id может быть получен до INSERT (из последовательности) — удобно для batch.
 *     3) UuidEntity06       — @Id private UUID id; @GeneratedValue (UUID-стратегия).
 *        id типа UUID, генерируется до INSERT.
 *
 *   В main сохраните по объекту каждого типа и выведите их id + краткий вывод о различиях.
 *
 * ЦЕЛЬ: понять, как стратегия влияет на момент появления id и на batch-вставку.
 *
 * ПОДСКАЗКА: IDENTITY отключает JDBC-батчинг вставок (id нужен от БД построчно);
 *            SEQUENCE — предпочтительна для Postgres/Oracle и bulk-insert.
 */
import jakarta.persistence.*;

import java.util.UUID;

public class Task06 {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("shop-pu");
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            IdentityEntity06 a = new IdentityEntity06("A");
            // TODO: System.out.println("IDENTITY до persist: " + a.getId()); // null
            em.persist(a);
            em.flush();
            // TODO: System.out.println("IDENTITY после flush: " + a.getId()); // не null

            SequenceEntity06 b = new SequenceEntity06("B");
            em.persist(b);
            em.flush();
            // TODO: System.out.println("SEQUENCE id: " + b.getId());

            UuidEntity06 c = new UuidEntity06("C");
            em.persist(c);
            em.flush();
            // TODO: System.out.println("UUID id: " + c.getId());
            em.getTransaction().commit();
        } finally {
            em.close();
            emf.close();
        }
    }
}

@Entity @Table(name = "identity_entities")
class IdentityEntity06 {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    protected IdentityEntity06() {}
    public IdentityEntity06(String name) { this.name = name; }
    public Long getId() { return id; }
}

@Entity @Table(name = "sequence_entities")
class SequenceEntity06 {
    @Id
    // TODO: @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq06")
    // TODO: @SequenceGenerator(name = "seq06", sequenceName = "entity_seq", allocationSize = 1)
    private Long id;
    private String name;
    protected SequenceEntity06() {}
    public SequenceEntity06(String name) { this.name = name; }
    public Long getId() { return id; }
}

@Entity @Table(name = "uuid_entities")
class UuidEntity06 {
    @Id
    // TODO: @GeneratedValue
    private UUID id;
    private String name;
    protected UuidEntity06() {}
    public UuidEntity06(String name) { this.name = name; }
    public UUID getId() { return id; }
}
